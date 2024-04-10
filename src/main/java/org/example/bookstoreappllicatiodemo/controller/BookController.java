package org.example.bookstoreappllicatiodemo.controller;

import org.example.bookstoreappllicatiodemo.dto.BookDTO;
import org.example.bookstoreappllicatiodemo.entity.BookEntity;
import org.example.bookstoreappllicatiodemo.repository.BookRepository;
import org.example.bookstoreappllicatiodemo.service.bookservices.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    IBookService iBookService;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<BookEntity> getAllBooks(){
        return iBookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        // Create a new BookDTO and manually map the properties from Book entity
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setAuthorName(book.getAuthorName());
        bookDTO.setBookLogo(book.getBookLogo()); // Make sure your Book entity has this field
        bookDTO.setBookPrice(Double.valueOf(book.getBookPrice()));

        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("/book/add")
    public BookEntity createBook(@RequestBody BookEntity bookEntity){
        System.out.println(bookEntity.toString());
        return iBookService.createBook(bookEntity);
    }

    @PutMapping("/books/update/{id}")
    public BookEntity updateBook(@PathVariable long id, @RequestBody BookEntity bookEntity){
        return iBookService.updateBook(id, bookEntity);
    }

    @DeleteMapping("/books/delete/{id}")
    public BookEntity deleteBook (@PathVariable  long id){
        return iBookService.deleteBook(id);
    }
}
