package com.epamlab.service.interfaces;

import com.epamlab.model.EventInfo;
import com.epamlab.model.Order;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.model.User;

import java.util.List;

public interface OrderService<T> {

    void addOrder(T order);

    void updateOrder(T order, int id);

    void removeOrder(int id);

    T getOrderById(int id);

    List<T> getOrderByIdUser(int id);

    List<T> getListOrders();

    int getSumOrders(List<Order> orders);

    List<Order> getNotPaidOrders(List<EventInfo> fullEventsInfo, List<PerformanceShowing> performanceShowings,
                                 UserService<User> userService);

    Boolean confirmOrders();

    void moveOrderInBasket(PerformanceShowing performanceShowing, int count, int id, Order order,
                           UserService<User> userServiceImpl, OrderService<Order> orderService,
                           PerformanceShowingService<PerformanceShowing> performanceShowingService);
}