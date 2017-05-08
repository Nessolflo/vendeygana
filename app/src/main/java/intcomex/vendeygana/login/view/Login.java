package intcomex.vendeygana.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import intcomex.vendeygana.BuildConfig;
import intcomex.vendeygana.R;
import intcomex.vendeygana.VendeGana;
import intcomex.vendeygana.contenedor.Contenedor;
import intcomex.vendeygana.login.entities.LoginDatos;
import intcomex.vendeygana.login.presenter.LoginPresenter;
import intcomex.vendeygana.registro.view.RegistroActivity;

public class Login extends AppCompatActivity implements LoginView {

    @Bind(R.id.txtUsuario)
    EditText txtUsuario;
    @Bind(R.id.txtClave)
    EditText txtClave;
    @Bind(R.id.btnIngresar)
    Button btnIngresar;
    @Bind(R.id.btnRegistrar)
    Button btnRegistrar;
    @Bind(R.id.txtProgressTitle)
    TextView txtProgressTitle;
    @Bind(R.id.txtProgressSubTitle)
    TextView txtProgressSubTitle;
    @Bind(R.id.Progress)
    FrameLayout progress;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupInject();
    }

    private void setupInject() {
        ((VendeGana) getApplication()).getLoginComponent(this).inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideProgress();
        loginPresenter.onStart();
    }

    @Override
    protected void onStop() {
        loginPresenter.onStop();
        super.onStop();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void progressMessage(String title, String mensaje) {
        txtProgressTitle.setText(title);
        txtProgressSubTitle.setText(mensaje);
    }

    @Override
    public void enviarDatos(String usuario, String clave) {
        showProgress();
        loginPresenter.validarDatos((new LoginDatos(this, usuario, clave, String.valueOf(BuildConfig.VERSION_CODE))));
    }

    @OnClick(R.id.btnIngresar)
    void onClick() {
        enviarDatos(txtUsuario.getText().toString(), txtClave.getText().toString());
    }

    @Override
    public void showMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    @OnClick(R.id.btnRegistrar)
    public void abrirRegistro() {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    @Override
    public void abrirContenedor() {
        Intent i = new Intent(this, Contenedor.class);
        startActivity(i);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
