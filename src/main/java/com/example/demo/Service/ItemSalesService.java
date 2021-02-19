package com.example.demo.Service;

import com.example.demo.Entity.HotDeals;
import com.example.demo.Entity.User;
import com.example.demo.Repository.HotDealsRepository;
import com.example.demo.Repository.ItemsRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemSalesService {

    private static int DEALS_FROM_WISH_LIST = 4;
    private static int DEALS_FROM_ORDER_HISTORY = 2;
    private static int MAX_ITEM_IN_RECOMMENDED = 8;


    @Autowired
    UserRepository userRepository;

    @Autowired
    HotDealsRepository hotDealsRepository;

    @Autowired
    ItemsRepository itemsRepository;

    public String getItemsOnSale(String userId) {

        Optional<User> user = userRepository.findById(userId);
        List<String> wishList;
        Set<String> recommendedList = new HashSet<>();

        if (user.isPresent()) {
            if (doesDealExist(user))
                return user.get().getHotDeals().getItemList();

            addItemsFromWishList(user, recommendedList);
            addItemsFromHistory(user, recommendedList);
        }
        addItemsFromOtherReviews(recommendedList);

        System.out.println("Recommended List: " + recommendedList);
        if (user.isPresent()) {
            saveHotDeals(user, recommendedList);
        }
        return recommendedList.toString();
    }

    private void saveHotDeals(Optional<User> user, Set<String> recommendedList) {
        HotDeals h = new HotDeals();
        h.setCreatedOn(LocalDate.now());
        h.setUser(user.get());
        h.setItemList(recommendedList.toString());

        hotDealsRepository.save(h);
    }

    private void addItemsFromOtherReviews(Set<String> recommendedList) {
        int numberOfDealsFromReviews;
        numberOfDealsFromReviews = MAX_ITEM_IN_RECOMMENDED - recommendedList.size();
        List<String> items = itemsRepository.findAll()
                .stream()
                .filter(item -> item.isOnSale())
                .sorted((i1, i2) -> i2.getAggregateRating() - i1.getAggregateRating())
                .limit(numberOfDealsFromReviews)
                .map(item -> String.valueOf(item.getId()))
                .collect(Collectors.toList());

        System.out.println("List from items Review:" + items);

        recommendedList.addAll(items);
    }

    public void addItemsFromHistory(Optional<User> user, Set<String> recommendedList) {
        int recommendedListSize = recommendedList.size();
        int numberOfDealsFromHistory = DEALS_FROM_ORDER_HISTORY;

        if (recommendedListSize < DEALS_FROM_WISH_LIST) {
            int dif = DEALS_FROM_WISH_LIST - recommendedListSize;

            if (dif % 2 == 0) {
                numberOfDealsFromHistory += dif / 2;
            } else {
                numberOfDealsFromHistory += dif / 2 + 2;
            }
        }

        List<String> orderHistory = user.get().getOrderHistory()
                .stream()
                .filter(orderHistory1 -> orderHistory1.getItem().isOnSale())
                .sorted((o1, o2) -> o2.getUserRating() - o1.getUserRating())
                .limit(numberOfDealsFromHistory).map(orderHistory1 -> String.valueOf(orderHistory1.getItem().getId()))
                .collect(Collectors.toList());

        System.out.println("List from orderHistory:" + orderHistory);

        recommendedList.addAll(orderHistory);
    }

    public void addItemsFromWishList(Optional<User> user, Set<String> recommendedList) {
        List<String> wishList;
        wishList = Arrays.asList(user.get().getWishList().itemList.split(","));
        Random rand = new Random();
        for (int i = 0; i < DEALS_FROM_WISH_LIST; i++) {
            recommendedList.add(wishList.get(rand.nextInt(wishList.size())));
        }
    }

    public boolean doesDealExist(Optional<User> user) {
        if(null!= user.get().getHotDeals()) {
            LocalDate date = user.get().getHotDeals().getCreatedOn();
            if(date.isEqual(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }
}
