package com.example.ordersevice.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;
    private String numbofOrder;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedItems> orderedItems;
}

