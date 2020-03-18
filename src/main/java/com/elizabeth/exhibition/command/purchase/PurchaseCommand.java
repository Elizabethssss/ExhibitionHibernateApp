package com.elizabeth.exhibition.command.purchase;

import com.elizabeth.exhibition.command.Command;
import com.elizabeth.exhibition.domain.Exhibition;
import com.elizabeth.exhibition.domain.Ticket;
import com.elizabeth.exhibition.domain.User;
import com.elizabeth.exhibition.service.ExhibitionService;
import com.elizabeth.exhibition.service.Localization;
import com.elizabeth.exhibition.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PurchaseCommand implements Command {
    final TicketService ticketService;
    final ExhibitionService exhibitionService;
    final Localization localization;

    public PurchaseCommand(TicketService ticketService, ExhibitionService exhibitionService, Localization localization) {
        this.ticketService = ticketService;
        this.exhibitionService = exhibitionService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final long userId = user.get().getId();
        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        final List<Ticket> userTickets = ticketService.getUserExhibsByUserId(userId, false);
        Map<Long, Optional<Exhibition>> ticketsMap = new LinkedHashMap<>();
        long totalPrice = 0;
        for (Ticket ticket : userTickets) {
            Optional<Exhibition> temp = exhibitionService.getExhibitionById(ticket.getExhibitionId());
            ticketsMap.put(ticket.getId(), temp);
            totalPrice += temp.get().getPrice();
        }

        setAttributes(request, session, user, ticketsMap, totalPrice);
        return "pages/purchase.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/purchase.jsp";
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, Optional<User> user, Map<Long,
            Optional<Exhibition>> ticketsMap, long totalPrice) {
        request.setAttribute("user", user);
        request.setAttribute("ticketsMap", ticketsMap);
        session.setAttribute("totalPrice", totalPrice);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("inCart", session.getAttribute("inCart"));
    }
}
