package intcomex.vendeygana.login.view;

import android.content.Context;

import intcomex.vendeygana.domain.ProgressView;

/**
 * Created by nesto on 24/04/2017.
 */

public interface LoginView extends ProgressView {
    void enviarDatos(String usuario, String clave);

    void abrirRegistro();

    void abrirContenedor();

    Context getContext();
}
