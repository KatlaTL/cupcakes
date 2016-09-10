/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.DBHandler;
import entity.bottom;
import entity.invoice;
import entity.topping;
import entity.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "cupcakesServle", urlPatterns = {"/cupcakesServle"})
public class cupcakesServle extends HttpServlet {

    private static DBHandler DBH = DBHandler.getInstance();
    private List<invoice> invoice = new ArrayList();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
            user u = null;
            if(request.getSession().getAttribute("user") != null) {
                List<user> userList = (List<user>)request.getSession().getAttribute("user");
                u = userList.get(0);
            }
            if(invoice != null && u != null) {
                
                double totalPrice = 0;
                for (invoice i : invoice) {
                    totalPrice = i.getTotalPrice() + totalPrice;
                }
                DBH.buyOrder(invoice, u, totalPrice);
                List<topping> toppingList = DBH.getToppings();
                List<bottom> bottomList = DBH.getBottoms();
                request.setAttribute("topping", toppingList);
                request.setAttribute("bottom", bottomList);
                request.setAttribute("succes", "");
                
                List<user> user = DBH.getLoggedInUser(u);                   
                if(!user.isEmpty()) {
                    request.getSession().setAttribute("user", user);
                }
                
                request.getRequestDispatcher("cupcakes.jsp").forward(request, response);
            }
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
        String origin = request.getParameter("origin");
        switch(origin) {
            case "Add to cart":
                String topping = request.getParameter("topping").split(",")[0];
                double topPrice = Double.parseDouble(request.getParameter("topping").split(",")[1]);
                String bottom = request.getParameter("bottom").split(",")[0];
                double botPrice = Double.parseDouble(request.getParameter("bottom").split(",")[1]);
                invoice.add(new invoice(topping, bottom, (topPrice + botPrice)));
                break;
            case "removeFromCart":
                int invoiceId = Integer.parseInt(request.getParameter("remove"));
                invoice.remove(invoiceId);
                break;  
        }
        request.getSession().setAttribute("invoice", invoice);

        List<topping> toppingList = DBH.getToppings();
        List<bottom> bottomList = DBH.getBottoms();
        request.setAttribute("topping", toppingList);
        request.setAttribute("bottom", bottomList);
        request.getRequestDispatcher("cupcakes.jsp").forward(request, response);
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
