package intcomex.vendeygana.contenedor.fragments.premios.interactor;

import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;
import intcomex.vendeygana.contenedor.fragments.premios.repository.PremiosRepository;

/**
 * Created by nesto on 6/05/2017.
 */

public class Interactor implements PremiosInteractor {

    private PremiosRepository premiosRepository;

    public Interactor(PremiosRepository premiosRepository) {
        this.premiosRepository = premiosRepository;
    }

    @Override
    public void consultarPuntos(DatosPuntos datosPuntos) {
        premiosRepository.consultarPuntos(datosPuntos);
    }

    @Override
    public void consultarPremios(DatosPremios datosPremios) {
        premiosRepository.consultarPremios(datosPremios);
    }
}
