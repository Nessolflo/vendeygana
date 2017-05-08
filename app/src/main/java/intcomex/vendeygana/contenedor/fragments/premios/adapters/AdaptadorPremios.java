package intcomex.vendeygana.contenedor.fragments.premios.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import intcomex.vendeygana.R;
import intcomex.vendeygana.domain.GlideImageLoader;
import intcomex.vendeygana.domain.ImageLoader;
import intcomex.vendeygana.domain.JsonKeys;
import intcomex.vendeygana.io.UrlsConstants;

/**
 * Created by nesto on 6/05/2017.
 */

public class AdaptadorPremios extends RecyclerView.Adapter<AdaptadorPremios.PremioViewHolder> {


    private JsonArray premios;
    private Context context;

    public AdaptadorPremios(Context context, JsonArray premios) {
        this.premios = premios;
        this.context = context;
    }

    @Override
    public PremioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_premios, parent, false);

        PremioViewHolder pvh = new PremioViewHolder(itemView);

        return pvh;
    }

    @Override
    public void onBindViewHolder(PremioViewHolder holder, int position) {
        JsonObject premio = premios.get(position).getAsJsonObject();
        holder.bindPremio(premio);
        holder.txtPuntos.setText(String.format(context.getString(R.string.cantidadpuntos),
                premio.get(JsonKeys.KEY_PUNTOS).getAsString()));
        StringBuilder url = new StringBuilder();
        url.append(UrlsConstants.IP);
        url.append(UrlsConstants.PATH_IMAGENES);
        url.append("/");
        url.append(premio.get(JsonKeys.KEY_FOTO).getAsString());
        Log.e(getClass().getName(), url.toString());
        Glide.with(context).load(url.toString()).into(holder.getImgPremio());
    }

    @Override
    public int getItemCount() {
        return premios.size();
    }

    public static class PremioViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPremio;
        private TextView txtPuntos;
        private TextView txtDescripcion;

        public PremioViewHolder(View itemView) {
            super(itemView);

            imgPremio = (ImageView) itemView.findViewById(R.id.imgItem);
            txtPuntos = (TextView) itemView.findViewById(R.id.txtPuntos);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        }

        public void bindPremio(JsonObject json) {
            txtDescripcion.setText(json.get(JsonKeys.KEY_DESCRIPCION).getAsString());

        }

        public ImageView getImgPremio() {
            return imgPremio;
        }

        public void setImgPremio(ImageView imgPremio) {
            this.imgPremio = imgPremio;
        }

        public TextView getTxtPuntos() {
            return txtPuntos;
        }

        public void setTxtPuntos(TextView txtPuntos) {
            this.txtPuntos = txtPuntos;
        }

        public TextView getTxtDescripcion() {
            return txtDescripcion;
        }

        public void setTxtDescripcion(TextView txtDescripcion) {
            this.txtDescripcion = txtDescripcion;
        }
    }

    public JsonArray getPremios() {
        return premios;
    }

    public void setPremios(JsonArray premios) {
        this.premios = premios;
        notifyDataSetChanged();
    }
}
