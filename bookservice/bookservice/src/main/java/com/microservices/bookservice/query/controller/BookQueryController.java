package com.microservices.bookservice.query.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bookservice.query.model.BookResponseModel;
import com.microservices.bookservice.query.queries.GetAllBookQuey;
import com.microservices.bookservice.query.queries.GetBookDetailQuery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<BookResponseModel> getAllBook() {
        GetAllBookQuey getAllBookQuey = new GetAllBookQuey();
        List<BookResponseModel> result = queryGateway.query(getAllBookQuey,
                ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        return result;
    }

    @GetMapping("/{bookId}")
    public BookResponseModel getDetailBook(@PathVariable String bookId) {
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
    }

}
