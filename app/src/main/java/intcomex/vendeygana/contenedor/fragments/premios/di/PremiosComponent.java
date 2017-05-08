package intcomex.vendeygana.contenedor.fragments.premios.di;

import javax.inject.Singleton;

import dagger.Component;
import intcomex.vendeygana.contenedor.fragments.premios.view.FragmentCatalogoPremios;

/**
 * Created by nesto on 6/05/2017.
 */
@Singleton @Component(modules = {PremiosModule.class})
public interface PremiosComponent {
    void inject(FragmentCatalogoPremios fragmentCatalogoPremios);
}
