package intcomex.vendeygana.contenedor.fragments.venta.view;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import intcomex.vendeygana.R;
import intcomex.vendeygana.VendeGana;
import intcomex.vendeygana.contenedor.fragments.venta.presenter.VentasPresenter;
import intcomex.vendeygana.contenedor.view.ContenedorView;
import intcomex.vendeygana.domain.GlideImageLoader;
import intcomex.vendeygana.domain.ImageLoader;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.domain.permisos.PermisoObj;
import intcomex.vendeygana.domain.permisos.Permisos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentVenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVenta extends Fragment implements VentaView {


    public static final int REQUEST_TAKE_PHOTO = 2;

    public static final int VALIDACION_IMEI = 1;
    public static final int VALIDACION_CORRELATIVO = 2;
    public static final int VALIDACION_FOTO = 3;

    private static final int REQ_HEIGHT = 800;
    private static final int REQ_WIDTH = 800;

    @Bind(R.id.imgFoto)
    CircleImageView imgFoto;
    @Bind(R.id.inputImei)
    EditText inputImei;
    @Bind(R.id.inputCorrelativo)
    EditText inputCorrelativo;
    @Bind(R.id.btnBorrar)
    Button btnBorrar;
    @Bind(R.id.btnEnviar)
    Button btnEnviar;
    @Bind(R.id.lblFoto)
    TextView lblFoto;
    @Bind(R.id.linearVenta)
    LinearLayout linearVenta;
    private ContenedorView contenedorView;

    @Inject
    VentasPresenter ventasPresenter;

    //path
    private String mCurrentPhotoPath = "";
    //File
    private File fileFoto;

    private SharedPreferences sharedPreferences;

    private Permisos permisos;

    public FragmentVenta() {
        // Required empty public constructor
    }


    public static FragmentVenta newInstance() {
        FragmentVenta fragment = new FragmentVenta();
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
        View view = inflater.inflate(R.layout.fragment_venta, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((VendeGana) getActivity().getApplication()).getVentasComponent(getActivity(), this).inject(this);
        contenedorView = ((ContenedorView) getActivity());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ventasPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showProgress() {
        getContenedorView().showProgress();
    }

    @Override
    public void hideProgress() {
        getContenedorView().hideProgress();
    }


    @Override
    public void onStart() {
        super.onStart();
        ventasPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        ventasPresenter.onStop();
    }

    @Override
    public void progressMessage(String title, String mensaje) {
        getContenedorView().progressMessage(title, mensaje);
    }

    @Override
    public void showMessage(String mensaje) {
        getContenedorView().showMessage(mensaje);
    }

    public ContenedorView getContenedorView() {
        return contenedorView;
    }

    public void setContenedorView(ContenedorView contenedorView) {
        this.contenedorView = contenedorView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    @OnClick(R.id.imgFoto)
    public void tomarFoto() {
        ventasPresenter.tomarFoto();
    }

    @Override
    public void bloquearInputs() {

        inputCorrelativo.setEnabled(false);
        inputImei.setEnabled(false);
        imgFoto.setEnabled(false);
        btnEnviar.setEnabled(false);
        btnBorrar.setEnabled(false);

    }

    @Override
    public void desbloquearInputs() {
        inputCorrelativo.setEnabled(true);
        inputImei.setEnabled(true);
        imgFoto.setEnabled(true);
        btnEnviar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    @Override
    public void validacion(int campo) {
        if (campo == VALIDACION_IMEI)
            showMessage(getString(R.string.validaciones_imei));
        else if (campo == VALIDACION_CORRELATIVO)
            showMessage(getString(R.string.validaciones_correlativo));
        else if (campo == VALIDACION_FOTO)
            showMessage(getString(R.string.validaciones_foto));
    }

    @Override
    public void errorPermisos(int tipo, final View.OnClickListener listener) {
        String mensaje = getString(R.string.permisos_fotografia);
        final ForegroundColorSpan whiteSpan = new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.rojo));
        final SpannableStringBuilder snackbarText = new SpannableStringBuilder(mensaje);
        snackbarText.setSpan(whiteSpan, 0, snackbarText.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        final Snackbar snackbar = Snackbar.make(linearVenta, snackbarText, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.intentadenuevo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    @OnClick(R.id.btnBorrar)
    public void limpiarInputs() {
        inputCorrelativo.setText("");
        inputImei.setText("");
        mCurrentPhotoPath = "";
        fileFoto = null;
        imgFoto.setImageResource(R.drawable.ic_camara);
        lblFoto.setText(getString(R.string.help_photo));
        inputImei.requestFocus();
    }

    @Override
    @OnClick(R.id.btnEnviar)
    public void enviarDatos() {
        showProgress();
        ventasPresenter.enviarDatos(String.valueOf(sharedPreferences.getInt(JsonKeys.KEY_ID,0)), inputImei.getText().toString(), inputCorrelativo.getText().toString(), fileFoto);
    }

    @Override
    public void abrirCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "intcomex.vendeygana",
                        photoFile);
                List<ResolveInfo> resolvedIntentActivities = getContext().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                    String packageName = resolvedIntentInfo.activityInfo.packageName;

                    getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void setImage(Uri uri) {
        if (mCurrentPhotoPath.length() > 0) {
            if (ventasPresenter.eliminarArchivo(mCurrentPhotoPath)) {
                mCurrentPhotoPath = "file:" + uri.getPath();
                ImageLoader imageLoader = new GlideImageLoader(getActivity());
                imageLoader.load(imgFoto, mCurrentPhotoPath);
                fileFoto = new File(uri.getPath());
                lblFoto.setText(getString(R.string.help_photo_2));
            }

        } else if (uri != null) {
            mCurrentPhotoPath = "file:" + uri.getPath();
            ImageLoader imageLoader = new GlideImageLoader(getActivity());
            imageLoader.load(imgFoto, mCurrentPhotoPath);
            fileFoto = new File(uri.getPath());
            lblFoto.setText(getString(R.string.help_photo_2));
        }
    }

    @Override
    public void openCrop() {
        if (mCurrentPhotoPath.length() > 0)
            CropImage.activity(Uri.parse(mCurrentPhotoPath)).setAspectRatio(2, 3).setCropShape(CropImageView.CropShape.RECTANGLE).setRequestedSize(REQ_WIDTH, REQ_HEIGHT)
                    .start(getContext(), this);
    }

    @Override
    public void openCrop(Uri uri) {
        CropImage.activity(uri).setAspectRatio(2, 3).setCropShape(CropImageView.CropShape.RECTANGLE).setRequestedSize(REQ_WIDTH, REQ_HEIGHT)
                .start(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ventasPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Activity getActivityApp() {
        return contenedorView.getActivityApp();
    }

    @Override
    public File createImageFile() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = "file:" + image.getAbsolutePath();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Permisos getPermisos() {
        return permisos;
    }
}
