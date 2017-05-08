package intcomex.vendeygana.contenedor.fragments.promociones.repository;

import com.google.gson.JsonObject;

import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PromocionesRepository {
    void consultarDatos(DatosPromociones datosPromociones);
    void enviarRespuesta(JsonObject jsonObject);

}
