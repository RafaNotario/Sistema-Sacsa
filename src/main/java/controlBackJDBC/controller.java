
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
        SQL = "INSERT INTO carga4 VALUES "+Cadena+";";
       // System.out.println("Qwery = "+SQL);
       try {
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pps != null) {
                    pps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }   //finally catch  
    }//@endGuardaTurno

        public void GuardaPart1datosCliente(String Cadena, int opc) throws SQLException  {
        Connection cn = con2.getConnection();
        PreparedStatement pps = null;
        String SQL = "";
        if(opc == 0)
            SQL = "INSERT INTO datoscliente VALUES "+Cadena+";";
        if(opc == 1)
            SQL = "INSERT INTO telefonos VALUES "+Cadena+";";
       // System.out.println("Qwery = "+SQL);
       try {
           
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la transaccion. \n opc = "+opc);
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pps != null) {
                    pps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }   //finally catch  
    }//@endGuardaPart1datosCliente
        
           //*** Obtener ultimo turno creado
    public int getLastdtosCli() throws SQLException{
            Connection cn = con2.getConnection();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM datoscliente ORDER BY id DESC LIMIT 1;";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idTurno = rs.getInt(1);
                    }else{
                         idTurno = -1;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idTurno;
    }//@endgetLastdtosCli
    
}
