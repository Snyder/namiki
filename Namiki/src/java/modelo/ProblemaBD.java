package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Administrador
 */
public class ProblemaBD {
    
    private int idProblema;
    private int idCategoria;
    private int idUsuario;
    public String descripcion;
    public Date fecha;
    public String titulo;
    public String topico;
    public BaseDatos base;
 
   
    /**
     * Constructor por default
     */
   public ProblemaBD() {
       this.base = new BaseDatos();
   }
   
   public ProblemaBD(int id_problema,int id_categoria,int id_usuario,String descripcion, 
           Date fecha,String titulo, String topico) {
       this.idProblema = id_problema;
       this.idCategoria = id_categoria;
       this.idUsuario = id_usuario;
       this.descripcion = descripcion;
       this.fecha = fecha;
       this.titulo = titulo;
       this.topico = topico;
       this.base = new BaseDatos();
   }
   
    public void guardar(int idCategoria, int idUsuario, 
                String descripcion,Date fecha,String titulo, String topico){
        base.conectar();
        String problema= "INSERT INTO problema (idcategoria,idusuario,descripcion,fecha,titulo,topico) VALUES('"
                                +idCategoria+"','"+idUsuario+"','"+descripcion+"','"
                                +fecha+"','"+titulo+"','"+topico+"')";
        System.out.println(problema);
        System.out.println("Guandando datos");
        base.query(problema);
    }
    
    /**
     * 
     * @param idProblema Int
     */
    public void eliminar(int idProblema){
        base.conectar();
        String eliminar = "DELETE FROM problema WHERE idproblema = "+idProblema;
        base.query(eliminar);
    }

    /**
     * 
     * @param idProblema Int
     */
    public void getDatos(int idProblema){
        base.conectar();
        String consulta = "SELECT * FROM problema WHERE idproblema = " +idProblema;
        base.query(consulta); 
    }
   
    public void editar(int idProblema,int idCategoria, int idUsuario, 
            String descripcion,Date fecha,String titulo, String topico){
        base.conectar();
        String update = "UPDATE problema SET descripcion "+descripcion+"WHERE idproblema = " +idProblema
                + " and idusuario = "+idUsuario+ " and idcategoria = " +idCategoria 
                + " and descripcion = "+descripcion+ " and fecha = " 
                +fecha+ " and titulo = "+titulo+ "and topico = " +topico;
        base.query(update);
    }
    
    public String[][] tablaCompleta() {
        base.conectar();
        int numRows = 0;
        ResultSet cont = base.queryRS("SELECT COUNT(*) numRows FROM problema");
        try {
            if(cont.next()){
                numRows = cont.getInt("numRows");
            } else {
                System.out.println("Error al contar los registros");
                numRows = 0;
            }
        } catch (SQLException e) {}
        
        ResultSet rs = base.queryRS("Select * FROM problema");
        String[][] res = new String[numRows][7];
        int actual = 0;
        try {
            while(cont.next()){
                res[actual][1] = rs.getString(1);
                res[actual][2] = rs.getString(2);
                res[actual][3] = rs.getString(3);
                res[actual][4] = rs.getString(4);
                res[actual][5] = rs.getString(5);
                res[actual][6] = rs.getString(6);
                res[actual][7] = rs.getString(7);
                actual++;
            }
        } catch (SQLException e) {}
        return res;
    }
}