package intcomex.vendeygana.contenedor.fragments.venta.entities;

import com.google.gson.JsonObject;

/**
 * Created by nesto on 2/05/2017.
 */

public class RespuestaVentas {
    private JsonObject jsonRespuesta;

    public RespuestaVentas(JsonObject jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public JsonObject getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(JsonObject jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }
}
