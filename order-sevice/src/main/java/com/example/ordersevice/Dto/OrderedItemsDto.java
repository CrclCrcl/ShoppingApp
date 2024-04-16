package com.example.ordersevice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderedItemsDto {
    private Long id;
    private String uniCode;
    private BigDecimal price;
    private Integer quantity;
}
