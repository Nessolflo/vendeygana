package intcomex.vendeygana.contenedor.fragments.venta.di;

import javax.inject.Singleton;

import dagger.Component;
import intcomex.vendeygana.contenedor.di.ContenedorModule;
import intcomex.vendeygana.contenedor.fragments.venta.view.FragmentVenta;

/**
 * Created by nesto on 3/05/2017.
 */
@Singleton @Component(modules = {VentasModule.class, ContenedorModule.class})
public interface VentasComponent {
    void inject(FragmentVenta fragmentVenta);
}
