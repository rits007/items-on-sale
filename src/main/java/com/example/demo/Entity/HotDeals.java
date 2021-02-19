package com.example.demo.Entity;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HOT_DEALS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Immutable
public class HotDeals {

    @Id
    int id;

    @Column(name = "ITEM_LIST")
    String itemList;

    @Column(name = "CREATED_DATE")
    LocalDate createdOn;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;
}
