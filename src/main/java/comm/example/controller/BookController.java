package comm.example.controller;

import comm.example.dao.BookRepository;
import comm.example.dao.PageRepository;
import comm.example.model.Book;
import comm.example.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@EnableTransactionManagement
@EnableAutoConfiguration
public class BookController {
    private BookRepository bookRepository;
    private PageRepository pageRepository;

    @Autowired
    public BookController(BookRepository bookRepository, PageRepository pageRepository) {
        this.bookRepository = bookRepository;
        this.pageRepository = pageRepository;
    }
    @GetMapping(value = {"/books"})
    public ResponseEntity<Iterable<Book>> getAllBooks()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("bookId") int bookId)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(bookRepository.findById(bookId));
    }
    @PostMapping("/books")
    @Transactional
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));

    }
    @DeleteMapping("/books/{bookId}")
    @Transactional
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId){
    bookRepository.deleteById(bookId);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }
    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book)
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
    }

}
