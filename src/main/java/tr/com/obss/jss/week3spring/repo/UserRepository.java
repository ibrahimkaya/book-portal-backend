package tr.com.obss.jss.week3spring.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.week3spring.entity.Book;
import tr.com.obss.jss.week3spring.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByRoles_NameIn(List<String> roles);

    Page<User> findAllByIdOrUsernameContaining(long p1, String p2, Pageable pageable);

    Page<User> findByUsernameIsContaining(String username, Pageable pageable);

}
