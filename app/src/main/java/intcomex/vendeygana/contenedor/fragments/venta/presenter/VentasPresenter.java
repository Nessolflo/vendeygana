package intcomex.vendeygana.contenedor.fragments.venta.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.io.File;

import intcomex.vendeygana.contenedor.fragments.venta.entities.RespuestaVentas;

/**
 * Created by nesto on 2/05/2017.
 */

public interface VentasPresenter {
    void tomarFoto();
    void enviarDatos(String id, String imei, String correlativo, File pathFotos);
    void onEventMaintTread(RespuestaVentas respuestaVentas);
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    boolean eliminarArchivo(String path);
    void onStart();
    void onStop();
}
