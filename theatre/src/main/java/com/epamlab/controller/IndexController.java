package com.epamlab.controller;

import com.epamlab.model.EventInfo;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.service.interfaces.PerformanceShowingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class IndexController {
    private static final String FULL_EVENTS_INFO = "fullEventsInfo";
    private final PerformanceShowingService<PerformanceShowing> performanceShowingService;

    public IndexController(PerformanceShowingService<PerformanceShowing> performanceShowingService) {
        this.performanceShowingService = performanceShowingService;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        List<EventInfo> fullEventsInfo = performanceShowingService.getFullEventsInfo();
        model.addAttribute(FULL_EVENTS_INFO, fullEventsInfo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("hasAdminRole", hasAdminRole);
        return "index";
    }
}
