package intcomex.vendeygana.registro.entities;

import com.google.gson.JsonObject;

/**
 * Created by nesto on 25/04/2017.
 */

public class RespuestaRegistro {
    private JsonObject jsonObject;

    public RespuestaRegistro(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
