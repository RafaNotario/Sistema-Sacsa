package controlBackJDBC;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.err.println("Error GuardaPart1datosCliente:" + ex.getMessage());
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
            System.err.println("Error GuardaPart1datosCliente:" + ex.getMessage());
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

    public void GuardaPart1datosClienteAux(String Cadena, int opc) throws SQLException {
        Connection cn = con2.getConnection();
        PreparedStatement pps = null;
        String SQL = "";
        if (opc == 0) {
            SQL = "INSERT INTO datosclienteAux VALUES " + Cadena + ";";
        }
        if (opc == 1) {
            SQL = "INSERT INTO telefonos VALUES " + Cadena + ";";
        }
        try {
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error GuardaPart1datosClienteAux:-*--*//-*/" + ex.getMessage());
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pps != null) {
                con2.close(pps);
            }
            if (cn != null) {
                con2.close(cn);
            }
        }   //finally catch  
    }//@endGuardaPart1datosClienteAux

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
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return existe;
    }

    //*** Obtener ultimo turno creado
    public int getLastdIdArchi() throws SQLException {
        Connection cn = con2.getConnection();
        int idTurno = -1;
        String sql = "";
        sql = "SELECT id FROM archivo ORDER BY id DESC LIMIT 1;";
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
    }//@endgetLastdIdArchi

    //regresa matrizde vista tickets del dia
    public List<List<String>> getNewsCuentas() throws SQLException {
        Connection cn = con2.getConnection();
        List<List<String>> container = new ArrayList<>();
        List<String> interna;
        String sql = "", aux;
        sql = "SELECT numCuenta,ssolicitud_OT,client_desde,fech_creacion,fech_llegada,nombre,despacho,calle,numExter,numInteri,colonia,cp,municipio,edo,entCalle1,entCalle2,\n"
                + " telCasa,telTrabaj,telOtro,email,latitud,lingitud,status,fech_llegada,subStatus,tipoSubStatus,cartera\n"
                + " FROM datosclienteaux\n"
                + " WHERE NOT EXISTS ( SELECT * FROM datoscliente\n"
                + " WHERE\n"
                + " datoscliente.numCuenta = datosclienteaux.numCuenta AND\n"
                + " datoscliente.ssolicitud_OT = datosclienteaux.ssolicitud_OT);";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {//es necesario el for para llenar dinamicamente la lista, ya que varia el numero de columnas de las tablas
                interna = new ArrayList<>();
                for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                    // System.out.print("| "+rs.getString(x)+" |");
                    interna.add(rs.getString(x).trim());
                    //System.out.print(x+" -> "+rs.getString(x));                   
                }//for
                container.add(interna);
            }//whilE
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//             System.out.println("cierra conexion a la base de datos");    
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//finally        

        return container;
    }//@endmatrizgetAmbuSemana

    public static void printContainer(List<List<String>> container) {
        int contador = 1;
        for (List<String> stringList : container) {
            System.out.print(contador + "-");

            for (String str : stringList) {
                System.out.printf("%10s", str.trim() + " | ");
            }
            contador++;
            System.out.println("");
        }
    }

        //*** Obtener ultimo turno creado
    public void endTruncateAux() throws SQLException {
        Connection cn = con2.getConnection();
        PreparedStatement pps = null;
        String SQL = "";
            SQL = "TRUNCATE TABLE datosclienteaux;";
        try {
            pps = cn.prepareStatement(SQL);
            pps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error endTruncateAux/-//*/**//*-:" + ex.getMessage());
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pps != null) {
                con2.close(pps);
            }
            if (cn != null) {
                con2.close(cn);
            }
        }   //finally catch  
    }//@endTruncateAux
    
           public void elimaRow(int id) throws SQLException{
            Connection cn = con2.getConnection();
            PreparedStatement preparedStmt = null;
                try {
                String query = "delete from datoscliente where cartera = '"+id+"' ";
                preparedStmt = cn.prepareStatement(query);
                preparedStmt.execute();
            } catch (SQLException ex) {
            }finally{
                try {
                    if(preparedStmt != null) preparedStmt.close();                
                    if(cn !=null) cn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                }//catch
            }//finally 
}
    
    
    public static void main(String[] args) {
        controller cont = new controller();
        try {
        //    cont.endTruncateAux();
            List<List<String>> contain = cont.getNewsCuentas();
            printContainer(contain);
            /*
            try {
            List<List<String>> contain = cont.getNewsCuentas();
            printContainer(contain);
            } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//main

}
