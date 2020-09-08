package tr.com.obss.jss.week3spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.week3spring.entity.Author;
import tr.com.obss.jss.week3spring.entity.Book;
import tr.com.obss.jss.week3spring.entity.User;
import tr.com.obss.jss.week3spring.model.BookDTO;
import tr.com.obss.jss.week3spring.model.BookUpdateDTO;
import tr.com.obss.jss.week3spring.repo.AuthorRepository;
import tr.com.obss.jss.week3spring.repo.BookRepository;

import java.util.*;

@Service
public class BookService {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Page<Book> findAll(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findAll(pageable);
    }

    public Book save(BookDTO bookDTO) {
        Book book = new Book();
        Optional<Author> author = authorRepository.findByName(bookDTO.getAuthor());

        if (!author.isPresent() || !author.get().isActive()) {
            throw new IllegalArgumentException("mevcut yazar bulunamadı");
        }
        if (bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent()){
            throw new IllegalArgumentException("isbn numarası birden fazla mevcut");
        }

        book.setAuthor(author.get());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());

        return bookRepository.save(book);
    }

    public Book update(long id, BookUpdateDTO bookUpdateDTO) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            Book book = byId.get();
            Optional<Author> author = authorRepository.findByName(bookUpdateDTO.getAuthor());

            if (!author.isPresent() || !author.get().isActive())
                throw new IllegalArgumentException("mevcut yazar bulunamadı");

            book.setAuthor(author.get());
            book.setTitle(bookUpdateDTO.getTitle());
            return bookRepository.save(book);
        }
        throw new IllegalArgumentException("kitap bulunamadı");
    }

    public Book delete(long id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            Book book = byId.get();
            book.setActive(!book.isActive());
            return bookRepository.save(book);
        }
        throw new IllegalArgumentException("kitap bulunamadı");
    }

    public Book findById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new IllegalArgumentException("kitap bulunamadi");
    }

    /*
        Controllere gelen değer eğer string bir değer ise sadece kitap isimi ve yazar isimlerine göre arat
        eğer gelen değer numerik bir değer ise kitap başlığı yada yazar isminde içeriyor mu kobtrolü yap
     */
    public Page<Book> getByParams(String param, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (isLong(param)) {
            long longparam = Long.parseLong(param);
            return bookRepository.findAllByIsbnOrIdOrTitleIsContainingOrAuthor_NameIsContaining(longparam, longparam, param,param, pageable);

        } else {
            return bookRepository.findAllByTitleIsContainingOrAuthor_NameIsContaining(param, param, pageable);
        }
    }

    public boolean isLong(String s) {
        try {
            Long.parseLong(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    //@Todo sadece 10 isim için tüm kişileri ve bilgilerini çekmek mantıksız daha verimli yollarını ara
    //pageable dene
    public Map<String, List<String>> getAllFavsUser(long bookId) {

        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent())
            throw new IllegalArgumentException("kitap bulunamadı");

        return listFormatter(book.get().getFavUserList());
    }

    public Map<String, List<String>> getAllReadUser(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent())
            throw new IllegalArgumentException("kitap bulunamadı");

        return listFormatter(book.get().getReadUserList());
    }

    private Map<String, List<String>> listFormatter(List<User> userList) {
        Map<String, List<String>> infoMap = new HashMap<>();
        List<String> usernameList = new ArrayList<>();
        List<String> sizeList = new ArrayList<>();
        sizeList.add(String.valueOf(userList.size()));

        infoMap.put("size", sizeList);

        if (userList.size() >= 10) {
            userList = userList.subList(userList.size() - 1, userList.size() - 11);
        }

        //get last 10 users username
        for (User user : userList) {
            usernameList.add(user.getUsername());
        }

        infoMap.put("username", usernameList);

        return infoMap;
    }

}
