package tr.com.obss.jss.week3spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.week3spring.entity.Author;
import tr.com.obss.jss.week3spring.model.AuthorDTO;
import tr.com.obss.jss.week3spring.model.AuthorUpdateDTO;
import tr.com.obss.jss.week3spring.repo.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> getAll(int pageSize, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findAll(pageable);
    }

    public Author create(AuthorDTO authorDTO){
        Author author = new Author();

        author.setName(authorDTO.getName());
        return authorRepository.save(author);
    }

    public Author delete(AuthorDTO authorDTO){
        Optional<Author> author = authorRepository.findByName(authorDTO.getName());

        if(!author.isPresent())
            throw new IllegalArgumentException("girilen isimde yazar bulunamadı");

        author.get().setActive(!author.get().isActive());
        return authorRepository.save(author.get());
    }

    public Author update(AuthorUpdateDTO authorUpdateDTO){
        Optional<Author> author = authorRepository.findByName(authorUpdateDTO.getName());

        if(!author.isPresent())
            throw new IllegalArgumentException("girilen isimde yazar bulunamadı");

        author.get().setName(authorUpdateDTO.getNewName());
        return authorRepository.save(author.get());
    }


    public Page<Author> getByName(String name,int pageNumber,int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findByNameIsContaining(name,pageable);

    }

}
