package intcomex.vendeygana.contenedor.fragments.promociones.di;

import javax.inject.Singleton;

import dagger.Component;
import intcomex.vendeygana.contenedor.fragments.promociones.view.FragmentCatalogoPromociones;

/**
 * Created by nesto on 4/05/2017.
 */
@Singleton @Component(modules = {PromocionesModule.class})
public interface PromocionesComponent {
    void inject(FragmentCatalogoPromociones promociones);
}
