package com.epamlab.controller;

import com.epamlab.model.EventInfo;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.service.interfaces.PerformanceShowingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {
    private static final String EVENT = "event";
    private final PerformanceShowingService<PerformanceShowing> performanceShowingService;

    public EventController(PerformanceShowingService<PerformanceShowing> performanceShowingService) {
        this.performanceShowingService = performanceShowingService;
    }

    @RequestMapping("event/{id}")
    public String getProduct(@PathVariable("id") int id, Model model) {
        EventInfo event = performanceShowingService.getEventInfoByIdFromEventsInfoList(id);
        model.addAttribute(EVENT, event);
        return "event";
    }
}
