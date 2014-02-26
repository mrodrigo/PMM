package examen.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseExamen extends SQLiteOpenHelper {

    public static final String TABLA_EXAMEN = "CREATE TABLE horarios (" +
            " cod_horario  INT NOT NULL," +
            " horas  int(2)," +
            " minutos       int(2)," +
            " segundos   int(2)," +
            "  PRIMARY KEY (cod_horario)" +
            " )";

    public static final String NOMBRE_BASEDATOS = "Examen";
    public static final int VERSION_BASEDATOS = 1;
    public SQLiteDatabase miTabla;
    public BaseExamen(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_EXAMEN);//CREAR BASE DE DATOS
        //miTabla = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_EXAMEN);
        onCreate(sqLiteDatabase);
    }



    public  void insertarClock(int horas, int minutos, int segundos){
        miTabla = getWritableDatabase();
        if (miTabla != null){
            ContentValues valores = new ContentValues();
            valores.put("horas",horas);
            valores.put("minutos", minutos);
            valores.put("segundos",segundos);

            miTabla.insert("horarios",null,valores);
            miTabla.close();
        }
    }

    public ArrayList<Clock> verHorario(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteDatabase sq = getWritableDatabase();
        ArrayList<Clock> lista_clock = new ArrayList<Clock>();
        String[] valores_recuperar = {"horas","minutos","segundos"};
        Cursor cursor = sqLiteDatabase.query("horarios",valores_recuperar,null,null,null,null,null,null);
        cursor.moveToFirst();
        do {
            Clock clock = new Clock(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
            lista_clock.add(clock);
        }while (cursor.moveToNext());
        sqLiteDatabase.close();
        cursor.close();
        return lista_clock;
    }


}
