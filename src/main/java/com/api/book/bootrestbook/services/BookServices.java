package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service //internally uses Component
public class BookServices {

    //fake service jo data list madhe asl toch deil database madhn nhi

    private static List<Book> list = new ArrayList<>();

    static
    {
        list.add(new Book(12,"Java Complete Reference","XYZ"));
        list.add(new Book(36,"Head First to Java","ABC"));
        list.add(new Book(12983,"Think in Java ","LMN"));
    }

    //get all books
    public List<Book> getBooks() {
        return list;
    }

    public Book getBookById(int id)
    {
        Book book = null;
        book=list.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }

    public Book addBook(Book book)
    {
        list.add(book);
        return book;
    }

    public void deleteBook(int bid)
    {
        //updating list keeping those who are not in id
        list = list.stream().filter(e->e.getId()!=bid).collect(Collectors.toList());
    }

    public void updateBook(int bid, Book book)
    {
       list = list.stream().map(b->{
           if(b.getId()==bid){
               b.setTitle(book.getTitle());
               b.setAuthor(book.getAuthor());
           }
            return b;
       }).collect(Collectors.toList());
    }
}
