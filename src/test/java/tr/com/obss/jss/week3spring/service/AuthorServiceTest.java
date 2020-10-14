package tr.com.obss.jss.week3spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tr.com.obss.jss.week3spring.entity.Author;
import tr.com.obss.jss.week3spring.model.AuthorDTO;
import tr.com.obss.jss.week3spring.model.AuthorUpdateDTO;
import tr.com.obss.jss.week3spring.repo.AuthorRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

/**
 * @Author ibrahim
 * @create 13.10.2020 14:03
 */
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks
    AuthorService service;

    @Mock
    AuthorRepository repository;

    @DisplayName("get all authors")
    @Test
    void getAll() {
        Page<Author> authors = new PageImpl<>(new ArrayList<>());
        Pageable pageable = PageRequest.of(0, 5);

        //given
        given(repository.findAll(pageable)).willReturn(authors);

        //when
        Page<Author> foundAuthors = service.getAll(5, 0);

        //then
        then(repository).should(only()).findAll(pageable);
        assertEquals(foundAuthors, authors);
    }


    @DisplayName("create author from authorDTO")
    @Test
    void create() {
        //given
        Author author = new Author();
        author.setName("kaya");

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("kaya");

        Author wrongNameAuthor = new Author();
        wrongNameAuthor.setName("not equal");

        given(repository.save(any(Author.class))).willReturn(author);

        //when
        Author foundAuthor = service.create(authorDTO);


        //then
        then(repository).should(only()).save(author);
        assertEquals(author, foundAuthor);
        assertNotEquals(wrongNameAuthor, foundAuthor);
    }

    @DisplayName("delete author")
    @Test
    void delete() {
        //given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("existAuthor");

        Author author = new Author();
        author.setName("existAuthor");
        author.setActive(true);

        given(repository.findByName(authorDTO.getName())).willReturn(Optional.of(author));
        given(repository.save(any(Author.class))).willReturn(author);

        //when
        Author foundAuthor = service.delete(authorDTO);

        //then
        assertThrows(IllegalArgumentException.class, () -> service.delete(new AuthorDTO()), "nonexistent author should be throw an exception");
        assertEquals(foundAuthor, author, "deleted author should return itself");
    }

    @Test
    void update() {
        //given
        AuthorUpdateDTO authorUpdateDTO = new AuthorUpdateDTO();
        authorUpdateDTO.setName("new name");

        Author author = new Author();
        author.setName("old name");

        AuthorUpdateDTO nonExistAuthor = new AuthorUpdateDTO();
        nonExistAuthor.setName("not exists");


        given(repository.findByName(authorUpdateDTO.getName())).willReturn(Optional.of(author));

        given(repository.save(author)).willReturn(author);
        given(repository.findByName(nonExistAuthor.getName())).willReturn(Optional.empty());

        //when
        Author foundAuthor = service.update(authorUpdateDTO);
        //then
        assertThrows(IllegalArgumentException.class, () -> service.update(nonExistAuthor), "nonexistent author should be throw an exception");
        assertEquals(foundAuthor.getName(), authorUpdateDTO.getNewName());
    }

    @Test
    void getByName() {
        //given
        Pageable pageable = PageRequest.of(0, 5);
        Page<Author> authors = new PageImpl<>(new ArrayList<>());
        given(repository.findByNameIsContaining("", pageable)).willReturn(authors);

        //when
        Page<Author> foundAuthors = service.getByName("", 0, 5);

        //then
        assertEquals(authors, foundAuthors);
    }
}