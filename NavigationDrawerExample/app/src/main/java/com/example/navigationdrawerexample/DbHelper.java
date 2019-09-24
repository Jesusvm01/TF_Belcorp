package com.example.navigationdrawerexample;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        // null porque se va a usar el SQLiteCursor
        super(context, "belcorpdb2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "" +
                "CREATE TABLE IF NOT EXISTS producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, marca TEXT NOT NULL, categoria TEXT NOT NULL, " +
                "unidad TEXT NOT NULL, precio DECIMAL NOT NULL, fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

        db.execSQL(sql);

        String sql2 = "" +
                "CREATE TABLE IF NOT EXISTS tienda (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, direccion TEXT NOT NULL, telefono TEXT NOT NULL," +
                "latitud TEXT NOT NULL, longitud TEXT NOT NULL);";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS genero");
        // onCreate(db);
    }
}
