package controlBackJDBC;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class controller {

    Conexion con2 = new Conexion();

    public void GuardaTurno(String Cadena) throws SQLException {
        Connection cn = con2.getConnection();
        PreparedStatement pps = null;
        String SQL = "";
        SQL = "INSERT INTO carga4 VALUES " + Cadena + ";";
        // System.out.println("Qwery = "+SQL);
        try {
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pps != null) {
                con2.close(pps);
            }
            if (cn != null) {
                con2.close(cn);
            }
        }   //finally catch  
    }//@endGuardaTurno

    public void GuardaPart1datosCliente(String Cadena, int opc) throws SQLException {
        Connection cn = con2.getConnection();
        PreparedStatement pps = null;
        String SQL = "";
        if (opc == 0) {
            SQL = "INSERT INTO datoscliente VALUES " + Cadena + ";";
        }
        if (opc == 1) {
            SQL = "INSERT INTO telefonos VALUES " + Cadena + ";";
        }
        try {
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la transaccion. \n opc = " + opc);
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pps != null) {
                con2.close(pps);
            }
            if (cn != null) {
                con2.close(cn);
            }
        }   //finally catch  
    }//@endGuardaPart1datosCliente

    //*** Obtener ultimo turno creado
    public int getLastdtosCli() throws SQLException {
        Connection cn = con2.getConnection();
        int idTurno = -1;
        String sql = "";
        sql = "SELECT id FROM datoscliente ORDER BY id DESC LIMIT 1;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            if (rs.next()) {
                if (rs.getRow() > 0) {
                    idTurno = rs.getInt(1);
                } else {
                    idTurno = -1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cn != null) {
                con2.close(cn);
            }
            if (rs != null) {
                con2.close(rs);
            }
            if (st != null) {
                st.close();
            }
        }
        return idTurno;
    }//@endgetLastdtosCli

    /**
     * **-- Metodo pata saber si un diablo ya fue rentado en una fecha
     */
    /*COMPRA A PROVEEDORES*/
    public boolean validaExistArchivo(String nombreArch) throws SQLException {
        Connection cn = con2.getConnection();
        boolean existe = false;
        int num = 0, i = 1;
        String sql = "SELECT '1' FROM archivo WHERE nombre = '" + nombreArch + "' ;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            if (rs.next()) {
                if (rs.getRow() > 0) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (cn != null) {
                    con2.close(cn);
                }
                if (rs != null) {
                    con2.close(rs);
                }
                if(st != null){
                    st.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return existe;
    }

    public static void main(String[] args) {
        controller cont = new controller();
        String compara = "SERVICIOS_APLICADOS_DE_COBRANZA_MERCANTIL_VD_01032021";
        try {
            System.err.println("Existe:" + cont.validaExistArchivo(compara));
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
