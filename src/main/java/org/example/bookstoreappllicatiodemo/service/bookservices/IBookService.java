package org.example.bookstoreappllicatiodemo.service.bookservices;

import org.example.bookstoreappllicatiodemo.entity.BookEntity;

import java.util.List;

public interface IBookService  {
    List<BookEntity> getAllBooks();

    BookEntity getBook(long id);

    BookEntity createBook(BookEntity bookEntity);

    BookEntity updateBook(long id, BookEntity bookEntity);

    BookEntity deleteBook(long id);
}
