/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ProblemaBD;

/**
 *
 * @author Jules
 */
public class Problema extends HttpServlet {
    
    private int idProblema;
    private int idCategoria;
    private int idUsuario;
    private String descripcion;
    private Date fecha;
    private String titulo;
    private String topico;
    
     
    public int getidProblema() {
        return idProblema;
    }
    public int getidCategoria() {
        return idCategoria;
    }
    public int getidUsuario() {
        return idUsuario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getTopico() {
        return topico;
    }
    
    public void setidProblema(int idProblema) {
        this.idProblema = idProblema;
    }
    public void setidCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setTopico(String topico) {
        this.topico = topico;
    }
    
    /**
     * 
     * @param idProblema
     * @param idCategoria
     * @param idUsuario
     * @param descripcion
     * @param titulo
     * @param fecha
     * @param topico 
     */
  public void registrarProblema(int idCategoria, int idUsuario, String descripcion, 
        String titulo, Date fecha, String topico) {
        ProblemaBD problema = new ProblemaBD();
        System.out.println("new ProblemaBD ...");
        problema.guardar(idCategoria, idUsuario, descripcion, obtenerFecha(), titulo, topico);
  }
  
 /*
  * Metodo auxiliar para obtener un tipo de la clase sql.Date y pasarlo a la base.
  */
  private java.sql.Date obtenerFecha(){
    java.util.Calendar cal = Calendar.getInstance();
    java.util.Date utilDate = new java.util.Date(); // your util date
    cal.setTime(utilDate);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);    
    java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime()); // your sql date
    return sqlDate;
  }
  
  
  public void editarProblema(int idProblema, int idCategoria, int idUsuario, String descripcion, 
        String titulo, Date fecha, String topico) {
        
      ProblemaBD problema = new ProblemaBD();
      problema.getDatos(idProblema);
      problema.editar(idProblema, idCategoria, idUsuario, descripcion, obtenerFecha(), titulo, topico);
  }

  public void borrarProblema(int idProblema, int idCategoria, int idUsuario, String descripcion, 
        String titulo, Date fecha, String topico) {
        
      ProblemaBD problema = new ProblemaBD(idProblema, idCategoria, idUsuario, descripcion, obtenerFecha(), titulo, topico);
      problema.getDatos(idProblema);
      problema.eliminar(idProblema);
  }
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String titulo = request.getParameter("titulo");
            String topico = request.getParameter("topico");
            String categoria = request.getParameter("categoria" );
            String descripcion = request.getParameter("descripcion");
            String answer = "";
            if (titulo!= null && topico!=null && categoria!=null && descripcion!=null){
                registrarProblema(1,1000,descripcion,titulo, obtenerFecha(),topico);
                answer = "Success";
            } else {
                answer = "ERROR AL GUARDAR";
            }
         //   registrarProblema();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Problema_</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Problema_ at " + request.getContextPath() + "</h1>");
            out.println("<p>"+answer+"</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
}
