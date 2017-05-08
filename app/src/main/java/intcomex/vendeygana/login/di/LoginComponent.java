package intcomex.vendeygana.login.di;

import javax.inject.Singleton;

import dagger.Component;
import intcomex.vendeygana.login.view.Login;

/**
 * Created by nesto on 24/04/2017.
 */
@Singleton @Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(Login login);
}
