package intcomex.vendeygana.contenedor.fragments.premios.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intcomex.vendeygana.contenedor.fragments.premios.interactor.Interactor;
import intcomex.vendeygana.contenedor.fragments.premios.interactor.PremiosInteractor;
import intcomex.vendeygana.contenedor.fragments.premios.presenter.PremiosPresenter;
import intcomex.vendeygana.contenedor.fragments.premios.presenter.Presenter;
import intcomex.vendeygana.contenedor.fragments.premios.repository.PremiosRepository;
import intcomex.vendeygana.contenedor.fragments.premios.repository.Repository;
import intcomex.vendeygana.contenedor.fragments.premios.view.PremiosView;
import intcomex.vendeygana.io.RetroFitHelper;
import intcomex.vendeygana.io.Urls;

/**
 * Created by nesto on 6/05/2017.
 */
@Module
public class PremiosModule {

    private PremiosView premiosView;

    public PremiosModule(PremiosView premiosView) {
        this.premiosView = premiosView;
    }

    @Provides
    @Singleton
    PremiosPresenter premiosPresenter(PremiosView view, PremiosInteractor interactor){
        return new Presenter(view, interactor);
    }

    @Provides
    @Singleton
    PremiosView premiosView()
    {
        return this.premiosView;
    }

    @Provides
    @Singleton
    PremiosInteractor premiosInteractor(PremiosRepository repository){
        return  new Interactor(repository);
    }

    @Provides
    @Singleton
    PremiosRepository premiosRepository(Urls urls){
        return new Repository(urls);
    }

    @Provides
    @Singleton
    Urls providerUrl(){
        return RetroFitHelper.getApiService();
    }
}
