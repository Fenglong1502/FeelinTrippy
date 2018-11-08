/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Customer;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.NoResultException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookedActivitySessionLocal;
import session.CustomerSessionLocal;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;

/**
 *
 * @author fengl
 */
public class Controller extends HttpServlet {

    @EJB
    BookedActivitySessionLocal bookedActivitySessionLocal;
    @EJB
    CustomerSessionLocal customerSessionLocal;
    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
            /**
             * Processes requests for both HTTP <code>GET</code> and
             * <code>POST</code> methods.
             *
             * @param request servlet request
             * @param response servlet response
             * @throws ServletException if a servlet-specific error occurs
             * @throws IOException if an I/O error occurs
             */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        path = path.split("/")[1];

        switch (path) {
            case "doLogin":
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                Customer c = new Customer();
                c.setEmail(email);
                c.setPassword(encryptPassword(password));

                if (customerSessionLocal.Login(c)) {
                    HttpSession httpSession = request.getSession();
                    try {
                        c = customerSessionLocal.getCustomerByEmail(email);
                    } catch (NoResultException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    httpSession.setAttribute("user", c);

                    response.sendRedirect("../mainPage.jsp");
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User or password incorrect');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                    //response.sendRedirect("503.html");
                }
                break;

            case "doRegister":

                break;
            
               
                
            case "doFilterTrip":
                String btnType = request.getParameter("btnType");
                int myRange = Integer.parseInt(request.getParameter("myRange"));
                List<TrippyEventItem> filteredList;
                TrippyEventType tType = trippyEventTypeSessionLocal.searchTrippyEventType(btnType);

                if (btnType.equals("everything")) {
                    filteredList = trippyEventSessionLocal.searchEventListByPrice((double) myRange);
                } else {
                    filteredList = trippyEventSessionLocal.searchEventListByConditions(tType, (double) myRange);
                }

                HttpSession httpSession = request.getSession();
                // httpSession.setAttribute("user", httpSession.getAttribute("user"));    
                httpSession.setAttribute("filterList", filteredList);

                response.sendRedirect("activities.jsp");
                break;

            case "filterTrip.jsp":
                if (request.getSession().getAttribute("user") == null) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please login to start trippy');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                }

                break;
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
