package tr.com.obss.jss.week3spring.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.week3spring.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);

    Optional<Book> findByIsbn(long isbn);

    Page<Book> findAllByIsbnOrIdOrTitleIsContainingOrAuthor_NameIsContaining(long p1, long p2,String p3, String p4, Pageable pageable);

    Page<Book> findAllByTitleIsContainingOrAuthor_NameIsContaining(String p1, String p2, Pageable pageable);

}

