package intcomex.vendeygana.domain;

/**
 * Created by nesto on 23/04/2017.
 */

public interface ProgressView {
    void showProgress();
    void hideProgress();
    void progressMessage(String title, String mensaje);
    void showMessage(String mensaje);
}
