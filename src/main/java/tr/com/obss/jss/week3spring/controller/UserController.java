package tr.com.obss.jss.week3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jss.week3spring.entity.User;
import tr.com.obss.jss.week3spring.model.UserDTO;
import tr.com.obss.jss.week3spring.model.UserUpdateDTO;
import tr.com.obss.jss.week3spring.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> listAll(@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                     @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok(userService.findAll(pageSize, pageNumber));
    }

    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<?> getAuthUser(Principal principal) {
        return ResponseEntity.ok(userService.getAuthuser(principal.getName()));
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.save(userDTO);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{param}")
    @ResponseBody
    public ResponseEntity<?> getByParam(@PathVariable String param,
                                        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                        @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok(userService.getByParams(param, pageSize, pageNumber));

    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userService.update(id, userUpdateDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        User user = userService.delete(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/has-role-user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByRoles() {
        List<User> userList = userService.findByRoles(Arrays.asList("ROLE_USER"));
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/favorites/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> addFavoriteBook(@PathVariable long id, Principal principal) {
        return ResponseEntity.ok(userService.doFavorites(id, principal.getName()));
    }

    @PostMapping("/reads/{id}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> addReadBook(@PathVariable long id, Principal principal) {

        return ResponseEntity.ok(userService.doReads(id, principal.getName()));
    }

    @GetMapping("/favorites")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> getAllFavorites(Principal principal) {
        return ResponseEntity.ok(userService.getAllFavorites(principal.getName()));
    }

    @GetMapping("/reads")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> getAllReads(Principal principal) {
        return ResponseEntity.ok(userService.getAllReads(principal.getName()));
    }

    @GetMapping("/profile/{username}")
    @ResponseBody
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }


}
