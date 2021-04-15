
package dominio;

public class cuenta {
   
    private int id;
    private String numCuenta;
    private String ssolicitud_OT;
    private String nombre;
    private String status;
    private String cp;

    public cuenta() {
    }

    public cuenta(int idCliente) {
        this.id = idCliente;
    }

    public cuenta(String cuenta, String silicitud, String nombre, String status) {
        this.numCuenta = cuenta;
        this.ssolicitud_OT = silicitud;
        this.nombre = nombre;
        this.status = status;
//        this.saldo = saldo;
    }

    public cuenta(int idCuenta, String cuenta, String solicitud, String nombre, String status, String cp) {
        this.id = idCuenta;
        this.numCuenta = cuenta;
        this.ssolicitud_OT = solicitud;
        this.nombre = nombre;
        this.status = status;
        this.cp = cp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getSsolicitud_OT() {
        return ssolicitud_OT;
    }

    public void setSsolicitud_OT(String ssolicitud_OT) {
        this.ssolicitud_OT = ssolicitud_OT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "cuenta{" + "id=" + id + ", numCuenta=" + numCuenta + ", ssolicitud_OT=" + ssolicitud_OT + ", nombre=" + nombre + ", status=" + status + ", C.P.=" + cp + '}';
    }



    
}
