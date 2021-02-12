package com.osmar.tcc_mobile.features.informações;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.osmar.tcc_mobile.R;
import com.osmar.tcc_mobile.model.Componente;

public class InfoActivity extends AppCompatActivity {
    private ImageView imgVoltar;
    private Componente componente;
    private EditText editComponenteName;
    private EditText editComponenteDescricao;
    private TextView txtPinoEscolhido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        imgVoltar=findViewById(R.id.imgVoltar2);
        editComponenteDescricao=findViewById(R.id.editTextComponenteDescriçãoInfo);
        editComponenteName=findViewById(R.id.editTextComponenteNameInfo);
        txtPinoEscolhido=findViewById(R.id.txtPinoEscolhidoInfo);
        Bundle dadosComponente =getIntent().getExtras();
        componente=(Componente)dadosComponente.getSerializable("componente");

        editComponenteName.setText(componente.getNome());
        editComponenteDescricao.setText(componente.getDescrição());
        txtPinoEscolhido.setText(componente.getPino().toString());
        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}