package intcomex.vendeygana.login.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import intcomex.vendeygana.BuildConfig;
import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.login.entities.LoginDatos;
import intcomex.vendeygana.login.entities.RespuestaLogin;
import intcomex.vendeygana.login.interactor.LoginInteractor;
import intcomex.vendeygana.login.view.LoginView;

/**
 * Created by nesto on 24/04/2017.
 */

public class Presenter implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Presenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(loginView.getContext());
        editor = sharedPreferences.edit();
        inicializar();
    }

    @Override
    public void validarDatos(LoginDatos loginDatos) {
        if (loginDatos != null) {
            if(loginDatos.getUsuario()== null || loginDatos.getUsuario().isEmpty())
                errorValidacion(R.string.usuarioobligatorio);
            else if(loginDatos.getClave() == null || loginDatos.getClave().isEmpty())
                errorValidacion(R.string.claveobligatoria);
            else
                enviarDatos(loginDatos);

        } else
            errorValidacion(R.string.camposobligatorios);
    }

    @Override
    public void enviarDatos(LoginDatos loginDatos) {
        editor.putString(JsonKeys.KEY_USUARIO, loginDatos.getUsuario());
        editor.putString(JsonKeys.KEY_CLAVE, loginDatos.getClave());
        loginInteractor.enviarDatos(loginDatos);
    }

    @Override
    public void inicializar() {
        String usuario = sharedPreferences.getString(JsonKeys.KEY_USUARIO, "");
        String clave = sharedPreferences.getString(JsonKeys.KEY_CLAVE, "");
        if(!usuario.isEmpty() && !clave.isEmpty()) {
            loginView.showProgress();
            validarDatos(new LoginDatos(loginView.getContext(), usuario, clave, String.valueOf(BuildConfig.VERSION_CODE)));
        }
    }

    @Override
    public void errorValidacion(int mensaje) {
        loginView.hideProgress();
        loginView.showMessage(loginView.getContext().getString(mensaje));
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    @Subscribe
    public void respuestaServidor(RespuestaLogin respuestaLogin) {
        JsonObject json = respuestaLogin.getJsonObject();
        loginView.hideProgress();
        loginView.showMessage(json.get(JsonKeys.JSON_MESSAGE).getAsString());
        if(json.get(JsonKeys.JSON_RESULT).getAsBoolean()){
            loginView.abrirContenedor();
            editor.putInt(JsonKeys.KEY_ID, json.get(JsonKeys.JSON_RECORDS).getAsJsonObject().get(JsonKeys.KEY_ID).getAsInt());
            editor.putString(JsonKeys.KEY_NOMBRE, json.get(JsonKeys.JSON_RECORDS).getAsJsonObject().get(JsonKeys.KEY_NOMBRE).getAsString());
            editor.apply();
        }
    }
}
