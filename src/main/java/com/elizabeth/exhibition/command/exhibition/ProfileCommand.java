package com.elizabeth.exhibition.command.exhibition;

import com.elizabeth.exhibition.command.Command;
import com.elizabeth.exhibition.dao.Page;
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

public class ProfileCommand implements Command {
    final TicketService ticketService;
    final ExhibitionService exhibitionService;
    final Localization localization;

    public ProfileCommand(TicketService ticketService, ExhibitionService exhibitionService, Localization localization) {
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

        final int numberOfTickets = ticketService.getUserExhibsByUserId(userId, true).size();
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if(pageNumber < 1 || pageNumber > Math.ceil(numberOfTickets/5.)) {
            pageNumber = 1;
        }
        final Page page = new Page( (pageNumber - 1) * 5, 5 );

        List<Ticket> tickets = ticketService.getPageOfBoughtTickets(userId, page);
        Map<Long, Optional<Exhibition>> ticketsMap = new LinkedHashMap<>();
        for (Ticket ticket : tickets) {
            Optional<Exhibition> temp = exhibitionService.getExhibitionById(ticket.getExhibitionId());
            ticketsMap.put(ticket.getId(), temp);
        }

        setAttributes(request, numberOfTickets, pageNumber, ticketsMap, session);
        return "pages/myProfile.jsp";
    }

    private void setAttributes(HttpServletRequest request, int numberOfTickets, int pageNumber, Map<Long,
            Optional<Exhibition>> ticketsMap, HttpSession session) {
        request.setAttribute("ticketsMap", ticketsMap);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("numberOfTickets", numberOfTickets);
        request.setAttribute("inCart", session.getAttribute("inCart"));
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/myProfile.jsp";
    }
}
