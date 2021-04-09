
package dominio;

public class Archivo {
    private int id;//int
    private int idUsuario; //int
    private String nombre;//varchar
    private String fecha;//datetime
    private int tipoCart;//int
    private int statusCarg;//bit
    private int numRegist;//int

    public Archivo() {
    }

    public Archivo(int id) {
        this.id = id;
    }
    
        public Archivo(int id, int idCliente,String nombre, String fech, int tipo, int statC, int numR) {  
        this.id = idCliente;
        this.idUsuario = idCliente;
        this.nombre = nombre;
        this.fecha = fech;
        this.tipoCart = tipo;
        this.statusCarg = statC;
        this.numRegist = numR;
    }

    public Archivo(String nombre, String fech, int tipo, int statC, int numR) {
        this.nombre = nombre;
        this.fecha = fech;
        this.tipoCart = tipo;
        this.statusCarg = statC;
        this.numRegist = numR;
    }

    public Archivo(int idCliente,String nombre, String fech, int tipo, int statC, int numR) {        
        this.idUsuario = idCliente;
        this.nombre = nombre;
        this.fecha = fech;
        this.tipoCart = tipo;
        this.statusCarg = statC;
        this.numRegist = numR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipoCart() {
        return tipoCart;
    }

    public void setTipoCart(int tipoCart) {
        this.tipoCart = tipoCart;
    }

    public int getStatusCarg() {
        return statusCarg;
    }

    public void setStatusCarg(int statusCarg) {
        this.statusCarg = statusCarg;
    }

    public int getNumRegist() {
        return numRegist;
    }

    public void setNumRegist(int numRegist) {
        this.numRegist = numRegist;
    }

    @Override
    public String toString() {
        return "Archivo{" + "id=" + id + ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", fecha=" + fecha + ", tipoCart=" + tipoCart + ", statusCarg=" + statusCarg + ", numRegist=" + numRegist + '}';
    }
   
}
