package com.bijay.example.chatapp.service;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 12:07 PM
 */

public final class DynamicOrderMessage {

    public static Sort.Direction getSortingDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public static List<Sort.Order> getOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortingDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortingDirection(sort[1]), sort[0]));
        }
        return orders;
    }

}
