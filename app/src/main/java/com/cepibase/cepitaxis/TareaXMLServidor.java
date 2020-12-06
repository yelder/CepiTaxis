package com.cepibase.cepitaxis;

import android.os.AsyncTask;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TareaXMLServidor extends AsyncTask {
    ArrayList<Taxi> taxisDisponibles;
    URL url;

    public TareaXMLServidor(String s){
        try {
            url=new URL(s);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        taxisDisponibles=new ArrayList<Taxi>();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        PrincipalActivity.taxisDisponibles=taxisDisponibles;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            InputSource entrada=new InputSource(url.openStream());
            AnalizadorXMLDom analizadorXMLDom=new AnalizadorXMLDom(entrada);
            taxisDisponibles=analizadorXMLDom.parse();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
