package tr.com.obss.jss.week3spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.week3spring.entity.Book;
import tr.com.obss.jss.week3spring.entity.User;
import tr.com.obss.jss.week3spring.model.MyUserDetails;
import tr.com.obss.jss.week3spring.model.UserDTO;
import tr.com.obss.jss.week3spring.model.UserUpdateDTO;
import tr.com.obss.jss.week3spring.repo.RoleRepository;
import tr.com.obss.jss.week3spring.repo.UserRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private BookService bookService;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public User save(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent())
            throw new IllegalArgumentException("bu kullanıcı ismi daha önce alınmış");

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);
    }

    public List<User> findByRoles(List<String> roles) {
        return userRepository.findByRoles_NameIn(roles);
    }

    public Page<User> findAll(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    public User getAuthuser(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("kullanıcı bulunamadı");
        } else {
            return user.get();
        }
    }

    public User update(long id, UserUpdateDTO userUpdateDTO) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setPassword(encoder.encode(userUpdateDTO.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("kullanıcı bulunamadı");
    }

    public User delete(long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setActive(!user.isActive());
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("kullanıcı bulunamadı");
    }

    public List<Book> doFavorites(long bookId, String username){
        User user = checkUser(username);

        Book book = bookService.findById(bookId);

        if (user.getFavoritesList().contains(book)) {
            user.getFavoritesList().remove(book);

            userRepository.save(user);

            return user.getFavoritesList();
        }else{
            user.getFavoritesList().add(book);
            userRepository.save(user);

            return user.getFavoritesList();
        }
    }

    public List<Book> doReads(long bookId, String username){
        User user = checkUser(username);

        Book book = bookService.findById(bookId);

        if (user.getReadList().contains(book)) {
            user.getReadList().remove(book);

            userRepository.save(user);

            return user.getReadList();
        }else{
            user.getReadList().add(book);
            userRepository.save(user);

            return user.getReadList();
        }
    }



    public Page<User> getByParams(String param, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (isLong(param)) {
            long longparam = Long.parseLong(param);
            return userRepository.findAllByIdOrUsernameContaining(longparam, param, pageable);

        } else {
            return userRepository.findByUsernameIsContaining(param, pageable);
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

    private User checkUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent())
            throw new IllegalArgumentException("kullanici bulunamadi");
        return user.get();
    }


    public List<Book> getAllFavorites(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent())
            return user.get().getFavoritesList();
        throw new IllegalArgumentException("kullanıcı bulunamadı");

    }

    public List<Book> getAllReads(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent())
            return user.get().getReadList();
        throw new IllegalArgumentException("kullanıcı bulunamadı");
    }

    public User getUserProfile(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent() || !user.get().isActive()) {
            throw new IllegalArgumentException("kullanıcı bulunamadı");
        }

        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> byUserName = userRepository.findByUsername(username);
        if (byUserName.isPresent()) {
            return new MyUserDetails(byUserName.get());
        }
        throw new UsernameNotFoundException("kullanıcı bulunamadı");
    }


}
