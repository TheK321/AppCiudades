package com.itz.isc.appciudades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MunicipiosActivity extends AppCompatActivity implements View.OnClickListener {

    Bundle bundle = null;
    String estado = null;
    ArrayList<String> municipios = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipios);
        bundle = getIntent().getExtras();
        setTitle("Municipios");
        LinearLayout botonera = findViewById(R.id.layoutMunicipios);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //Crear botones
        String jsonFileContent = null;
        String jsonFileContentMunicipios = null;
        int idEstado = bundle.getInt("estado_id");
        System.out.println(idEstado);

        //int idInt = Integer.parseInt(idEstado);
        try {
            jsonFileContent = Utilidades.leerJSON(getApplicationContext(),"estados.json");
            JSONArray jsonArray = new JSONArray(jsonFileContent);
            jsonFileContentMunicipios = Utilidades.leerJSON(getApplicationContext(),"municipios.json");
            JSONArray jsonArrayMunicipios = new JSONArray(jsonFileContentMunicipios);
            JSONObject jsonObject = jsonArrayMunicipios.getJSONObject(idEstado);
            System.out.println(jsonObject.toString(2));
            Iterator<String> keys = jsonObject.keys();
            estado = keys.next();

            JSONArray municipiosDeEstado = jsonObject.getJSONArray(estado);
            for (int i=0; i<municipiosDeEstado.length();i++){
                String nombremunicipio = municipiosDeEstado.getString(i);
                municipios.add(nombremunicipio);
                Log.d("JSON","Estado: "+estado+"\tMunicipio: "+nombremunicipio);
                Button button = new Button(this);
                button.setLayoutParams(lp);
                button.setText(nombremunicipio);
                button.setId(i);
                button.setOnClickListener(this);
                botonera.addView(button);
            }
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        Toast.makeText(this, municipios.get(view.getId()) +", "+estado,Toast.LENGTH_LONG).show();
    }
}