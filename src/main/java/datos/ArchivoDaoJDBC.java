package datos;

import dominio.Archivo;
import java.sql.*;
import java.util.*;

public class ArchivoDaoJDBC {

    private static final String SQL_SELECT_VIEW1 = "SELECT id, idUser, nombre, fecha, tipoCartera, statusCarga, numRegistros"
            + " FROM archivo ";
    private static final String SQL_SELECT_BY_ID = "SELECT  id, idUser, nombre, fecha, tipoCartera, statusCarga, numRegistros"
            + " FROM archivo WHERE id = ? ";
    private static final String SQL_INSERT = "INSERT INTO archivo(idUser,nombre, fecha, tipoCartera, statusCarga, numRegistros)"
            + " VALUES (?, ?, ?, ?, ?, ?) ";
    
    //accion eliminar registros de este archivo poner statusCarga = 0:Cancelado
    private static final String SQL_UPDATE = "UPDATE archivo SET statusCarga = ? WHERE id = ?";
   // private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ? ";

    public List<Archivo> listarArchivosAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Archivo archivon = null;

        List<Archivo> archivos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_VIEW1);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCliente = rs.getInt("idUser");
                String nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                int tipoC = rs.getInt("tipoCartera");
                int statC = rs.getInt("statusCarga");
                int numR = rs.getInt("numRegistros");

                archivon = new Archivo(id,idCliente, nombre, fecha, tipoC, statC, numR);
                archivos.add(archivon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return archivos;
    }

    public int insertar(Archivo archivo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
//            ResultSet rs = null;//es para recuperar informacion
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt .setInt(1, archivo.getIdUsuario());//le agrega un parametro al statement
            stmt.setString(2, archivo.getNombre());
            stmt.setString(3, archivo.getFecha());
            stmt.setInt(4, archivo.getTipoCart());
            stmt.setInt(5, archivo.getStatusCarg());
            stmt.setInt(6, archivo.getNumRegist());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }


/*   
        public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());//le agrega un parametro al statement
            rs = stmt.executeQuery();
            rs.absolute(1);//Nos posicionamos en el primer registro devuelto

            // int idCliente = rs.getInt("id_cliente");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
        public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
//            ResultSet rs = null;//es para recuperar informacion
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            //stmt .setInt(1, cliente.getIdCliente());//le agrega un parametro al statement
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public static void main(String[] args) {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = "+clientes);
    }*/
    
}

