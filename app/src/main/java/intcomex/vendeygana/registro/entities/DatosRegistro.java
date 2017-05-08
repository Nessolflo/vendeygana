package intcomex.vendeygana.registro.entities;

import android.content.Context;

/**
 * Created by nesto on 25/04/2017.
 */

public class DatosRegistro {
    private String nombre;
    private String fechanacimiento;
    private String dpi;
    private String telefono;
    private String email;
    private String usuario;
    private String clave;
    private Context context;

    public DatosRegistro(String nombre, String fechanacimiento, String dpi, String telefono, String email, String usuario, String clave, Context context) {
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.dpi = dpi;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
        this.clave = clave;
        this.context = context;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
