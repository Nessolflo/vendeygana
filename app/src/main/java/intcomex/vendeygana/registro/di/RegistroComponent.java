package intcomex.vendeygana.registro.di;

import javax.inject.Singleton;

import dagger.Component;
import intcomex.vendeygana.registro.view.RegistroActivity;

/**
 * Created by nesto on 25/04/2017.
 */
@Singleton @Component(modules = {RegistroModule.class})
public interface RegistroComponent {
    void inject(RegistroActivity registroActivity);
}
