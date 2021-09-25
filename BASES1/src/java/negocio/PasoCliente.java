package negocio;

import datos.ClienteDAO;

import util.CaException;

/**
 * Clase controladora de la aplicaci�n
 * @author Brayan
 */
public class  PasoCliente{
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public PasoCliente() {
         clienteDAO= new ClienteDAO();
    }
    
   /**
     * Invoca el m�todo del DAO para incluir al cliente
     * @throws CaException
     */
    public void incluirCliente() throws CaException {
      clienteDAO.incluirCliente();
    }


    public Cliente getCliente() {
        return clienteDAO.getCliente();
    }
    
    
}