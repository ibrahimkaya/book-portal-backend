package tr.com.obss.jss.week3spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jss.week3spring.model.AuthorDTO;
import tr.com.obss.jss.week3spring.model.AuthorUpdateDTO;
import tr.com.obss.jss.week3spring.service.AuthorService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getAllAuthors( @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        return ResponseEntity.ok(authorService.getAll(pageSize,pageNumber));
    }

    @GetMapping("{name}")
    @ResponseBody
    public ResponseEntity<?> getByName(@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                       @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @PathVariable String name){
        return ResponseEntity.ok(authorService.getByName(name,pageNumber,pageSize));
    }

    @PostMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody AuthorDTO authorDTO){
       return ResponseEntity.ok( authorService.create(authorDTO));
    }

    @PutMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody AuthorUpdateDTO authorUpdateDTO){
        return ResponseEntity.ok( authorService.update(authorUpdateDTO));
    }

    @DeleteMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@Valid @RequestBody AuthorDTO authorDTO){
        return ResponseEntity.ok( authorService.delete(authorDTO));
    }


}
