package com.dio.beerstock.controller;

import com.dio.beerstock.dto.request.BeerDTO;
import com.dio.beerstock.dto.request.QuantityDTO;
import com.dio.beerstock.entity.Beer;
import com.dio.beerstock.exception.BeerAlreadyRegistredException;
import com.dio.beerstock.exception.BeerNotFoundException;
import com.dio.beerstock.exception.BeerStockExceededException;
import com.dio.beerstock.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController{

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegistredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> listBeers(){
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(@PathVariable Long id,@RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }

}
