package com.example.demo.Entity;

import com.example.demo.Entity.User;
import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "WISH_LIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Immutable
public class WishList {
    @Id
    int id;

    @Column(name="ITEM_LIST")
    public String itemList;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User user;
}
