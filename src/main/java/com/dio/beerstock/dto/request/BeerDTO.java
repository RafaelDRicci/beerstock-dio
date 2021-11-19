package com.dio.beerstock.dto.request;

import com.dio.beerstock.enums.BeerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDTO {

    private long id;

    @NotNull
    @Size(min=2, max=200)
    private String name;

    @NotNull
    @Size(min=2, max=200)
    private String brand;

    @NotNull
    @Max(500)
    private int max;

    @NotNull
    @Max(100)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BeerType type;



}
