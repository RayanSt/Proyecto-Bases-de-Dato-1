package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import negocio.Cliente;

import oracle.jdbc.driver.OracleConnection;

import util.CaException;
import util.ServiceLocator;

/**
 * @author Brayan
 * Esta clase encapsula el acceso a la Base de Datos
 */
public class ClienteDAO {
    private Cliente cliente;
    /*
     * Constructor de la clase
     */
    public ClienteDAO(){
      cliente = new Cliente();
    }
    /**
     * Incluye una nueva fila en la tabla CAMUNICIPIO.
     * @throws CaException
     */
    public void incluirCliente() throws CaException {
      try {
      
        String strSQL = "INSERT INTO Cliente (Nombre, Apellido, fecha_nacimiento, correo_electronico, usuario, contrasena) values (?,?,?,?,?,?)";
        Connection conexion = ServiceLocator.getInstance().tomarConexion();
        PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
        prepStmt.setString(1, cliente.getNOMBRE()); 
        prepStmt.setString(2, cliente.getAPELLIDO()); 
        prepStmt.setDate(3, cliente.getFECHA_NACIMIENTO());
        prepStmt.setString(4, cliente.getCORREO_ELECTRONICO()); 
        prepStmt.setString(5, cliente.getUSUARIO());
        prepStmt.setString(6, cliente.getCONTRASENA());
        prepStmt.executeUpdate();
        prepStmt.close();
        ServiceLocator.getInstance().commit();
      } catch (SQLException e) {
           throw new CaException( "ClienteDAO", "No pudo agregar al cliente"+ e.getMessage());
      }  finally {
         ServiceLocator.getInstance().liberarConexion();
      }
      
    }
    
    public void modificarCliente(){
      
    }
    
    public void eliminarCliente(){
      
    }
    
    public void buscarCliente() throws CaException{
      try{
         String strSQL = "SELECT Nombre, Apellido, fecha_nacimiento, correo_electronico, usuario, contrasena FROM Cliente WHERE USUARIO = ?";
         Connection conexion = ServiceLocator.getInstance().tomarConexion();
          PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
          prepStmt.setString(1,cliente.getUSUARIO());
          ResultSet rs = prepStmt.executeQuery();
          while (rs.next()){
              cliente.setNOMBRE(rs.getNString(1));
              cliente.setAPELLIDO(rs.getNString(2));
              cliente.setFECHA_NACIMIENTO(rs.getDate(3));
              cliente.setCORREO_ELECTRONICO(rs.getNString(4));
              cliente.setUSUARIO(rs.getNString(5));
              cliente.setCONTRASENA(rs.getNString(6));
          }
      }
      catch(SQLException e){
        throw new CaException("ClienteDAO", "No pudo recuperar al cliente "+ e.getMessage());
      }
      
    }
    

    public void actualizarCliente() throws CaException {
        try{
         String strSQL = "SELECT Nombre, Apellido, fecha_nacimiento, correo_electronico, usuario, contrasena FROM Cliente WHERE usuario = ?";
         Connection conexion = ServiceLocator.getInstance().tomarConexion();
          PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
          prepStmt.setString(1,cliente.getUSUARIO());
          ResultSet rs = prepStmt.executeQuery();
          while (rs.next()){
            cliente.setNOMBRE(rs.getNString(1));
              cliente.setAPELLIDO(rs.getNString(2));
              cliente.setFECHA_NACIMIENTO(rs.getDate(3));
              cliente.setCORREO_ELECTRONICO(rs.getNString(4));
              cliente.setUSUARIO(rs.getNString(5));
              cliente.setCONTRASENA(rs.getNString(6));
          }
      }
      catch(SQLException e){
        throw new CaException("ClienteDAO", "No pudo recuperar al cliente "+ e.getMessage());
      }
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
