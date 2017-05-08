package intcomex.vendeygana.login.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intcomex.vendeygana.io.RetroFitHelper;
import intcomex.vendeygana.io.Urls;
import intcomex.vendeygana.login.interactor.Interactor;
import intcomex.vendeygana.login.interactor.LoginInteractor;
import intcomex.vendeygana.login.presenter.LoginPresenter;
import intcomex.vendeygana.login.presenter.Presenter;
import intcomex.vendeygana.login.repository.LoginRepository;
import intcomex.vendeygana.login.repository.Repository;
import intcomex.vendeygana.login.view.LoginView;

/**
 * Created by nesto on 24/04/2017.
 */
@Module
public class LoginModule {
    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginPresenter providerPresenter(LoginView view, LoginInteractor loginInteractor) {
        return new Presenter(view, loginInteractor);
    }

    @Provides
    @Singleton
    LoginView providerView(){
        return this.view;
    }

    @Provides
    @Singleton
    LoginInteractor providerInteractor(LoginRepository loginRepository){
        return new Interactor(loginRepository);
    }

    @Provides
    @Singleton
    LoginRepository providerRepository(Urls urls){
        return new Repository(urls);
    }

    @Provides
    @Singleton
    Urls providerUrl(){
        return RetroFitHelper.getApiService();
    }
}
