package org.example.bookstoreappllicatiodemo.repository;

import org.example.bookstoreappllicatiodemo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
    @Query("SELECT b FROM BookEntity b WHERE b.id = :id")
    Optional<BookEntity> findBookById(@Param("id") Long id);
}

