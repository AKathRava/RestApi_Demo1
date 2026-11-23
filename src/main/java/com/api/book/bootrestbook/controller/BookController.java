package com.api.book.bootrestbook.controller;

//import org.springframework.stereotype.Controller;
import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@Controller
public class BookController {

//    @RequestMapping(value="/books",method = RequestMethod.GET) no need when we use RestController directly we can use GetMapping
//    @ResponseBody no need to use when we write @RestController
//    @GetMapping("/books")
///*    public String*/ public Book getBook(){
//
//        Book b1 = new Book();
//        b1.setId(1);
//        b1.setTitle("Book 1");
//        b1.setAuthor("Author 1");
////      return "this is testing book first";
//        return b1;
//    }

    @Autowired
    private BookServices bookServices;

    //get books handler
    @GetMapping("/books")
    public List<Book> getBooks()
    {
        return this.bookServices.getBooks();
    }

    //get single book handler
    @GetMapping("/books/{id}")
    public  Book getBookById(@PathVariable("id") int id)
    {
        return this.bookServices.getBookById(id);
    }

    //new book handler
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book)  //it convert the json body data into java object
    {
       Book b =  this.bookServices.addBook(book);
       System.out.println(b);
        return b;
    }

    //delete book handler
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") int id)
    {
       this.bookServices.deleteBook(id);
    }

    //update book handler

    @PutMapping("/books/{id}")
    public Book udpateBook(@PathVariable("id") int id, @RequestBody Book book)
    {
        this.bookServices.updateBook(id,book);
        return book;
    }
}
