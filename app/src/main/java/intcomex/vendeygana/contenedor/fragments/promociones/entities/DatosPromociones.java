package intcomex.vendeygana.contenedor.fragments.promociones.entities;

import android.content.Context;

/**
 * Created by nesto on 4/05/2017.
 */

public class DatosPromociones {
    private Context context;

    public DatosPromociones(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
