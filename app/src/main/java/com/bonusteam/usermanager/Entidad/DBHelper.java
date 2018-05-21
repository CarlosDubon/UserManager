package com.bonusteam.usermanager.Entidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="db_usuarios";
    public static final String TABLA_USUARIO="db_usuarios";
    public static final String CAMPO_CARNET="carnet";
    public static final String CAMPO_MATERIA="materia";
    public static final String CAMPO_NOTA="nota";
    public static final String CAMPO_CATEDRATICO="catedratico";

    public static final String CREATION="CREATE TABLE "+ TABLA_USUARIO +"( "+CAMPO_CARNET+" TEXT, "+CAMPO_CATEDRATICO+" TEXT, "
                                        +CAMPO_MATERIA+" TEXT, "+CAMPO_NOTA+" TEXT)";


    public static DBHelper dbHelper = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper = new DBHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    public DBHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context= context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOTA);
        onCreate(db);
    }

    public boolean add(Usuario usuario){
        boolean flag;
        ContentValues values = new ContentValues();
        try {
            values.put(CAMPO_CARNET, usuario.getCarnet());
            values.put(CAMPO_CATEDRATICO, usuario.getCatedratico());
            values.put(CAMPO_MATERIA, usuario.getMateria());
            values.put(CAMPO_NOTA, usuario.getNota());
            flag=true;
        }catch (SQLException e){
            flag=false;
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return flag;
    }

    public Usuario findUser(String carnet){
        Usuario usuario;
        String[] params={carnet};
        String[] fields={CAMPO_NOTA,CAMPO_MATERIA,CAMPO_CATEDRATICO};
        try{
            Cursor cursor = db.query(TABLA_USUARIO,fields,CAMPO_CARNET+"=?",params,null,null,null);
            cursor.moveToFirst();
            usuario = new Usuario(carnet,cursor.getString(1),cursor.getString(0),cursor.getString(2));
        }catch (Exception e){
            usuario=null;
        }
        return usuario;
    }
    public ArrayList<Usuario> getAllUser(){
        Usuario usuario;
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        String[] fields={CAMPO_CATEDRATICO,CAMPO_MATERIA,CAMPO_NOTA,CAMPO_CARNET};
        try{
            Cursor cursor = db.query(TABLA_USUARIO,fields,null,null,null,null,null);
            while (cursor.moveToNext()){
                usuario = new Usuario(cursor.getString(0),cursor.getString(2),cursor.getString(1),cursor.getString(3));
                usuarioList.add(usuario);
            }
        }catch (Exception e){
            usuario =null;
        }
        return  usuarioList;
    }

    public  boolean modfifyUser(Usuario usuario){
        boolean flag;
        try {
            String[] params = {usuario.getCarnet()};
            String[] fields = {CAMPO_NOTA};
            ContentValues contentValues = new ContentValues();
            contentValues.put(CAMPO_NOTA, usuario.getNota());
            db.update(TABLA_USUARIO, contentValues, CAMPO_CARNET + "=?", params);
            flag = true;
        }catch (SQLException e){
            flag = false;
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return  flag;
    }
}
