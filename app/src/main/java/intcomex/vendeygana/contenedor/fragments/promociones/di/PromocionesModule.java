package intcomex.vendeygana.contenedor.fragments.promociones.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intcomex.vendeygana.contenedor.fragments.promociones.interactor.Interactor;
import intcomex.vendeygana.contenedor.fragments.promociones.interactor.PromocionesInteractor;
import intcomex.vendeygana.contenedor.fragments.promociones.presenter.Presenter;
import intcomex.vendeygana.contenedor.fragments.promociones.presenter.PromocionesPresenter;
import intcomex.vendeygana.contenedor.fragments.promociones.repository.PromocionesRepository;
import intcomex.vendeygana.contenedor.fragments.promociones.repository.Repository;
import intcomex.vendeygana.contenedor.fragments.promociones.view.PromocionesView;
import intcomex.vendeygana.io.RetroFitHelper;
import intcomex.vendeygana.io.Urls;

/**
 * Created by nesto on 4/05/2017.
 */

@Module
public class PromocionesModule {

    private PromocionesView view;

    public PromocionesModule(PromocionesView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PromocionesPresenter providerPresenter(PromocionesView view, PromocionesInteractor interactor){
        return  new Presenter(view, interactor);
    }

    @Provides
    @Singleton
    PromocionesView providerView(){
        return this.view;
    }

    @Provides
    @Singleton
    PromocionesInteractor providerInteractor(PromocionesRepository repository) {
        return new Interactor(repository);
    }

    @Provides
    @Singleton
    PromocionesRepository providerRepository(Urls urls){
        return new Repository(urls);
    }

    @Provides
    @Singleton
    Urls providerUrl(){
        return RetroFitHelper.getApiService();
    }

}
