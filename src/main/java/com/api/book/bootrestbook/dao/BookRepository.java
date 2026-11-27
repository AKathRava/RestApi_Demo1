package com.api.book.bootrestbook.dao;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface BookRepository extends JpaRepository<Book,Integer> {

    public Book findBookById(int id);

    List<Book> id(int id);
}