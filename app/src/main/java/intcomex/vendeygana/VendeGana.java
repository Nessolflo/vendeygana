package intcomex.vendeygana;

import android.app.Activity;
import android.app.Application;

import intcomex.vendeygana.contenedor.di.ContenedorModule;
import intcomex.vendeygana.contenedor.fragments.premios.di.DaggerPremiosComponent;
import intcomex.vendeygana.contenedor.fragments.premios.di.PremiosComponent;
import intcomex.vendeygana.contenedor.fragments.premios.di.PremiosModule;
import intcomex.vendeygana.contenedor.fragments.premios.view.PremiosView;
import intcomex.vendeygana.contenedor.fragments.promociones.di.DaggerPromocionesComponent;
import intcomex.vendeygana.contenedor.fragments.promociones.di.PromocionesComponent;
import intcomex.vendeygana.contenedor.fragments.promociones.di.PromocionesModule;
import intcomex.vendeygana.contenedor.fragments.promociones.view.PromocionesView;
import intcomex.vendeygana.contenedor.fragments.venta.di.DaggerVentasComponent;
import intcomex.vendeygana.contenedor.fragments.venta.di.VentasComponent;
import intcomex.vendeygana.contenedor.fragments.venta.di.VentasModule;
import intcomex.vendeygana.contenedor.fragments.venta.view.VentaView;
import intcomex.vendeygana.login.di.DaggerLoginComponent;
import intcomex.vendeygana.login.di.LoginComponent;
import intcomex.vendeygana.login.di.LoginModule;
import intcomex.vendeygana.login.view.LoginView;
import intcomex.vendeygana.registro.di.DaggerRegistroComponent;
import intcomex.vendeygana.registro.di.RegistroComponent;
import intcomex.vendeygana.registro.di.RegistroModule;
import intcomex.vendeygana.registro.view.RegistroView;

/**
 * Created by nesto on 24/04/2017.
 */

public class VendeGana extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoginComponent getLoginComponent(LoginView loginView){
        return DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(loginView))
                .build();
    }

    public RegistroComponent getRegistroComponent(RegistroView registroView){
        return DaggerRegistroComponent
                .builder()
                .registroModule(new RegistroModule(registroView))
                .build();
    }

    public VentasComponent getVentasComponent(Activity activity, VentaView ventaView){
        return DaggerVentasComponent
                .builder()
                .contenedorModule(new ContenedorModule(activity))
                .ventasModule(new VentasModule(ventaView))
                .build();
    }

    public PromocionesComponent getPromocionesComponent(PromocionesView view){
        return DaggerPromocionesComponent
                .builder()
                .promocionesModule(new PromocionesModule(view))
                .build();
    }

    public PremiosComponent getPremiosComponent(PremiosView view){
        return DaggerPremiosComponent
                .builder()
                .premiosModule(new PremiosModule(view))
                .build();
    }
}
