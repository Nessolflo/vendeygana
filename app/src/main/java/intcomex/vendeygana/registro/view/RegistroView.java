package intcomex.vendeygana.registro.view;

import android.content.Context;

import intcomex.vendeygana.domain.ProgressView;

/**
 * Created by nesto on 25/04/2017.
 */

public interface RegistroView extends ProgressView {
    void enviarDatos(String nombre,
                     String fechaNacimiento,
                     String dpi,
                     String telefono,
                     String email,
                     String usuario,
                     String clave,
                     String validarClave);
    Context getContext();
    void abrirContenedor();
}
