package intcomex.vendeygana.login.entities;

import com.google.gson.JsonObject;

/**
 * Created by nesto on 24/04/2017.
 */

public class RespuestaLogin {
    private JsonObject jsonObject;

    public RespuestaLogin(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
