package intcomex.vendeygana.contenedor.fragments.premios.interactor;

import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PremiosInteractor {
    void consultarPuntos(DatosPuntos datosPuntos);
    void consultarPremios(DatosPremios datosPremios);
}
