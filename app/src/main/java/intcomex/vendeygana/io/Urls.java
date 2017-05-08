package intcomex.vendeygana.io;

import com.google.gson.JsonElement;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nesto on 30/07/2016.
 */
public interface Urls {

    @POST(UrlsConstants.URL_REGISTRO)
    Call<JsonElement> registroMovil(@Query(UrlsConstants.NOMBRE) String nombre,
                                    @Query(UrlsConstants.FECHANACIMIENTO) String fechanacimiento,
                                    @Query(UrlsConstants.DPI) String dpi,
                                    @Query(UrlsConstants.TELEFONO) String telefono,
                                    @Query(UrlsConstants.EMAIL) String email,
                                    @Query(UrlsConstants.USUARIO) String usuario,
                                    @Query(UrlsConstants.PASSWORD) String password
    );


    @POST(UrlsConstants.URL_LOGIN)
    Call<JsonElement> loginMovil(@Query(UrlsConstants.USUARIO) String usuario,
                                 @Query(UrlsConstants.PASSWORD) String password,
                                 @Query(UrlsConstants.VERSION) String version
    );

    @GET(UrlsConstants.URL_CONSULTAR_PUNTOS)
    Call<JsonElement> consultarPuntos(@Path("idvendedor") String idvendedor);

    @Multipart
    @POST(UrlsConstants.URL_REGISTRAR_VENTA)
    Call<JsonElement> registrarVenta(
            @Part(UrlsConstants.FOTO + "\"; filename=\"pp.jpg\" ") RequestBody file,
            @Query(UrlsConstants.ID_VENDEDOR) String idvendedor,
            @Query(UrlsConstants.IMEI) String imei,
            @Query(UrlsConstants.CORRELATIVO) String correlativo);

    @GET(UrlsConstants.URL_CATALOGO_PREMIO)
    Call<JsonElement> catalogoPremios(@Query(UrlsConstants.PUNTOS) String puntos);

    @GET(UrlsConstants.URL_CATALOGO_PROMOCIONES)
    Call<JsonElement> catalogoPromociones();

}
