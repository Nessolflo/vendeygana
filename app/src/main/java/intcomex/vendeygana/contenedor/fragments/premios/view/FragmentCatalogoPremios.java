package intcomex.vendeygana.contenedor.fragments.premios.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import intcomex.vendeygana.R;
import intcomex.vendeygana.VendeGana;
import intcomex.vendeygana.contenedor.Contenedor;
import intcomex.vendeygana.contenedor.fragments.premios.adapters.AdaptadorPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.presenter.PremiosPresenter;
import intcomex.vendeygana.contenedor.view.ContenedorView;
import intcomex.vendeygana.domain.JsonKeys;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCatalogoPremios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCatalogoPremios extends Fragment implements PremiosView {
    @Bind(R.id.txtPuntos)
    TextView txtPuntos;
    @Bind(R.id.switchFiltro)
    Switch switchFiltro;
    @Bind(R.id.lyLista)
    RecyclerView lyLista;
    @Bind(R.id.encabezado)
    LinearLayout encabezado;

    @Inject
    PremiosPresenter premiosPresenter;

    private SharedPreferences sharedPreferences;
    private AdaptadorPremios adaptadorPremios;
    private ContenedorView contenedorView;

    public FragmentCatalogoPremios() {
        // Required empty public constructor
    }

    public static FragmentCatalogoPremios newInstance() {
        FragmentCatalogoPremios fragment = new FragmentCatalogoPremios();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogo_premios, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contenedorView = (Contenedor) getActivity();
        ((VendeGana) getActivity().getApplication()).getPremiosComponent(this).inject(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        premiosPresenter.consultarPuntos(new DatosPuntos(view.getContext(),
                String.valueOf(sharedPreferences.getInt(JsonKeys.KEY_ID,0))));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showProgress() {
        contenedorView.showProgress();
    }

    @Override
    public void hideProgress() {
        contenedorView.hideProgress();
    }

    @Override
    public void progressMessage(String title, String mensaje) {
        contenedorView.progressMessage(title, mensaje);
    }

    @Override
    public void showMessage(String mensaje) {
        contenedorView.showMessage(mensaje);
    }

    @Override
    public void setAdaptador(JsonArray premios) {
        if(adaptadorPremios == null){
            adaptadorPremios = new AdaptadorPremios(getContext(), premios);
            lyLista.setHasFixedSize(true);
            lyLista.setAdapter(adaptadorPremios);
            lyLista.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else
            adaptadorPremios.setPremios(premios);
    }

    @Override
    public void setPuntos(JsonObject vendedor) {
        String puntos = vendedor.get(JsonKeys.KEY_PUNTOS_ACUMULADOS).getAsString();
        txtPuntos.setText(puntos);
        txtPuntos.setTag(puntos);
    }

    @Override
    @OnCheckedChanged(R.id.switchFiltro)
    public void filtrarPuntos(boolean checked) {
        if(checked)
            premiosPresenter.consultarPremios(new DatosPremios(getContext(), txtPuntos.getTag().toString()));
        else
            premiosPresenter.consultarPremios(new DatosPremios(getContext(), ""));
    }

    @Override
    public void onStart() {
        super.onStart();
        premiosPresenter.onStart();
    }

    @Override
    public void onStop() {
        premiosPresenter.onStop();
        super.onStop();

    }

    @Override
    public Context obtenerContexto() {
        return getContext();
    }
}
