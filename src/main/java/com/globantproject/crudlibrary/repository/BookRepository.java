package com.globantproject.crudlibrary.repository;

import com.globantproject.crudlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    Optional<Book>  findStudentByAuthor(String Author);
}
