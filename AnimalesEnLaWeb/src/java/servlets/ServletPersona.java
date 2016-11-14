/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Persona;
import generados.InfoPersona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registrameanimal.Factory;
import registrameanimal.IControladorPersona;

/**
 *
 * @author emi
 */
public class ServletPersona extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)            throws ServletException, IOException {
        RequestDispatcher despachador;
        response.setContentType("text/html;charset=UTF-8");
        
        Factory f = new Factory();
        IControladorPersona icp;

        String comando = request.getParameter("comando");

        if (comando != null && comando.equals("verPerfil")) {
            String id = request.getParameter("idPersona");
            request.setAttribute("persona", id);
            despachador = request.getRequestDispatcher("personas/ver-perfil.jsp");
            despachador.forward(request, response);
        } else {
//            icp = f.getIControladorPersona();
//            Collection<Persona> personas = icp.GetPersonas();
            List<InfoPersona> personas = getPeople();

            request.setAttribute("personas", personas);
            request.getRequestDispatcher("personas/MostrarPersonas.jsp").forward(request, response);
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

    private static java.util.List<generados.InfoPersona> getPeople() {
        generados.SalutatorService service = new generados.SalutatorService();
        generados.Salutator port = service.getSalutatorPort();
        return port.getPeople();
    }

}
