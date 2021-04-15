package web;

import datos.ClienteDaoJDBC;
import datos.cuentaDaoJDBC;
import dominio.Cliente;
import dominio.cuenta;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import procesarExcel.Test2;

@MultipartConfig
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    Test2 procesarExcel = new Test2();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<cuenta> clientes = new cuentaDaoJDBC().listar();
        //System.out.println("clientes = "+clientes);
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //   processRequest(request, response);
        PrintWriter out = response.getWriter();
        String nomb = request.getParameter("nombre");
        Part arch = request.getPart("archivo");
        InputStream is = arch.getInputStream();
        File f = new File("A:/Archivos/" + nomb);
        FileOutputStream ous = new FileOutputStream(f);
        int dato = is.read();
        while (dato != -1) {
            ous.write(dato);
            dato = is.read();
        }
        ous.close();
        is.close();
        //lo pinta en el iframe
        out.println("Bienvenidp" + nomb);
        try {//procesa el excel enviado
            procesarExcel.lee("A:/Archivos/" + nomb,0,1212);
        } catch (Exception ex) {
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
