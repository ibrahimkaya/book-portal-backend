package tr.com.obss.jss.week3spring.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.obss.jss.week3spring.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
    Page<Author> findAll(Pageable pageable);

    Page<Author> findByNameIsContaining(String name,Pageable pageable);
}
