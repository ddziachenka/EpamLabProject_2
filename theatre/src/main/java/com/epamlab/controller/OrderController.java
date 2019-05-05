package com.epamlab.controller;

import com.epamlab.exception.CountException;
import com.epamlab.model.EventInfo;
import com.epamlab.model.Order;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.model.User;
import com.epamlab.service.interfaces.OrderService;
import com.epamlab.service.interfaces.PerformanceShowingService;
import com.epamlab.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    private static final String EVENT = "event";
    private final UserService<User> userServiceImpl;
    private final PerformanceShowingService<PerformanceShowing> performanceShowingService;
    private final OrderService<Order> orderService;
    private final Order order;

    public OrderController(UserService<User> userServiceImpl, PerformanceShowingService<PerformanceShowing> performanceShowingService,
                           OrderService<Order> orderService, Order order) {
        this.userServiceImpl = userServiceImpl;
        this.performanceShowingService = performanceShowingService;
        this.orderService = orderService;
        this.order = order;
    }

    @RequestMapping("/order/{id}")
    public String newOrder(@PathVariable(name = "id") int id, @RequestParam("count") int count, Model model) throws CountException {
        PerformanceShowing performanceShowing = performanceShowingService.getPerformanceShowing(id);
        orderService.moveOrderInBasket(performanceShowing, count, id, order, userServiceImpl, orderService, performanceShowingService);
        EventInfo event = performanceShowingService.getEventInfoByIdFromEventsInfoList(id);
        model.addAttribute(EVENT, event);
        return "event";
    }
}





