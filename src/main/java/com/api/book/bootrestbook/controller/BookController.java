package com.api.book.bootrestbook.controller;

//import org.springframework.stereotype.Controller;
import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> list= bookServices.getBooks();

        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    //get single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") int id) {
        try {
            Optional<Book> b = bookServices.getBookById(id);

            if (b == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //new book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)  //it convert the json body data into java object
    {
        try {
            Book b =  this.bookServices.addBook(book);
            System.out.println(b);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        try {
            this.bookServices.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update book handler

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> udpateBook(@PathVariable("id") int id, @RequestBody Book book)
    {
        try {
            this.bookServices.updateBook(id, book);
            return ResponseEntity.ok().body(book);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}