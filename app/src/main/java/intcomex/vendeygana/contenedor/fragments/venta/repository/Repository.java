package intcomex.vendeygana.contenedor.fragments.venta.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import intcomex.vendeygana.R;
import intcomex.vendeygana.contenedor.fragments.venta.entities.RespuestaVentas;
import intcomex.vendeygana.contenedor.fragments.venta.entities.VentasDatos;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.io.Urls;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nesto on 2/05/2017.
 */

public class Repository implements VentasRepository {

    private Urls urls;

    public Repository(Urls urls) {
        this.urls = urls;
    }

    @Override
    public void enviarDatos(final VentasDatos ventasDatos) {
        RequestBody file = RequestBody.create(MediaType.parse("image/*"), ventasDatos.getPathFotos());
        urls.registrarVenta(file, ventasDatos.getId(), ventasDatos.getImei(), ventasDatos.getCorrelativo()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    enviarRespuesta(jsonObject);
                } else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            ventasDatos.getContext().getString(R.string.errorconsultarws),
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
                                    ventasDatos.getContext().getString(R.string.conexioninternet)
                            )
                    );
                else
                    enviarRespuesta(
                            crearJsonError(
                                    String.format(
                                            ventasDatos.getContext().getString(R.string.errorconsultarwsstring),
                                            t.getLocalizedMessage()
                                    )
                            )
                    );

            }
        });
    }

    @Override
    public void enviarRespuesta(JsonObject jsonObject) {
        EventBus.getDefault().post(new RespuestaVentas(jsonObject));
    }

    private JsonObject crearJsonError(String mensaje) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeys.JSON_MESSAGE, mensaje);
        jsonObject.addProperty(JsonKeys.JSON_RESULT, false);
        return jsonObject;
    }
}
