package intcomex.vendeygana.contenedor.fragments.promociones.presenter;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.entities.RespuestaPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.interactor.PromocionesInteractor;
import intcomex.vendeygana.contenedor.fragments.promociones.view.PromocionesView;
import intcomex.vendeygana.domain.JsonKeys;

/**
 * Created by nesto on 4/05/2017.
 */

public class Presenter implements PromocionesPresenter {

    private PromocionesView view;
    private PromocionesInteractor interactor;

    public Presenter(PromocionesView view, PromocionesInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void consultarDatos(DatosPromociones datosPromociones) {
        interactor.consultarDatos(datosPromociones);
    }

    @Override
    @Subscribe
    public void respuestaServidor(RespuestaPromociones respuestaPromociones) {
        JsonObject json = respuestaPromociones.getJsonObject();
        view.hideProgress();
        view.showMessage(json.get(JsonKeys.JSON_MESSAGE).getAsString());
        if(json.get(JsonKeys.JSON_RESULT).getAsBoolean()){
            view.setAdapter(json.get(JsonKeys.JSON_RECORDS).getAsJsonArray());
        }
    }
}
