package intcomex.vendeygana.login.repository;

import com.google.gson.JsonObject;

import intcomex.vendeygana.login.entities.LoginDatos;

/**
 * Created by nesto on 24/04/2017.
 */

public interface LoginRepository {
    void enviarDatos(LoginDatos loginDatos);

    void enviarRespuesta(JsonObject jsonObject);
}
