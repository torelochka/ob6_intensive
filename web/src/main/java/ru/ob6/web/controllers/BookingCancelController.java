package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ob6.api.services.BookingService;
import ru.ob6.web.security.details.UserDetailsImpl;

@Controller
public class BookingCancelController {

    private final BookingService bookingService;

    @Autowired
    public BookingCancelController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking/reset/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        bookingService.cancelBooking(userDetails.getUser().getId(), bookingId);

        return "redirect:/profile";
    }
}
