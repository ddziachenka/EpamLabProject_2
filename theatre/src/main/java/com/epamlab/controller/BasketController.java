package com.epamlab.controller;

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
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BasketController {
    private static final String ORDERS = "orders";
    private static final String TOTAL = "total";
    private static final String SUCCESS = "success";
    private static final String MESSAGE_PAYMENT_SUCCESSFUL = "Payment was successful";
    private static final int SUM_ZERO = 0;
    private final UserService<User> userServiceImpl;
    private final PerformanceShowingService<PerformanceShowing> performanceShowingService;
    private final OrderService<Order> orderService;

    public BasketController(UserService<User> userServiceImpl, PerformanceShowingService<PerformanceShowing> performanceShowingService, OrderService<Order> orderService) {
        this.userServiceImpl = userServiceImpl;
        this.performanceShowingService = performanceShowingService;
        this.orderService = orderService;
    }

    @RequestMapping("/basket")
    public String getBasket(Model model) {
        List<EventInfo> fullEventsInfo = performanceShowingService.getFullEventsInfo();
        List<PerformanceShowing> performanceShowings = performanceShowingService.getListPerformanceShowings();
        List<Order> orders = orderService.getNotPaidOrders(fullEventsInfo, performanceShowings, userServiceImpl);
        int sum = orderService.getSumOrders(orders);
        model.addAttribute(ORDERS, orders);
        model.addAttribute(TOTAL, sum);
        return "basket";
    }

    @RequestMapping("/basket/order")
    public String doOrder(Model model) {
        Boolean flagSuccess = orderService.confirmOrders();
        model.addAttribute(TOTAL, SUM_ZERO);
        if (!flagSuccess) {
            model.addAttribute(SUCCESS, MESSAGE_PAYMENT_SUCCESSFUL);
        }
        return "basket";
    }

    @RequestMapping("/basket/delete/{id}")
    public RedirectView deleteOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrderById(id);
        PerformanceShowing performanceShowing = performanceShowingService.getPerformanceShowing(order.getIdPerformanceShowing());
        performanceShowing.setTickets(performanceShowing.getTickets() + order.getCountTickets());
        performanceShowingService.updatePerformanceShowing(performanceShowing, order.getIdPerformanceShowing());
        orderService.removeOrder(id);
        return new RedirectView("/basket");
    }
}
