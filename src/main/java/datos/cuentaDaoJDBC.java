package datos;

//import dominio.Cliente;
import dominio.cuenta;
import java.sql.*;
import java.util.*;

public class cuentaDaoJDBC {

    private static final String SQL_SELECT = "SELECT id, numCuenta,ssolicitud_OT,nombre,status, IF(cartera =0,'HEAT','OFSC') AS cartera"
            + " FROM datoscliente WHERE id >= 100274 AND id <= 100300 limit 50";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo"
            + " FROM cliente WHERE id_cliente = ? ";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) "
            + " VALUES (?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellido=?,email=?,telefono=?,saldo=? "
            + "WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ? ";

    public List<cuenta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cuenta cuenta = null;

        List<cuenta> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("id");
                String nombre = rs.getString("numCuenta");
                String apellido = rs.getString("ssolicitud_OT");
                String email = rs.getString("nombre");
                String telefono = rs.getString("status");
                String cartera = rs.getString("cartera");

                cuenta = new cuenta(idCliente, nombre, apellido, email, telefono,cartera);
                clientes.add(cuenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
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

    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
//            ResultSet rs = null;//es para recuperar informacion
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //stmt .setInt(1, cliente.getIdCliente());//le agrega un parametro al statement
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
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

    public int eliminar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
//            ResultSet rs = null;//es para recuperar informacion
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
*/

    public static void main(String[] args) {
        List<cuenta> clientes = new cuentaDaoJDBC().listar();
        System.out.println("cuentas = "+clientes);
    }

}
