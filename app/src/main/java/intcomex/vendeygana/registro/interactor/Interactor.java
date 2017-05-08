package intcomex.vendeygana.registro.interactor;

import intcomex.vendeygana.registro.entities.DatosRegistro;
import intcomex.vendeygana.registro.repository.RegistroRepository;

/**
 * Created by nesto on 25/04/2017.
 */

public class Interactor implements RegistroInteractor {

    private RegistroRepository registroRepository;

    public Interactor(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    @Override
    public void enviarDatos(DatosRegistro datosRegistro) {
        registroRepository.enviarDatos(datosRegistro);
    }
}
