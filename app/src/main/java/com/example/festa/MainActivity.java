package com.example.festa;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String ENDERECO = "Rua Formosa, Vargem Grande, MA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMapa = findViewById(R.id.btnMapa);

        btnMapa.setOnClickListener(v -> abrirMapa());
    }

    private void abrirMapa() {
        String enderecoCodificado = Uri.encode(ENDERECO);

        // Tenta abrir no aplicativo de mapas
        Intent mapa = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + enderecoCodificado));
        try {
            startActivity(mapa);
        } catch (ActivityNotFoundException e) {
            // Se não houver app de mapas, abre no navegador
            Intent web = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=" + enderecoCodificado));
            try {
                startActivity(web);
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(this, "Nenhum aplicativo de mapas encontrado",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
