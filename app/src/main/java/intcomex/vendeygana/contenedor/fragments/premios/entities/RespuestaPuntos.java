package intcomex.vendeygana.contenedor.fragments.premios.entities;

import com.google.gson.JsonObject;

/**
 * Created by nesto on 6/05/2017.
 */

public class RespuestaPuntos {
    private JsonObject vendedor;

    public RespuestaPuntos(JsonObject vendedor) {
        this.vendedor = vendedor;
    }

    public JsonObject getVendedor() {
        return vendedor;
    }

    public void setVendedor(JsonObject vendedor) {
        this.vendedor = vendedor;
    }
}
