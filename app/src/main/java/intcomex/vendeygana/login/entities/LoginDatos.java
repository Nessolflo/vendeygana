package intcomex.vendeygana.login.entities;

import android.content.Context;

/**
 * Created by nesto on 24/04/2017.
 */

public class LoginDatos {
    private String usuario;
    private String clave;
    private String version;
    private Context context;

    public LoginDatos( Context context, String usuario, String clave, String version) {
        this.usuario = usuario;
        this.clave = clave;
        this.version = version;
        this.context = context;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
