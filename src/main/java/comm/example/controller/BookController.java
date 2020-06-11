package comm.example.controller;

import comm.example.dao.BookRepository;
import comm.example.dao.PageRepository;
import comm.example.model.Book;
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

    @PostMapping("/books")
    @Transactional
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));

    }

    @PutMapping("/books/")
    @Transactional
    public ResponseEntity<Book> updateBook(@RequestBody Book book)
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
    }

    @GetMapping(value = "/all-books")
    @Transactional
    public ResponseEntity<Iterable<Book>> getBook(@RequestBody Book book)
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }


    @GetMapping(value = "/books/{bookId}")
    @Transactional
    public ResponseEntity<Optional<Book>> getByBookId(@PathVariable("bookId") int bookId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findById(bookId));
    }

    @DeleteMapping(value = "/books")
    @Transactional
    public ResponseEntity<String> deleteBook(@RequestBody Book book)
    {
        bookRepository.delete(book);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @DeleteMapping(value = "/books/{bookId}")
    @Transactional
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId") int bookId)
    {
        bookRepository.deleteById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Using BookId successfully");
    }


}
