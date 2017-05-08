package intcomex.vendeygana.contenedor.fragments.premios.entities;

import android.content.Context;

/**
 * Created by nesto on 6/05/2017.
 */

public class DatosPremios {
    private Context context;
    private String puntos;

    public DatosPremios(Context context, String puntos) {
        this.context = context;
        this.puntos = puntos;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
