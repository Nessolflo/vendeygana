package intcomex.vendeygana.contenedor.fragments.premios.repository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import intcomex.vendeygana.R;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPuntos;
import intcomex.vendeygana.contenedor.fragments.venta.entities.RespuestaVentas;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.io.Urls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nesto on 6/05/2017.
 */

public class Repository implements PremiosRepository {

    private Urls urls;

    public Repository(Urls urls) {
        this.urls = urls;
    }

    @Override
    public void consultarPuntos(final DatosPuntos datosPuntos) {
        urls.consultarPuntos(datosPuntos.getIdvendedor()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    enviarRespuestaPuntos(jsonObject);
                } else
                    enviarRespuestaPuntos(
                            crearJsonError(
                                    String.format(
                                            datosPuntos.getContext().getString(R.string.errorconsultarws),
                                            response.code()
                                    )
                            )
                    );
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (t.getLocalizedMessage().contains("Failed to connect"))
                    enviarRespuestaPuntos(
                            crearJsonError(
                                    datosPuntos.getContext().getString(R.string.conexioninternet)
                            )
                    );
                else
                    enviarRespuestaPuntos(
                            crearJsonError(
                                    String.format(
                                            datosPuntos.getContext().getString(R.string.errorconsultarwsstring),
                                            t.getLocalizedMessage()
                                    )
                            )
                    );
            }
        });
    }

    @Override
    public void consultarPremios(final DatosPremios datosPremios) {
        urls.catalogoPremios(datosPremios.getPuntos()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    JsonObject jsonObject = response.body().getAsJsonObject();
                    enviarRespuestaPremios(jsonObject);
                } else
                    enviarRespuestaPremios(
                            crearJsonError(
                                    String.format(
                                            datosPremios.getContext().getString(R.string.errorconsultarws),
                                            response.code()
                                    )
                            )
                    );
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (t.getLocalizedMessage().contains("Failed to connect"))
                    enviarRespuestaPremios(
                            crearJsonError(
                                    datosPremios.getContext().getString(R.string.conexioninternet)
                            )
                    );
                else
                    enviarRespuestaPremios(
                            crearJsonError(
                                    String.format(
                                            datosPremios.getContext().getString(R.string.errorconsultarwsstring),
                                            t.getLocalizedMessage()
                                    )
                            )
                    );
            }
        });
    }

    private void enviarRespuestaPremios(JsonObject jsonObject) {
        EventBus.getDefault().post(new RespuestaPremios(jsonObject));
    }

    private void enviarRespuestaPuntos(JsonObject jsonObject) {
        EventBus.getDefault().post(new RespuestaPuntos(jsonObject));
    }

    private JsonObject crearJsonError(String mensaje) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeys.JSON_MESSAGE, mensaje);
        jsonObject.addProperty(JsonKeys.JSON_RESULT, false);
        return jsonObject;
    }
}
