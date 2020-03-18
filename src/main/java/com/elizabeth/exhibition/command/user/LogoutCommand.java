package com.elizabeth.exhibition.command.user;

import com.elizabeth.exhibition.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();

        return "/login?lang=en";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/login.jsp";
    }
}
