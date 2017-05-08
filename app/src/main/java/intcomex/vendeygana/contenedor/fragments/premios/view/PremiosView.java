package intcomex.vendeygana.contenedor.fragments.premios.view;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import intcomex.vendeygana.domain.ProgressView;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PremiosView extends ProgressView {
    void setAdaptador(JsonArray premios);
    void setPuntos(JsonObject vendedor);
    void filtrarPuntos(boolean checked);
    Context obtenerContexto();
}
