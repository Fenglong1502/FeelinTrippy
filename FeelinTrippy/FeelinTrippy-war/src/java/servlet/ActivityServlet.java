/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.TrippyEventItem;
import entity.TrippyEventType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.RecommendedActivitySessionLocal;
import session.TrippyEventSessionLocal;
import session.TrippyEventTypeSessionLocal;

/**
 *
 * @author Congx2
 */
@WebServlet(name = "ActivityServlet")
public class ActivityServlet extends HttpServlet {

    @EJB
    RecommendedActivitySessionLocal recommendedActivitySessionLocal;
    @EJB
    TrippyEventTypeSessionLocal trippyEventTypeSessionLocal;
    @EJB
    TrippyEventSessionLocal trippyEventSessionLocal;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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

        Long Id = Long.parseLong(request.getParameter("Id"));
//        Activity activity = managedbean.getActivity(id);
        /* response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActivityServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActivityServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
        //processRequest(request, response);
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
        httpSession.setAttribute("user", httpSession.getAttribute("user"));    
        httpSession.setAttribute("filterList", filteredList);
        
        response.sendRedirect("activities.jsp");
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
