package intcomex.vendeygana.contenedor.fragments.premios.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by nesto on 6/05/2017.
 */

public class RespuestaPremios {
    private JsonObject premios;

    public RespuestaPremios(JsonObject premios) {
        this.premios = premios;
    }

    public JsonObject getPremios() {
        return premios;
    }

    public void setPremios(JsonObject premios) {
        this.premios = premios;
    }
}
