package intcomex.vendeygana.contenedor.fragments.venta.di;

import android.Manifest;
import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import intcomex.vendeygana.contenedor.fragments.venta.interactor.Interactor;
import intcomex.vendeygana.contenedor.fragments.venta.interactor.VentasInteractor;
import intcomex.vendeygana.contenedor.fragments.venta.presenter.Presenter;
import intcomex.vendeygana.contenedor.fragments.venta.presenter.VentasPresenter;
import intcomex.vendeygana.contenedor.fragments.venta.repository.Repository;
import intcomex.vendeygana.contenedor.fragments.venta.repository.VentasRepository;
import intcomex.vendeygana.contenedor.fragments.venta.view.VentaView;
import intcomex.vendeygana.domain.permisos.PermisoObj;
import intcomex.vendeygana.domain.permisos.Permisos;
import intcomex.vendeygana.io.RetroFitHelper;
import intcomex.vendeygana.io.Urls;

/**
 * Created by nesto on 3/05/2017.
 */
@Module
public class VentasModule {
    private VentaView view;

    public VentasModule(VentaView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    VentasPresenter providerVentasPresenter(VentaView ventaView, Permisos permisos, VentasInteractor ventasInteractor){
        return new Presenter(ventaView, permisos, ventasInteractor);
    }

    @Provides
    @Singleton
    Permisos providerPermisos(Activity activity){
        return new Permisos(activity, new PermisoObj(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1));
    }

    @Provides
    @Singleton
    VentaView providerVentasView(){
        return this.view;
    }

    @Provides
    @Singleton
    VentasInteractor providerVentasInteractor(VentasRepository repository){
        return new Interactor(repository);
    }

    @Provides
    @Singleton
    VentasRepository providerVentasRepository(Urls urls){
        return new Repository(urls);
    }

    @Provides
    @Singleton
    Urls providerUrl(){
        return RetroFitHelper.getApiService();
    }

}
