package com.dio.beerstock.service;

import com.dio.beerstock.dto.request.BeerDTO;
import com.dio.beerstock.entity.Beer;
import com.dio.beerstock.exception.BeerAlreadyRegistredException;
import com.dio.beerstock.exception.BeerNotFoundException;
import com.dio.beerstock.exception.BeerStockExceededException;
import com.dio.beerstock.mapper.BeerMapper;
import com.dio.beerstock.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;


    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegistredException {
        verifyIsAlreadyResgitred(beerDTO.getName());
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));
        return beerMapper.toDTO(foundBeer);
    }

    public List<BeerDTO> listAll(){
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIsExists(id);
        beerRepository.deleteById(id);
    }

    private void verifyIsAlreadyResgitred(String name) throws BeerAlreadyRegistredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);
        if(optSavedBeer.isPresent()){
            throw new BeerAlreadyRegistredException(name);
        }
    }

    private Beer verifyIsExists(long id) throws BeerNotFoundException{
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));

    }

    public BeerDTO increment(long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        Beer beerToIncrementStock = verifyIsExists(id);
        int quantityAfterToIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
        if(quantityAfterToIncrement <= beerToIncrementStock.getMax()){
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
            Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new BeerStockExceededException(id, quantityToIncrement);
    }
}
