package com.example.ordersevice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequst {
    private List<OrderedItemsDto> orderedItemsDtos;
}
