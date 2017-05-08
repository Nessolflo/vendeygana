package intcomex.vendeygana.registro.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intcomex.vendeygana.io.RetroFitHelper;
import intcomex.vendeygana.io.Urls;
import intcomex.vendeygana.registro.interactor.Interactor;
import intcomex.vendeygana.registro.interactor.RegistroInteractor;
import intcomex.vendeygana.registro.presenter.Presenter;
import intcomex.vendeygana.registro.presenter.RegistroPresenter;
import intcomex.vendeygana.registro.repository.RegistroRepository;
import intcomex.vendeygana.registro.repository.Repository;
import intcomex.vendeygana.registro.view.RegistroView;

/**
 * Created by nesto on 25/04/2017.
 */
@Module
public class RegistroModule {
    private RegistroView view;

    public RegistroModule(RegistroView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RegistroPresenter registroPresenter(RegistroView view, RegistroInteractor registroInteractor){
        return new Presenter(registroInteractor, view);
    }

    @Provides
    @Singleton
    RegistroView registroView(){
        return this.view;
    }

    @Provides
    @Singleton
    RegistroInteractor registroInteractor(RegistroRepository registroRepository){
        return new Interactor(registroRepository);
    }

    @Provides
    @Singleton
    RegistroRepository registroRepository(Urls urls){
        return new Repository(urls);
    }

    @Provides
    @Singleton
    Urls providerUrl(){
        return RetroFitHelper.getApiService();
    }
}
