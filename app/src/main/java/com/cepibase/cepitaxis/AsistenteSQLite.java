package com.cepibase.cepitaxis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AsistenteSQLite extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public AsistenteSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = context.openOrCreateDatabase(name, context.MODE_PRIVATE, null);
        String crearTabla = "CREATE TABLE historial (fecha datetime," + " matricula char(7)," + " nombre char(120), " + " apellidos char(120))";
        try{
            db.execSQL(crearTabla);
        }catch (Exception e){

        }
    }

    public void nuevoRegistro(String matricula, String nombre, String apellidos){
        String sql = "INSERT INTO historial (fecha, matricula, nombre, apellidos)" + "values(CURRENT_TIMESTAMP, '" + matricula + "', '" +
                nombre + "', '" + apellidos + "')";
        db.execSQL(sql);
    }

    public ArrayList<String[]> obtenerHistorial(){
        String sql = "SELECT * FROM historial";
        Cursor cursorHistorial = db.rawQuery("SELECT * FROM historial order by fecha desc", null);
        ArrayList<String[]> arrayListListado = new ArrayList<String[]>();
        cursorHistorial.moveToFirst();
        while (!cursorHistorial.isAfterLast()){
            arrayListListado.add(new String[]{
                    cursorHistorial.getString(0), cursorHistorial.getString(1),cursorHistorial.getString(2),cursorHistorial.getString(3)
            });
            cursorHistorial.moveToNext();
        }
        return arrayListListado;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
