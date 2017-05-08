package intcomex.vendeygana.contenedor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import intcomex.vendeygana.R;
import intcomex.vendeygana.contenedor.fragments.premios.view.FragmentCatalogoPremios;
import intcomex.vendeygana.contenedor.fragments.promociones.view.FragmentCatalogoPromociones;
import intcomex.vendeygana.contenedor.fragments.venta.view.FragmentVenta;
import intcomex.vendeygana.contenedor.view.ContenedorView;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.login.view.Login;

public class Contenedor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ContenedorView {

    @Bind(R.id.appbar)
    Toolbar appbar;
    @Bind(R.id.content_frame)
    FrameLayout contentFrame;
    @Bind(R.id.navview)
    NavigationView navview;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.txtProgressTitle)
    TextView txtProgressTitle;
    @Bind(R.id.txtProgressSubTitle)
    TextView txtProgressSubTitle;
    @Bind(R.id.Progress)
    FrameLayout progress;
    private TextView txtUsuario;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setSupportActionBar(appbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        try {
            txtUsuario = (TextView) navview.getHeaderView(0).findViewById(R.id.txtUsuario);
            String usuario = sharedPreferences.getString(JsonKeys.KEY_NOMBRE, "").split(" ")[0];
            txtUsuario.setText(String.format(getString(R.string.bienvenido), usuario));
        }catch (Exception e){
            e.printStackTrace();
        }
        navview.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navview.getMenu().getItem(0));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.menu_registrarventa:
                fragment = FragmentVenta.newInstance();
                fragmentTransaction = true;
                break;
            case R.id.menu_catalogopromociones:
                fragment = FragmentCatalogoPromociones.newInstance();
                fragmentTransaction = true;
                break;
            case R.id.menu_calatogopremios:
                fragment = FragmentCatalogoPremios.newInstance();
                fragmentTransaction = true;
                break;
            case R.id.menu_cerrarsesion:
                fragmentTransaction = false;
                cerrarSesion();
                break;
        }

        if (fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            menuItem.setChecked(true);
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(menuItem.getTitle());
        }

        drawerLayout.closeDrawers();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        hideProgress();
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
        showProgress();
    }

    @Override
    public void showMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity getActivityApp() {
        return this;
    }


    @Override
    public void cerrarSesion() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(JsonKeys.KEY_USUARIO);
        editor.remove(JsonKeys.KEY_CLAVE);
        editor.apply();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}
