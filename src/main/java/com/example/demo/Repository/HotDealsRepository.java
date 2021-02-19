package com.example.demo.Repository;

import com.example.demo.Entity.HotDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotDealsRepository extends JpaRepository<HotDeals, Integer> {
    //List<OrderHistory> findAllByUserId(String userId);
}
