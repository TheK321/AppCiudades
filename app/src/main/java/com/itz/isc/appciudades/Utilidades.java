package com.itz.isc.appciudades;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilidades {

    public static String leerJSON(Context context, String fileName) throws IOException{
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(
                context.getAssets().open(fileName), "UTF-8"
        ));
        String contenido="";
        String line;
        while ((line=reader.readLine())!=null){
            contenido+=line;
        }
        return contenido;
    }
}
