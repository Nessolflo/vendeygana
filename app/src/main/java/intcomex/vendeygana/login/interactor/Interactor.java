package intcomex.vendeygana.login.interactor;

import intcomex.vendeygana.login.entities.LoginDatos;
import intcomex.vendeygana.login.repository.LoginRepository;

/**
 * Created by nesto on 24/04/2017.
 */

public class Interactor implements LoginInteractor {
    private LoginRepository loginRepository;

    public Interactor(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void enviarDatos(LoginDatos loginDatos) {
        loginRepository.enviarDatos(loginDatos);
    }
}
