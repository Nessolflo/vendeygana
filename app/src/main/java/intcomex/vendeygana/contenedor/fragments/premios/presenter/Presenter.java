package intcomex.vendeygana.contenedor.fragments.premios.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import intcomex.vendeygana.R;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.RespuestaPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.interactor.PremiosInteractor;
import intcomex.vendeygana.contenedor.fragments.premios.view.PremiosView;
import intcomex.vendeygana.domain.JsonKeys;

/**
 * Created by nesto on 6/05/2017.
 */

public class Presenter implements PremiosPresenter {

    private PremiosView premiosView;
    private PremiosInteractor premiosInteractor;

    public Presenter(PremiosView premiosView, PremiosInteractor premiosInteractor) {
        this.premiosView = premiosView;
        this.premiosInteractor = premiosInteractor;
    }

    @Override
    public void consultarPuntos(DatosPuntos datosPuntos) {
        premiosView.showProgress();
        premiosView.progressMessage(
                premiosView.obtenerContexto().getString(R.string.cargando),
                premiosView.obtenerContexto().getString(R.string.consultandopuntos)
        );
        premiosInteractor.consultarPuntos(datosPuntos);
    }

    @Override
    public void consultarPremios(DatosPremios datosPremios) {
        premiosView.showProgress();
        premiosView.progressMessage(
                premiosView.obtenerContexto().getString(R.string.cargando),
                premiosView.obtenerContexto().getString(R.string.consultandopremios)
        );
        premiosInteractor.consultarPremios(datosPremios);
    }

    @Override
    @Subscribe
    public void respuestaPuntos(RespuestaPuntos respuestaPuntos) {
        premiosView.hideProgress();
        if (respuestaPuntos.getVendedor().get(JsonKeys.JSON_RESULT).getAsBoolean()) {
            premiosView.setPuntos(respuestaPuntos.getVendedor().get(JsonKeys.JSON_RECORDS).getAsJsonObject());
            consultarPremios(new DatosPremios(premiosView.obtenerContexto(), ""));
        } else
            premiosView.showMessage(respuestaPuntos.getVendedor().get(JsonKeys.JSON_MESSAGE).getAsString());
    }

    @Override
    @Subscribe
    public void respuestaPremios(RespuestaPremios respuestaPremios) {
        premiosView.hideProgress();
        if (respuestaPremios.getPremios().get(JsonKeys.JSON_RESULT).getAsBoolean())
            premiosView.setAdaptador(respuestaPremios.getPremios().get(JsonKeys.JSON_RECORDS).getAsJsonArray());
        else
            premiosView.showMessage(respuestaPremios.getPremios().get(JsonKeys.JSON_MESSAGE).getAsString());

    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }
}
