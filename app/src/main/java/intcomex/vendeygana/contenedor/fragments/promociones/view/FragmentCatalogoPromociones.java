package intcomex.vendeygana.contenedor.fragments.promociones.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import intcomex.vendeygana.R;
import intcomex.vendeygana.VendeGana;
import intcomex.vendeygana.contenedor.fragments.promociones.adapters.AdaptadorPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.presenter.PromocionesPresenter;
import intcomex.vendeygana.contenedor.view.ContenedorView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCatalogoPromociones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCatalogoPromociones extends Fragment implements PromocionesView {


    @Bind(R.id.listaPromociones)
    ListView listaPromociones;

    @Inject
    PromocionesPresenter presenter;

    private ContenedorView contenedorView;


    private AdaptadorPromociones adaptadorPromociones;
    public FragmentCatalogoPromociones() {
        // Required empty public constructor
    }


    public static FragmentCatalogoPromociones newInstance() {
        FragmentCatalogoPromociones fragment = new FragmentCatalogoPromociones();
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
        View view = inflater.inflate(R.layout.fragment_catalogo_promociones, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((VendeGana) getActivity().getApplication()).getPromocionesComponent(this).inject(this);
        contenedorView = ((ContenedorView) getActivity());
        consultarDatos(new DatosPromociones(getContext()));
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
    public void consultarDatos(DatosPromociones datosPromociones) {
        showProgress();
        presenter.consultarDatos(datosPromociones);
    }

    @Override
    public void setAdapter(JsonArray jsonArray) {
        hideProgress();
        if(adaptadorPromociones==null){
            adaptadorPromociones = new AdaptadorPromociones(getContext(), jsonArray);
            listaPromociones.setAdapter(adaptadorPromociones);
        }else{
            adaptadorPromociones.setJsonArray(jsonArray);
            adaptadorPromociones.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }
}
