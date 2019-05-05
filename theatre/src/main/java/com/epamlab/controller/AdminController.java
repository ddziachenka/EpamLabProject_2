package com.epamlab.controller;

import com.epamlab.model.EventInfo;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.service.interfaces.PerformanceShowingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class AdminController {
    private static final String FULL_EVENTS_INFO = "fullEventsInfo";
    private final PerformanceShowingService<PerformanceShowing> performanceShowingService;

    public AdminController(PerformanceShowingService<PerformanceShowing> performanceShowingService) {
        this.performanceShowingService = performanceShowingService;
    }

    @RequestMapping("/admin")
    public String getCabinet(Model model) {
        List<EventInfo> fullEventsInfo = performanceShowingService.getFullEventsInfo();
        model.addAttribute(FULL_EVENTS_INFO, fullEventsInfo);
        return "admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public RedirectView deleteOrder(@PathVariable("id") int id) {
        performanceShowingService.removePerformanceShowing(id);
        return new RedirectView("/admin");
    }
}
