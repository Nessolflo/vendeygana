package intcomex.vendeygana.contenedor.fragments.venta.interactor;

import java.io.File;

import intcomex.vendeygana.contenedor.fragments.venta.entities.VentasDatos;
import intcomex.vendeygana.contenedor.fragments.venta.repository.VentasRepository;

/**
 * Created by nesto on 2/05/2017.
 */

public class Interactor implements VentasInteractor {

    private VentasRepository repository;

    public Interactor(VentasRepository repository) {
        this.repository = repository;
    }

    @Override
    public void enviarDatos(VentasDatos ventasDatos) {
        repository.enviarDatos(ventasDatos);
    }
}
