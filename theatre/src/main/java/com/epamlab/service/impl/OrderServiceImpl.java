package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.OrderDao;
import com.epamlab.exception.CountException;
import com.epamlab.model.EventInfo;
import com.epamlab.model.Order;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.model.User;
import com.epamlab.service.interfaces.OrderService;
import com.epamlab.service.interfaces.PerformanceShowingService;
import com.epamlab.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService<Order> {
    private static final String MESSAGE_COUNT_EXCEPTION = "Sorry, but you ordered tickets more than we have :(";
    private final OrderDao<Order> dao;
    private final UserService<User> userServiceImpl;

    public OrderServiceImpl(OrderDao<Order> dao, UserService<User> userServiceImpl) {
        this.dao = dao;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void addOrder(Order order) {
        this.dao.add(order);
    }

    @Override
    public void updateOrder(Order order, int id) {
        this.dao.update(order, id);
    }

    @Override
    public void removeOrder(int id) {
        this.dao.remove(id);
    }

    @Override
    public Order getOrderById(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<Order> getOrderByIdUser(int id) {
        return this.dao.getByIdUser(id);
    }

    @Override
    public List<Order> getListOrders() {
        return this.dao.getListObjects();
    }

    @Override
    public int getSumOrders(List<Order> orders) {
        int sum = 0;
        for (Order order : orders) {
            sum += order.getCountTickets() * order.getPrice();
        }
        return sum;
    }

    @Override
    public List<Order> getNotPaidOrders(List<EventInfo> fullEventsInfo, List<PerformanceShowing> performanceShowings, UserService<User> userService) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUser(auth.getName());
        List<Order> listOrders = getOrderByIdUser(user.getId());
        List<Order> orders = new ArrayList<>();
        listOrders.forEach(i -> {
            if (!i.isPay()) {
                orders.add(i);
            }
        });
        for (Order orderCurrent : orders) {
            for (int j = 0; j < performanceShowings.size(); j++) {
                if (orderCurrent.getIdPerformanceShowing() == fullEventsInfo.get(j).getIdPerformanceShowing()) {
                    orderCurrent.setName(fullEventsInfo.get(j).getNamePerformance());
                    orderCurrent.setPrice(fullEventsInfo.get(j).getPrice());
                }
            }
        }
        return orders;
    }

    @Override
    public Boolean confirmOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.getUser(auth.getName());
        List<Order> listOrders = getOrderByIdUser(user.getId());
        List<Order> orders = new ArrayList<>();
        listOrders.forEach(i -> {
            if (!i.isPay()) {
                orders.add(i);
            }
        });
        orders.forEach(i -> {
            i.setPay(true);
            updateOrder(i, i.getId());
        });
        return orders.isEmpty();
    }

    @Override
    public void moveOrderInBasket(PerformanceShowing performanceShowing, int count, int id, Order order, UserService<User>
            userServiceImpl, OrderService<Order> orderService, PerformanceShowingService<PerformanceShowing> performanceShowingService) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (performanceShowing.getTickets() >= count) {
            order.setIdPerformanceShowing(id);
            order.setPay(false);
            String username = auth.getName();
            User user = userServiceImpl.getUser(username);
            order.setIdUser(user.getId());
            order.setCountTickets(count);
            orderService.addOrder(order);
            performanceShowing.setTickets(performanceShowing.getTickets() - count);
            performanceShowingService.updatePerformanceShowing(performanceShowing, id);
        } else {
            throw new CountException(MESSAGE_COUNT_EXCEPTION);
        }
    }
}
