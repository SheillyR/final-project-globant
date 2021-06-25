package com.globantproject.crudlibrary.repository;

import com.globantproject.crudlibrary.model.Book;
import com.globantproject.crudlibrary.model.State;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.author = ?1 AND b.title =?2")
    Optional<Book>  findBookByAuthorAndTitle(String Author, String Title);

    @Query("SELECT b FROM Book b WHERE b.state = ?1")
    List<Book> findBooksByState(State state, Sort sort);
}
