package com.itz.isc.appciudades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EstadosActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados);

        setTitle("Estados");
        LinearLayout botonera = findViewById(R.id.layoutEstados);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //Crear botones
        String jsonFileContent = null;
        try {
            jsonFileContent = Utilidades.leerJSON(getApplicationContext(),"estados.json");
            JSONArray jsonArray = new JSONArray(jsonFileContent);
            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String clave = jsonObject.getString("clave");
                String nombre = jsonObject.getString("nombre");
                Log.d("JSON","Clave: "+clave+"\tNombre: "+nombre);
                Button button = new Button(this);
                button.setLayoutParams(lp);
                button.setText(nombre);
                button.setId(i);
                button.setOnClickListener(this);
                botonera.addView(button);
            }
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),MunicipiosActivity.class)
        .putExtra("estado_id",view.getId())
        );

    }
}