package intcomex.vendeygana.contenedor.fragments.promociones.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.JsonKeys;

/**
 * Created by nesto on 4/05/2017.
 */

public class AdaptadorPromociones extends BaseAdapter {

    private Context context;
    private LayoutInflater linflater;
    private JsonArray jsonArray;

    public AdaptadorPromociones(Context context, JsonArray jsonArray) {
        this.context = context;
        this.linflater = LayoutInflater.from(context);
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.size();
    }

    @Override
    public Object getItem(int i) {
        return jsonArray.get(i).getAsJsonObject();
    }

    @Override
    public long getItemId(int i) {
        return jsonArray.get(i).getAsJsonObject().get(JsonKeys.KEY_ID).getAsLong();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        JsonObject object = jsonArray.get(i).getAsJsonObject();
        Item item;
        if (view == null) {
            view = linflater.inflate(R.layout.item_promocion_solo_texto, viewGroup, false);
            item = new Item();
            item.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
            item.txtFechas = (TextView) view.findViewById(R.id.txtFechas);
            item.txtPuntos = (TextView) view.findViewById(R.id.txtPuntos);
            view.setTag(item);
        } else
            item = (Item) view.getTag();

        item.txtPuntos.setText(object.get(JsonKeys.KEY_PUNTOS).getAsString());
        item.txtDescripcion.setText(object.get(JsonKeys.KEY_DESCRIPCION).getAsString());
        item.txtFechas.setText(String.format(context.getString(R.string.validahasta),
                object.get(JsonKeys.KEY_FECHA_INICIO).getAsString(),
                object.get(JsonKeys.KEY_FECHA_FIN).getAsString()));

        return view;
    }

    private class Item {
        TextView txtDescripcion;
        TextView txtFechas;
        TextView txtPuntos;
    }

    public JsonArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
