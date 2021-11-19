package com.dio.beerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerAlreadyRegistredException extends Exception{
    public BeerAlreadyRegistredException(String name) {
        super("Beer "+name+" already registred ");
    }
}
