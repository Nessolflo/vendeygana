package intcomex.vendeygana.contenedor.di;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nesto on 3/05/2017.
 */
@Module
public class ContenedorModule {
    private Activity activity;

    public ContenedorModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    Activity providerActivity(){
        return this.activity;
    }
}
