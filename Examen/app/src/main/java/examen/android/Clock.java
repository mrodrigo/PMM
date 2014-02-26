package examen.android;


import android.os.Handler;

public class Clock  implements Runnable{

    private static int hora=0;
    private static int minuto=0;
    private static int segundo=0;
    private static boolean threadSuspended = false;
    private Handler Handler = new Handler();
    public String time;
    public static boolean condicion;
    public Clock() {
       setHours(getHora());
       setMinute(getMinuto());
       setSeconds(getSegundo());
   };

    public Clock(int horas, int minutos, int segundos) {
        setHours(horas);
        setMinute(minutos);
        setSeconds(segundos);
    };



    public static void reiniciar(){
        hora = 0;
        minuto = 0;
        segundo = 0;
    }

    public static void setHours(int hora1){
        hora = hora1;
    }

    public static void setMinute(int minuto1){
        minuto = minuto1;
    }

    public static void setSeconds(int segundo1){
        segundo = segundo1;
    }

    public static int getHora() {
        return hora;
    }

    public static int getMinuto() {
        return minuto;
    }

    public static int getSegundo() {
        return segundo;
    }

    @Override
    public void run() {//Hilo con la logica del reloj
        condicion = true;
        while(condicion){
            segundo = getSegundo() + 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }

            if(getHora() == 0 && getMinuto() == 0 && getSegundo() == 0){
                String time = "00:00:00";
                imprimirReloj(time);
                condicion = false;
                break;
            }

            if (getSegundo() == 59){
                segundo = 00;
                if(getMinuto() == 59){
                    minuto = 00;
                    hora = getHora() +1;
                }else{
                     minuto = getMinuto() + 1;
                }
            }
            setFormatTime();
        }
    }

    public void setFormatTime(){
        if (getHora() <=9 && getSegundo() <=9 && getMinuto() <=9){
            time = "0"+ getHora() +":0"+ getMinuto() +":0"+ getSegundo();
            imprimirReloj(time);
            return;
        }

        if (getHora() <=9 && getMinuto() <=9){
            time = "0"+ getHora() +":0"+ getMinuto() +":"+ getSegundo();
            imprimirReloj(time);
            return;
        }

        if(getHora() <=9 && getSegundo() <=9){
            time = "0"+ getHora() +":"+ getMinuto() +":0"+ getSegundo();
            imprimirReloj(time);
            return;
        }
        if (getHora() <=9){
            time = "0"+ getHora() +":"+ getMinuto() +":"+ getSegundo();
            imprimirReloj(time);
            return;
        }

    }

    public void imprimirReloj(String time){
        final String time2 = time;
        Handler.post(new Runnable() {
            public void run() {
                InterfazReloj.reloj.setText(time2);
            }
         });
    }
}
