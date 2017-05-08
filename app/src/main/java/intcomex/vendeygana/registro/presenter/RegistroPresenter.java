package intcomex.vendeygana.registro.presenter;

import android.content.Context;

import intcomex.vendeygana.registro.entities.DatosRegistro;
import intcomex.vendeygana.registro.entities.RespuestaRegistro;

/**
 * Created by nesto on 25/04/2017.
 */

public interface RegistroPresenter {
    void enviarDatos(DatosRegistro datosRegistro);

    void validarCampos(String nombre,
                       String fechaNacimiento,
                       String dpi,
                       String telefono,
                       String email,
                       String usuario,
                       String clave,
                       String validarclave);

    void respuestaServidor(RespuestaRegistro respuestaRegistro);

    void onStart();

    void onStop();

    Context getContext();
}
