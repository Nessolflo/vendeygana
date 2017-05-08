package intcomex.vendeygana.contenedor.fragments.promociones.entities;

import com.google.gson.JsonObject;

/**
 * Created by nesto on 4/05/2017.
 */

public class RespuestaPromociones {
    private JsonObject jsonObject;

    public RespuestaPromociones(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
