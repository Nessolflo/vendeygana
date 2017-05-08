package intcomex.vendeygana.contenedor.fragments.promociones.view;

import com.google.gson.JsonArray;

import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;
import intcomex.vendeygana.domain.ProgressView;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PromocionesView extends ProgressView {
    void consultarDatos(DatosPromociones datosPromociones);
    void setAdapter(JsonArray jsonArray);
}
