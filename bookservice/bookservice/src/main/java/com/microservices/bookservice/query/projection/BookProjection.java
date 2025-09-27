package com.microservices.bookservice.query.projection;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.bookservice.command.data.Book;
import com.microservices.bookservice.command.data.BookRepository;
import com.microservices.bookservice.query.model.BookResponseModel;
import com.microservices.bookservice.query.queries.GetAllBookQuey;
import com.microservices.bookservice.query.queries.GetBookDetailQuery;

@Component
public class BookProjection {
    @Autowired
    BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handel(GetAllBookQuey quey) {
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> bookList = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            bookList.add(model);
        });
        return bookList;
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query) throws Exception {
        BookResponseModel bookResponseModel = new BookResponseModel();
        Book book = bookRepository.findById(query.getId())
                .orElseThrow(() -> new Exception("Not found book with BookId :" + query.getId()));
        BeanUtils.copyProperties(book, bookResponseModel);
        return bookResponseModel;
    }
}
