package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderHistory {
    @Id
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false)
    public User user;

    @Column(name = "USER_RATING")
    int userRating;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "order")
    public Item item;
}
