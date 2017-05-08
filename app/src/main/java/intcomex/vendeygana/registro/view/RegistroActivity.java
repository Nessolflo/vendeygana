package intcomex.vendeygana.registro.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import intcomex.vendeygana.R;
import intcomex.vendeygana.VendeGana;
import intcomex.vendeygana.contenedor.Contenedor;
import intcomex.vendeygana.registro.presenter.RegistroPresenter;

public class RegistroActivity extends AppCompatActivity implements RegistroView, DatePickerDialog.OnDateSetListener {

    @Bind(R.id.txtNombre)
    EditText txtNombre;
    @Bind(R.id.txtFechaNacimiento)
    EditText txtFechaNacimiento;
    @Bind(R.id.txtDPI)
    EditText txtDPI;
    @Bind(R.id.txtTelefono)
    EditText txtTelefono;
    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtUsuario)
    EditText txtUsuario;
    @Bind(R.id.txtPassword)
    EditText txtPassword;
    @Bind(R.id.btnRegistrar)
    Button btnRegistrar;
    @Bind(R.id.txtProgressTitle)
    TextView txtProgressTitle;
    @Bind(R.id.txtProgressSubTitle)
    TextView txtProgressSubTitle;
    @Bind(R.id.Progress)
    FrameLayout progress;
    @Bind(R.id.txtPasswordvalidacion)
    EditText txtPasswordvalidacion;

    private DatePickerDialog dialogofechas;

    @Inject
    RegistroPresenter registroPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        ((VendeGana) getApplication()).getRegistroComponent(this).inject(this);
        setToolbar();
        txtNombre.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_NEXT) {

                    abrirFecha();
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home || item.getItemId() == R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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
    public void showMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enviarDatos(String nombre, String fechaNacimiento, String dpi, String telefono, String email, String usuario, String clave, String validar) {
        registroPresenter.validarCampos(nombre,
                fechaNacimiento,
                dpi,
                telefono,
                email,
                usuario,
                clave,
                validar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registroPresenter.onStart();
    }

    @Override
    protected void onStop() {
        registroPresenter.onStop();
        super.onStop();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.btnRegistrar)
    public void registrar() {
        enviarDatos(txtNombre.getText().toString(),
                txtFechaNacimiento.getText().toString(),
                txtDPI.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtUsuario.getText().toString(),
                txtPassword.getText().toString(),
                txtPasswordvalidacion.getText().toString());
    }

    @OnClick(R.id.txtFechaNacimiento)
    public void abrirFecha() {
        if (dialogofechas != null)
            dialogofechas.dismiss();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialogofechas = new DatePickerDialog(this, this, year, month, day);
        dialogofechas.show();
        showMessage(getString(R.string.tips_cambiaranio));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        if (datePicker == dialogofechas.getDatePicker()) {
            StringBuilder fechaBuilder = new StringBuilder();
            StringBuilder fechaKeyBuilder = new StringBuilder();
            fechaKeyBuilder.append(year);
            if (dayOfMonth < 10)
                fechaBuilder.append("0" + dayOfMonth);
            else
                fechaBuilder.append(dayOfMonth);
            fechaBuilder.append("-");
            fechaKeyBuilder.append("-");
            month++;
            if (month < 10) {
                fechaBuilder.append("0" + month);
                fechaKeyBuilder.append("0" + month);
            } else {
                fechaBuilder.append(month);
                fechaKeyBuilder.append(month);
            }
            fechaBuilder.append("-");
            fechaKeyBuilder.append("-");
            fechaBuilder.append(year);
            if (dayOfMonth < 10)
                fechaKeyBuilder.append("0" + dayOfMonth);
            else
                fechaKeyBuilder.append(dayOfMonth);
            txtFechaNacimiento.setText(fechaBuilder.toString());
            txtFechaNacimiento.setTag(fechaKeyBuilder.toString());

            txtDPI.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtDPI, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void abrirContenedor() {
        Intent i = new Intent(this, Contenedor.class);
        startActivity(i);
        finish();
    }

    private void setToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }
}
