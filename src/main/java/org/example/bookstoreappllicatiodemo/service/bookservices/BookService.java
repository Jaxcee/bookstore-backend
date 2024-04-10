package org.example.bookstoreappllicatiodemo.service.bookservices;

import org.example.bookstoreappllicatiodemo.entity.BookEntity;
import org.example.bookstoreappllicatiodemo.exception.BookNotFoundException;
import org.example.bookstoreappllicatiodemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity getBook(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found by that id"));
    }

    @Override
    public BookEntity createBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public BookEntity updateBook(long id, BookEntity bookEntity) {
        BookEntity existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found by that id"));
        existingBook.setBookName(bookEntity.getBookName());
        existingBook.setAuthorName(bookEntity.getAuthorName());
        existingBook.setBookDescription(bookEntity.getBookDescription());
        existingBook.setBookPrice(bookEntity.getBookPrice());
        existingBook.setBookLogo(bookEntity.getBookLogo());
        existingBook.setBookQuantity(bookEntity.getBookQuantity());
        bookRepository.save(existingBook);
        return existingBook;
    }

    @Override
    public BookEntity deleteBook(long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found by that id"));
        bookRepository.delete(bookEntity);
        return bookEntity;
    }
}
