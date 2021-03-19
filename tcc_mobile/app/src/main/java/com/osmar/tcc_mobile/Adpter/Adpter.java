package com.osmar.tcc_mobile.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.ComponenteAdpter;
import com.osmar.tcc_mobile.model.ComponenteAdpterLed;

import java.util.ArrayList;

public class Adpter extends RecyclerView.Adapter<Adpter.MyViewHolder> {
    private ArrayList<ComponenteAdpter> listaComponentes;

    public Adpter(ArrayList<ComponenteAdpter> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_lista_componentes, parent, false);
        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ComponenteAdpter componente =listaComponentes.get(position);
        holder.nome_Componente.setText(componente.getName());
        holder.tipo.setText("Tipo"+componente.getType());
        holder.imagem_Estado.setImageResource(componente.getImgEstadoResource());

    }

    @Override
    public int getItemCount() {
        return listaComponentes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome_Componente;
        TextView tipo;
        ImageView imagem_Estado;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_Componente = itemView.findViewById(R.id.txtComponenteName);
            tipo = itemView.findViewById(R.id.txtTipoComponente);
            imagem_Estado = itemView.findViewById(R.id.imgButtonLiga);


        }
    }


}
