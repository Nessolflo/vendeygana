package intcomex.vendeygana.registro.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.io.Urls;
import intcomex.vendeygana.registro.entities.DatosRegistro;
import intcomex.vendeygana.registro.entities.RespuestaRegistro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nesto on 25/04/2017.
 */

public class Repository implements RegistroRepository {

    private Urls urls;

    public Repository(Urls urls) {
        this.urls = urls;
    }

    @Override
    public void enviarDatos(final DatosRegistro datos) {
        urls.registroMovil(datos.getNombre(),
                datos.getFechanacimiento(),
                datos.getDpi(),
                datos.getTelefono(),
                datos.getEmail(),
                datos.getUsuario(),
                datos.getClave()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    enviarRespuesta(jsonObject);
                } else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            datos.getContext().getString(R.string.errorconsultarws),
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
                                    datos.getContext().getString(R.string.conexioninternet)
                            )
                    );
                else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            datos.getContext().getString(R.string.errorconsultarwsstring),
                                            t.getLocalizedMessage()
                                    )
                            )
                    );
            }
        });
    }

    @Override
    public void enviarRespuesta(JsonObject jsonObject) {
        EventBus.getDefault().post(new RespuestaRegistro(jsonObject));
    }

    private JsonObject crearJsonError(String mensaje) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeys.JSON_MESSAGE, mensaje);
        jsonObject.addProperty(JsonKeys.JSON_RESULT, false);
        return jsonObject;
    }
}
