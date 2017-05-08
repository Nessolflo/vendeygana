package intcomex.vendeygana.contenedor.fragments.premios.entities;

import android.content.Context;

/**
 * Created by nesto on 6/05/2017.
 */

public class DatosPuntos {
    private Context context;
    private String idvendedor;

    public DatosPuntos(Context context, String idvendedor) {
        this.context = context;
        this.idvendedor = idvendedor;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }
}
