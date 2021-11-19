package com.dio.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerStockExceededException extends Exception{
    public BeerStockExceededException(long id, int quantity) {
        super("Quantity="+quantity+" for Beer whith id="+id+" Exceede Stock Max Limit");
    }
}
