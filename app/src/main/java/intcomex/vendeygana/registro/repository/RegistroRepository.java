package intcomex.vendeygana.registro.repository;

import com.google.gson.JsonObject;

import intcomex.vendeygana.registro.entities.DatosRegistro;

/**
 * Created by nesto on 25/04/2017.
 */

public interface RegistroRepository {
    void enviarDatos(DatosRegistro datosRegistro);
    void enviarRespuesta(JsonObject jsonObject);
}
