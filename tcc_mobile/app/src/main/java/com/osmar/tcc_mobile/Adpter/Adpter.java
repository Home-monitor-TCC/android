package com.osmar.tcc_mobile.Adpter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_lista_componentes, parent, false);
        //itemLista.setBackgroundColor(R.color.cinzaEscuro);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ComponenteAdpter componente =listaComponentes.get(position);
        holder.nome_Componente.setText(componente.getName());

        if(componente.getType() == 1){
            holder.tipo.setText("LED");
            holder.imagem_Estado.setImageResource(R.drawable.ic_btn_switch_on);
        }else{
            holder.tipo.setText("Sensor de temperatura");
            holder.imagem_Estado.setImageResource(R.drawable.ic_icon_awesome_temperature_low);
        }


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
