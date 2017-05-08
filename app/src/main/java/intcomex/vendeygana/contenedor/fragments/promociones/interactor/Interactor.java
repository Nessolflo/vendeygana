package intcomex.vendeygana.contenedor.fragments.promociones.interactor;

import intcomex.vendeygana.contenedor.fragments.promociones.entities.DatosPromociones;
import intcomex.vendeygana.contenedor.fragments.promociones.repository.PromocionesRepository;

/**
 * Created by nesto on 4/05/2017.
 */

public class Interactor implements PromocionesInteractor {
    private PromocionesRepository repository;

    public Interactor(PromocionesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void consultarDatos(DatosPromociones datosPromociones) {
        repository.consultarDatos(datosPromociones);
    }
}
