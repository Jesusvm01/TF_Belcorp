package com.example.navigationdrawerexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MedicionDAO {

    private DbHelper _dbHelper;

    public MedicionDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }



    public void insertar(String nombre, String marca, String categoria, String unidad, double precio) throws DAOException {
        Log.i("MedicionDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre+"", marca+"", categoria+"",unidad+"",  String.valueOf(precio)};
            db.execSQL("INSERT INTO producto(nombre, marca, categoria,unidad,precio ) VALUES(?,?,?,?,?)", args);
            Log.i("MedicionDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void actualizar(int idHorno, double temperatura, int ocupabilidad, String descripcion) throws DAOException {
        Log.i("MedicionDAO", "actualizar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            //String[] args = new String[]{temperatura+"", ocupabilidad+"",descripcion+"",idHorno+""};

            ContentValues args = new ContentValues();
            args.put("idHorno", idHorno);
            args.put("temperatura", temperatura);
            args.put("ocupabilidad", ocupabilidad);
            args.put("descripcion", descripcion);

            db.update("producto",args,"idHorno = " + String.valueOf(idHorno),null);

            Log.i("MedicionDAO", "Se actualizó");
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al actualizar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Medicion> listar() throws DAOException {
        Log.i("MedicionDAO", "listar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Medicion> lista = new ArrayList<Medicion>();
        try {
            Cursor c = db.rawQuery("select id, nombre, marca, categoria, unidad, precio from producto", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    String id = c.getString(c.getColumnIndex("id"));
                    String nombre = c.getString(c.getColumnIndex("nombre"));
                    String marca = c.getString(c.getColumnIndex("marca"));
                    String categoria = c.getString(c.getColumnIndex("categoria"));
                    String unidad = c.getString(c.getColumnIndex("unidad"));
                    String precio = c.getString(c.getColumnIndex("precio"));


                    Medicion modelo = new Medicion();
                    modelo.setId(Integer.parseInt(id));
                    modelo.setNombre(nombre);
                    modelo.setMarca(marca);
                    modelo.setCategoria(categoria);
                    modelo.setUnidad(unidad);
                    modelo.setPrecio(Double.parseDouble(precio));


                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }


    public ArrayList<Medicion> buscar(String criterio1, String criterio2, String operador) throws DAOException {
        Log.i("MedicionDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Medicion> lista = new ArrayList<Medicion>();
        try {
            Cursor c = db.rawQuery("select id, nombre, marca from producto where nombre like '%"+criterio1+"%' " +operador+ " marca like '%"+criterio2+"%'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String nombre = String.valueOf(c.getString(c.getColumnIndex("nombre")));
                    String marca = String.valueOf(c.getString(c.getColumnIndex("marca")));

                    Medicion modelo = new Medicion();
                    modelo.setId(id);
                    modelo.setNombre(nombre);
                    modelo.setMarca(marca);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }



    public void eliminar(int id) throws DAOException {
        Log.i("MedicionDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(id)};
            db.execSQL("DELETE FROM medicion WHERE idHorno=?", args);
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void eliminarTodos() throws DAOException {
        Log.i("MedicionDAO", "eliminarTodos()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM medicion");
        } catch (Exception e) {
            throw new DAOException("MedicionDAO: Error al eliminar todos: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

}
