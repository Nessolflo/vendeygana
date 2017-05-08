package intcomex.vendeygana.contenedor.fragments.venta.view;

import android.app.Activity;
import android.net.Uri;
import android.view.View;

import java.io.File;

import intcomex.vendeygana.domain.ProgressView;
import intcomex.vendeygana.domain.permisos.Permisos;

/**
 * Created by nesto on 2/05/2017.
 */

public interface VentaView extends ProgressView {
    void tomarFoto();
    void bloquearInputs();
    void desbloquearInputs();
    void validacion(int campo);
    void errorPermisos(int mensaje, View.OnClickListener listener);
    void limpiarInputs();
    void enviarDatos();
    void abrirCamara();
    void setImage(Uri uri);
    void openCrop();
    void openCrop(Uri uri);
    Permisos getPermisos();
    Activity getActivityApp();
    File createImageFile();
}
