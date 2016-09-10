/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.DBHandler;
import entity.bottom;
import entity.topping;
import entity.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asger
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {
    
    private static DBHandler DBH = DBHandler.getInstance();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hidden = request.getParameter("hidden");
        switch(hidden) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                List<user> user = DBH.loginCheck(username, password);    
                
                if(!user.isEmpty()) {
                    request.getSession().setAttribute("user", user);
                    
                    List<topping> toppingList = DBH.getToppings();
                    List<bottom> bottomList = DBH.getBottoms();
                    request.setAttribute("topping", toppingList);
                    request.setAttribute("bottom", bottomList);
                    request.getRequestDispatcher("cupcakes.jsp").forward(request, response);
                    
                } else {
                    response.sendRedirect("index.jsp?invalid");
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
