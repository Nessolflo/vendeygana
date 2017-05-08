package intcomex.vendeygana.contenedor.fragments.venta.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import intcomex.vendeygana.R;
import intcomex.vendeygana.contenedor.fragments.venta.entities.RespuestaVentas;
import intcomex.vendeygana.contenedor.fragments.venta.entities.VentasDatos;
import intcomex.vendeygana.contenedor.fragments.venta.interactor.VentasInteractor;
import intcomex.vendeygana.contenedor.fragments.venta.view.FragmentVenta;
import intcomex.vendeygana.contenedor.fragments.venta.view.VentaView;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.domain.permisos.Permisos;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nesto on 2/05/2017.
 */

public class Presenter implements VentasPresenter {

    private VentaView ventaView;
    private VentasInteractor ventasInteractor;
    private Permisos permisos;

    public Presenter(VentaView ventaView, Permisos permisos, VentasInteractor ventasInteractor) {
        this.ventaView = ventaView;
        this.ventasInteractor = ventasInteractor;
        this.permisos = permisos;
    }

    @Override
    public void tomarFoto() {
        if (permisos.comprobarPermisos())
            ventaView.abrirCamara();
        else
            permisos.solicitarPermisos();
    }

    @Override
    public void enviarDatos(String id, String imei, String correlativo, File pathFotos) {
        ventasInteractor.enviarDatos(new VentasDatos(ventaView.getActivityApp(), id, imei, correlativo, pathFotos));
    }

    @Override
    @Subscribe
    public void onEventMaintTread(RespuestaVentas respuestaVentas) {
        JsonObject json = respuestaVentas.getJsonRespuesta();
        ventaView.hideProgress();
        ventaView.desbloquearInputs();
        ventaView.showMessage(json.get(JsonKeys.JSON_MESSAGE).getAsString());
        if(json.get(JsonKeys.JSON_RESULT).getAsBoolean()){
            ventaView.limpiarInputs();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FragmentVenta.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            ventaView.openCrop();
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
                ventaView.setImage(result.getUri());
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                ventaView.showMessage(error.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (permisos.onRequestPermissionsResult(requestCode, permissions, grantResults))
            ventaView.abrirCamara();
        else {
            ventaView.errorPermisos(R.string.permisos_fotografia, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    permisos.solicitarPermisos();
                }
            });
        }
    }

    @Override
    public boolean eliminarArchivo(String path) {
        try {
            path = path.replace("file:", "");
            File file = new File(path);
            if (file.exists())
                return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }
}
