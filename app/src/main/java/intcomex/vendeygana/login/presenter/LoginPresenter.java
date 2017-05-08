package intcomex.vendeygana.login.presenter;

import intcomex.vendeygana.login.entities.LoginDatos;
import intcomex.vendeygana.login.entities.RespuestaLogin;

/**
 * Created by nesto on 24/04/2017.
 */

public interface LoginPresenter {
    void validarDatos(LoginDatos loginDatos);
    void enviarDatos(LoginDatos loginDatos);
    void inicializar();
    void errorValidacion(int mensaje);
    void onStart();
    void onStop();
    void respuestaServidor(RespuestaLogin respuestaLogin);
}
