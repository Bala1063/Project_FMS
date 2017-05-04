/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Servlet;

import ProjectFMS.DAO.LoginDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *PRP_FMS:
 *
 * @author Aruna M,Sushmitha S.
 */
public class LoginServlet extends HttpServlet {

    public String login(String userId, String password) {
        return new LoginDAO().login(userId, password);
    }

    public String logout(String userId) {
        return new LoginDAO().logout(userId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equalsIgnoreCase("login")) {
            String userId = request.getParameter("username");
            String password = request.getParameter("password");
            String result = login(userId, password);
            if (result == null) {
                request.setAttribute("status", "invalid user name or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if (result.equalsIgnoreCase("A")) {
                request.getSession().setAttribute("userId", userId);
                Cookie c = new Cookie("userId", userId);
                response.addCookie(c);
                response.sendRedirect("AdminHomePage.jsp");
            } else if (result.equalsIgnoreCase("T")) {
                request.getSession().setAttribute("userId", userId);
                Cookie c = new Cookie("userId", userId);
                response.addCookie(c);
                response.sendRedirect("TrainerHomePage.jsp");

            } else if (result.equalsIgnoreCase("P")) {
                request.getSession().setAttribute("userId", userId);
                Cookie c = new Cookie("userId", userId);
                response.addCookie(c);
                response.sendRedirect("PMOHomePage.jsp");

            } else if (result.equalsIgnoreCase("fail")) {
                request.setAttribute("status", "Server Problem");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
           else if (result.equalsIgnoreCase("already active")) {
                request.setAttribute("status", "already active");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        }

        if (operation.equalsIgnoreCase("logout")) {
            String userId = (String) request.getSession(false).getAttribute("userId");
            String result = logout(userId);
            Cookie c = new Cookie("userId", "");
            c.setMaxAge(0);
            response.addCookie(c);
            if (result.equalsIgnoreCase("success")) {
                request.getSession().invalidate();
                response.sendRedirect("Login.jsp");
            }

        }
        if (operation.equalsIgnoreCase("submit")) {
            String userId = (String) request.getSession(false).getAttribute("userId");
            String newPassword = request.getParameter("newpassword");
            String result = new LoginDAO().changePassword(userId, newPassword);
            if (!result.equalsIgnoreCase("fail")) {
                request.setAttribute("status", "Password Changed");
                if (result.equalsIgnoreCase("T")) {

                    request.getRequestDispatcher("TrainerChangePassword.jsp").forward(request, response);
                } else if (result.equalsIgnoreCase("A")) {
                    request.getRequestDispatcher("AdminChangePassword.jsp").forward(request, response);
                } else if (result.equalsIgnoreCase("P")) {
                    request.getRequestDispatcher("PMOChangePassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("status", "Servor Problem try again later");
                if (result.equalsIgnoreCase("T")) {

                    request.getRequestDispatcher("TrainerChangePassword.jsp").forward(request, response);
                } else if (result.equalsIgnoreCase("A")) {
                    request.getRequestDispatcher("AdminChangePassword.jsp").forward(request, response);
                } else if (result.equalsIgnoreCase("P")) {
                    request.getRequestDispatcher("PMOChangePassword.jsp").forward(request, response);
                }

            }

        }
    }

}
