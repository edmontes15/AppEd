package com.example.apped.Modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class dUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla="create table if not exists usuario(id integer primary key autoincrement, nombre text, apellidoP text, apellidoM text, email text, pass text )";

    public dUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd, Context.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if(buscar(u.getEmail())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("apellidoP",u.getApellidoP());
            cv.put("apellidoM",u.getApellidoM());
            cv.put("email",u.getEmail());
            cv.put("pass",u.getPassword());
            return (sql.insert("usuario", null,cv)>0);
        }else {
            return false;
        }
    }

    public int buscar(String u){
        int x=0;
        lista = selectUsuario();
        for (Usuario us:lista) {
            if(us.getEmail().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr!=null&&cr.moveToFirst()){
            do{
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellidoP(cr.getString(2));
                u.setApellidoM(cr.getString(3));
                u.setEmail(cr.getString(4));
                u.setPassword(cr.getString(5));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int a=0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr!=null&&cr.moveToFirst()){
            do{
                if (cr.getString(4).equals(u)&&cr.getString(5).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }

    public Usuario getEmail(String u, String p){
        lista=selectUsuario();
        for (Usuario us:lista) {
            if (us.getEmail().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getEmailById(int id){
        lista=selectUsuario();
        for (Usuario us:lista) {
            if (us.getId()==id){
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("nombre",u.getNombre());
        cv.put("apellidoP",u.getApellidoP());
        cv.put("apellidoM",u.getApellidoM());
        cv.put("email",u.getEmail());
        cv.put("pass",u.getPassword());
        return (sql.update("usuario",cv,"id="+u.getId(),null)>0);
    }

    public boolean deleteUsuario(int id){
        return (sql.delete("usuario","id="+id,null)>0);
    }

}
