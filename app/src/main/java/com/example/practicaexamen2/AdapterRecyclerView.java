package com.example.practicaexamen2;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

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

        String opcionSpinner = item.getPrioridad();

        switch (opcionSpinner){
            case "Prioridad Baja":
                holder.imagePrioridadLista.setBackgroundResource(R.drawable.icon_circle_green);
                break;
            case "Prioridad Media":
                holder.imagePrioridadLista.setBackgroundResource(R.drawable.icon_circle_yellow);
                break;
            case "Prioridad Alta":
                holder.imagePrioridadLista.setBackgroundResource(R.drawable.icon_circle_red);

        }

    }

    @Override
    public int getItemCount() {
        return tareaEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
