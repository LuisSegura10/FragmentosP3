package mx.com.softwell.fragmentos.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.com.softwell.fragmentos.R;
import mx.com.softwell.fragmentos.gui.Categorias2;
import mx.com.softwell.fragmentos.model.Categorias;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {

    private List<Categorias> categorias;
    private Context context;

    public CategoriasAdapter(List<Categorias> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categorias,parent,false);
        context = parent.getContext();
        return new CategoriasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorias categorias2 = categorias.get(position);
        Picasso.get().load(categorias2.getImagen()).resize(300, 150).into(holder.imgCategoria);
        holder.txtTitulo.setText(categorias2.getNombre());

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatImageView imgCategoria;
        private TextView txtTitulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategoria = itemView.findViewById(R.id.imagen);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            this.view = itemView;
        }
    }
}

