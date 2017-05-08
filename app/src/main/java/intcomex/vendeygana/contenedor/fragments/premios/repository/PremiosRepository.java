package intcomex.vendeygana.contenedor.fragments.premios.repository;

import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPremios;
import intcomex.vendeygana.contenedor.fragments.premios.entities.DatosPuntos;

/**
 * Created by nesto on 4/05/2017.
 */

public interface PremiosRepository {
    void consultarPuntos(DatosPuntos datosPuntos);
    void consultarPremios(DatosPremios datosPremios);
}
