package examen.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class InterfazReloj extends Activity {

    public static TextView reloj;
    public static Button iniciar;
    public static Button detener;
    public static Button reiniciar;
    private Handler progressBarHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reloj);
        reloj = (TextView)findViewById(R.id.reloj);
        iniciar = (Button)findViewById(R.id.botonStart);
        detener = (Button)findViewById(R.id.botonDetener);
        reiniciar = (Button)findViewById(R.id.botonReiniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clock reloj = new Clock();
                Thread t1 = new Thread(reloj);
                t1.start();
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Clock.condicion = false;
                BaseExamen bd = new BaseExamen(InterfazReloj.this);
                bd.insertarClock(Clock.getHora(),Clock.getMinuto(),Clock.getSegundo());
            }
        });
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Clock.reiniciar();
            }
        });


    }


}
