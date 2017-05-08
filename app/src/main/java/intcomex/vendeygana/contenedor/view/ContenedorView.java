package intcomex.vendeygana.contenedor.view;

import android.app.Activity;

import intcomex.vendeygana.domain.ProgressView;

/**
 * Created by nesto on 2/05/2017.
 */

public interface ContenedorView extends ProgressView {
    Activity getActivityApp();
    void cerrarSesion();
}
