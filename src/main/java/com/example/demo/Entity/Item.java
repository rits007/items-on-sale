package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ITEM_GLOBAL_RATING")
    private int aggregateRating;

    @Column(name = "ON_SALE")
    private boolean onSale;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    public OrderHistory order;
}
