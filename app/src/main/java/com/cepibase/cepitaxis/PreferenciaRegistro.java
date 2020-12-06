package com.cepibase.cepitaxis;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class PreferenciaRegistro extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,new misPreferenciasFragment())
                .commit();
    }
   public static class misPreferenciasFragment extends PreferenceFragment {
       @Override
       public void onCreate(@Nullable Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           addPreferencesFromResource(R.xml.info_usuario);
       }
   }
}
