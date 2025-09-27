package com.microservices.bookservice.command.event;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.bookservice.command.data.Book;
import com.microservices.bookservice.command.data.BookRepository;

@Component
public class BookEventHandle {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        if (oldBook.isPresent()) {
            Book book = oldBook.get();
            book.setAuthor(event.getAuthor());
            book.setName(event.getName());
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        }
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        if (oldBook.isPresent()) {

            bookRepository.delete(oldBook.get());
        }
    }
}
