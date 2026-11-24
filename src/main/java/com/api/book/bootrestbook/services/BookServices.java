package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Component
@Service //internally uses Component
public class BookServices {

    //fake service jo data list madhe asl toch deil database madhn nhi

//    private static List<Book> list = new ArrayList<>();
//
//    static
//    {
//        list.add(new Book(12,"Java Complete Reference","XYZ"));
//        list.add(new Book(36,"Head First to Java","ABC"));
//        list.add(new Book(12983,"Think in Java ","LMN"));
//    }

    @Autowired
    private BookRepository bookRepository;

    //get all books
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Optional<Book> getBookById(int id)
    {
//        Book book = null;
//        book=list.stream().filter(e->e.getId()==id).findFirst().get();
//        return book;

            Optional<Book> book = null;
       try {
            book = bookRepository.findById(id);
       }
       catch (Exception e) {
           e.printStackTrace();
       }
         return book;
    }

    public Book addBook(Book book)
    {
        book.setId(0);// do this then it takes id properly while adding in postman
     Book result =  bookRepository.save(book);
     return result;
    }

    public void deleteBook(int bid)
    {
        //updating list keeping those who are not in id
//        list = list.stream().filter(e->e.getId()!=bid).collect(Collectors.toList());

        bookRepository.deleteById(bid);
    }

    public void updateBook(int bid, Book book)
    {
//       list = list.stream().map(b->{
//           if(b.getId()==bid){
//               b.setTitle(book.getTitle());
//               b.setAuthor(book.getAuthor());
//           }
//            return b;
//       }).collect(Collectors.toList());

        book.setId(bid); //update the book which we gave the id

        bookRepository.save(book);
    }
}
