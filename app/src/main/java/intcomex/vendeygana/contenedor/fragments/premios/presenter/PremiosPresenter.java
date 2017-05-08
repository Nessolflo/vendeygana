package intcomex.vendeygana.contenedor.fragments.premios.presenter;

import com.google.gson.JsonObject;

import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPuntos;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PremiosPresenter {
    void consultarPuntos(DatosPuntos datosPuntos);
    void consultarPremios(DatosPremios datosPremios);
    void respuestaPuntos(RespuestaPuntos respuestaPuntos);
    void respuestaPremios(RespuestaPremios respuestaPremios);
    void onStart();
    void onStop();
}
