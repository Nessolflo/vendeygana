package intcomex.vendeygana.contenedor.fragments.venta.entities;

import android.content.Context;

import java.io.File;

/**
 * Created by nesto on 3/05/2017.
 */

public class VentasDatos {
    private String id;
    private String imei;
    private String correlativo;
    private File pathFotos;
    private Context context;

    public VentasDatos(Context context, String id, String imei, String correlativo, File pathFotos) {
        this.id = id;
        this.imei = imei;
        this.correlativo = correlativo;
        this.pathFotos = pathFotos;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public File getPathFotos() {
        return pathFotos;
    }

    public void setPathFotos(File pathFotos) {
        this.pathFotos = pathFotos;
    }
}
