package intcomex.vendeygana.io;

/**
 * Created by nesto on 30/07/2016.
 */
public class UrlsConstants {
    //http://190.151.129.244/ws/mensajes/crear?
    // telefono=49762571&tipo=1&codigo_vendedor=12345&
    // icc=ejemplo&imei=ejemplo 192.168.0.17

    public static final String IP = "http://190.95.226.85";
    public static final String PATH = "/Incentivos/public/ws/movil";
    public static final String URL_LOGIN = PATH + "/login";
    public static final String URL_REGISTRO = PATH + "/registrar";
    public static final String URL_CONSULTAR_PUNTOS = PATH + "/puntos_acumulados/{idvendedor}";
    public static final String URL_REGISTRAR_VENTA = PATH + "/registrar_venta";
    public static final String URL_CATALOGO_PREMIO = PATH + "/catalogo/premios";
    public static final String URL_CATALOGO_PROMOCIONES = PATH + "/catalogo/promociones";
    public static final String PATH_IMAGENES = "/Incentivos/public/premios";

    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String VERSION = "version";
    public static final String ID_SUPERVISOR = "id_supervisor";

    public static final String NOMBRE = "nombre";
    public static final String FECHANACIMIENTO = "fechanacimiento";
    public static final String DPI = "dpi";
    public static final String TELEFONO = "telefono";
    public static final String EMAIL = "email";

    public static final String PUNTOS = "puntos";
    public static final String ID_VENDEDOR = "id_vendedor";
    public static final String IMEI = "imei";
    public static final String CORRELATIVO = "correlativo";
    public static final String FOTO = "foto";

}
