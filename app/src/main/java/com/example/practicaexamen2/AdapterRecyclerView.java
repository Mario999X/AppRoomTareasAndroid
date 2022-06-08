package com.example.practicaexamen2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaexamen2.data.DataRoomDB;
import com.example.practicaexamen2.data.TareaEntity;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private List<TareaEntity> tareaEntities;
    private Activity context;
    private DataRoomDB database;

    public AdapterRecyclerView(List<TareaEntity> tareaEntities, Activity context) {
        this.tareaEntities = tareaEntities;
        this.context = context;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TareaEntity item = tareaEntities.get(position);

        holder.tituloTLista.setText(item.getTitulo());
        holder.fechaTLista.setText(item.getFecha());
        holder.descripcionTLista.setText(item.getDescripcion());

        // Recoge la opcion spinner del fragmento y la comprueba, segun el dato seleccionado, se pondra una imagen
        String opcionSpinner = item.getPrioridad();

        switch (opcionSpinner) {
            case "Prioridad Baja":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_green);
                break;
            case "Prioridad Media":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_yellow);
                break;
            case "Prioridad Alta":
                holder.imagePrioridadLista.setImageResource(R.drawable.icon_circle_red);

        }

        holder.imagePrioridadLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // seleccionamos el id del item que queramos eliminar al pulsar sobre su imagen
                int sId = item.getId();
                // conseguimos la instancia de la base de datos
                database = DataRoomDB.getInstance(context);
                // ejecutamos el dao, en este caso, escogi eliminarlo en base al id
                database.dataDao().deleteTarea(sId);
                // limpiamos la lista, y volvemos a añadir los elementos restantes de la BBDD
                tareaEntities.clear();
                tareaEntities.addAll(database.dataDao().selectTareas());

                Toast.makeText(context, "Tarea Eliminada", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return tareaEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTLista, fechaTLista, descripcionTLista;
        ImageView imagePrioridadLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTLista = itemView.findViewById(R.id.tituloTLista);
            fechaTLista = itemView.findViewById(R.id.fechaTLista);
            descripcionTLista = itemView.findViewById(R.id.descripcionTLista);
            imagePrioridadLista = itemView.findViewById(R.id.imagePrioridadLista);
        }
    }
}
