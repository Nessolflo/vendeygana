package intcomex.vendeygana.registro.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.registro.entities.DatosRegistro;
import intcomex.vendeygana.registro.entities.RespuestaRegistro;
import intcomex.vendeygana.registro.interactor.RegistroInteractor;
import intcomex.vendeygana.registro.view.RegistroView;

/**
 * Created by nesto on 25/04/2017.
 */

public class Presenter implements RegistroPresenter {

    private RegistroInteractor registroInteractor;
    private RegistroView registroView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Presenter(RegistroInteractor registroInteractor, RegistroView registroView) {
        this.registroInteractor = registroInteractor;
        this.registroView = registroView;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(registroView.getContext());
        editor = sharedPreferences.edit();
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
    public void enviarDatos(DatosRegistro datosRegistro) {
        registroView.showProgress();
        registroInteractor.enviarDatos(datosRegistro);
    }

    @Override
    public void validarCampos(String nombre, String fechaNacimiento, String dpi, String telefono, String email, String usuario, String clave, String validar) {
        if(nombre.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_nombre));
        else if(fechaNacimiento.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_fechanacimiento));
        else if(dpi.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_dpi));
        else if(telefono.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_telefono));
        else if(!isValidEmail(email))
            registroView.showMessage(getContext().getString(R.string.validaciones_email));
        else if(usuario.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_usuario));
        else if(clave.isEmpty())
            registroView.showMessage(getContext().getString(R.string.validaciones_clave));
        else if(!clave.equals(validar))
            registroView.showMessage(getContext().getString(R.string.validaciones_clave_nocoincide));
        else{//Esta correcto
            DatosRegistro datosRegistro = new DatosRegistro(
                    nombre,
                    fechaNacimiento,
                    dpi,
                    telefono,
                    email,
                    usuario,
                    clave,
                    getContext()
                    );
            enviarDatos(datosRegistro);
        }
    }

    @Override
    @Subscribe
    public void respuestaServidor(RespuestaRegistro respuestaRegistro) {
        JsonObject json = respuestaRegistro.getJsonObject();
        registroView.hideProgress();
        registroView.showMessage(json.get(JsonKeys.JSON_MESSAGE).getAsString());
        if(json.get(JsonKeys.JSON_RESULT).getAsBoolean()){
            editor.putInt(JsonKeys.KEY_ID, json.get(JsonKeys.JSON_RECORDS).getAsJsonObject().get(JsonKeys.KEY_ID).getAsInt());
            editor.apply();
            registroView.abrirContenedor();
        }
    }

    @Override
    public Context getContext() {
        return registroView.getContext();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
