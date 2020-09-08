package tr.com.obss.jss.week3spring.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jss.week3spring.model.BookDTO;
import tr.com.obss.jss.week3spring.model.BookUpdateDTO;
import tr.com.obss.jss.week3spring.service.BookService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> listAll(
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok(bookService.findAll(pageSize, pageNumber));
    }

    @GetMapping("/{param}")
    @ResponseBody
    public ResponseEntity<?> getByParam(@PathVariable String param,
                                        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                        @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {

        return ResponseEntity.ok(bookService.getByParams(param,pageSize,pageNumber));
    }

    @PostMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.save(bookDTO));
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable long id,
                                    @Valid @RequestBody BookUpdateDTO bookUpdateDTO) {
        return ResponseEntity.ok(bookService.update(id, bookUpdateDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

    //@Todo parametreyi servise geçirip orada tek fonksiyonda hallet

    @GetMapping("/{param}/{bookid}")
    @ResponseBody
    public ResponseEntity<?> getAllFavOrReads(@PathVariable String param,@PathVariable long bookid) throws NotFoundException {
        if(param.equals("favorites")){
            return ResponseEntity.ok(bookService.getAllFavsUser(bookid));
        }else if(param.equals("reads")){
            return ResponseEntity.ok(bookService.getAllReadUser(bookid));
        }else{
            throw new NotFoundException("sayfa bulunamadı");
        }
    }


}
