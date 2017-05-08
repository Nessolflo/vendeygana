package intcomex.vendeygana.contenedor.fragments.promociones.presenter;

import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.entities.RespuestaPromociones;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PromocionesPresenter {
    void consultarDatos(DatosPromociones datosPromociones);
    void onStart();
    void onStop();
    void respuestaServidor(RespuestaPromociones respuestaPromociones);
}
