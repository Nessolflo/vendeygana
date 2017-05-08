package intcomex.vendeygana.contenedor.fragments.venta.repository;

import com.google.gson.JsonObject;

import java.io.File;

import intcomex.vendeygana.contenedor.fragments.venta.entities.VentasDatos;

/**
 * Created by nesto on 2/05/2017.
 */

public interface VentasRepository {
    void enviarDatos(VentasDatos ventasDatos);
    void enviarRespuesta(JsonObject jsonObject);
}
