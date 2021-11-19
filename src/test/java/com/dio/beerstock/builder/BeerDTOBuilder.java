package com.dio.beerstock.builder;

import com.dio.beerstock.dto.request.BeerDTO;
import com.dio.beerstock.enums.BeerType;
import lombok.Builder;

import javax.persistence.*;

@Builder
public class BeerDTOBuilder {

    @Builder.Default
    private long id = 1L;

    @Builder.Default
    private String name = "Brahma";

    @Builder.Default
    private String brand = "Ambev";

    @Builder.Default
    private int max = 50;

    @Builder.Default
    private int quantity = 10;

    @Builder.Default
    private BeerType type = BeerType.LAGER;

    public BeerDTO toBeerDTO() {
        return new BeerDTO(id,
                name,
                brand,
                max,
                quantity,
                type);
    }

}
