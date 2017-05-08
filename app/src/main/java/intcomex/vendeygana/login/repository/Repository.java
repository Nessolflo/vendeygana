package intcomex.vendeygana.login.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.io.Urls;
import intcomex.vendeygana.login.entities.LoginDatos;
import intcomex.vendeygana.login.entities.RespuestaLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nesto on 24/04/2017.
 */

public class Repository implements LoginRepository {

    private Urls urls;

    public Repository(Urls urls) {
        this.urls = urls;
    }

    @Override
    public void enviarDatos(final LoginDatos loginDatos) {
        urls.loginMovil(loginDatos.getUsuario(), loginDatos.getClave(), loginDatos.getVersion()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    enviarRespuesta(jsonObject);
                } else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            loginDatos.getContext().getString(R.string.errorconsultarws),
                                            response.code()
                                    )
                            )
                    );

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (t.getLocalizedMessage().contains("Failed to connect"))
                    enviarRespuesta(
                            crearJsonError(
                                    loginDatos.getContext().getString(R.string.conexioninternet)
                            )
                    );
                else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            loginDatos.getContext().getString(R.string.errorconsultarwsstring),
                                            t.getLocalizedMessage()
                                    )
                            )
                    );
            }
        });
    }

    @Override
    public void enviarRespuesta(JsonObject jsonObject) {
        EventBus.getDefault().post(new RespuestaLogin(jsonObject));
    }

    private JsonObject crearJsonError(String mensaje) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeys.JSON_MESSAGE, mensaje);
        jsonObject.addProperty(JsonKeys.JSON_RESULT, false);
        return jsonObject;
    }
}
