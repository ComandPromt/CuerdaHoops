package com.example.cuerdahoops;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ListView lista;
    private AppBarConfiguration mAppBarConfiguration;
    public static EditText diametro,separacion;
    public static EditText bola;
    public static TextView resultado;
    public static RadioGroup rb;
    public static RadioGroup enganche;
    public static int opcion=1;
    public static int opcionEnganche=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private String calcularMedida(Double numero) {

        String resultado="";

        if(numero==100.0){
            resultado="1 metro";
        }

        else{

            if(numero%100==0){
                resultado=numero/100+" metros";
            }

        }

        if(numero>100.0 && numero<200.0){

            resultado="1 metro y "+truncar((numero-100))+ " cm";

        }

        if(numero>200.0){

            int unidad= (int) (numero/100);

            resultado=unidad+" metros y "+truncar((numero-(unidad*100)))+ " cm";

        }

        if(numero<100.0){
            resultado=numero+" cm";
        }

        resultado+=" aprox";

        return resultado;

    }

    public Double truncar(Double numero){
        return (double)((int)(numero*100)/100.0);
    }

    public void calculo(View view) {

        String datoSeparacion=separacion.getText().toString();

        if(datoSeparacion.isEmpty() || datoSeparacion.equals("0.0")){
            separacion.setText(""+0.1);
            datoSeparacion="0.1";
        }

        double medidaSeparacion=Double.parseDouble(datoSeparacion);

        double medidaDiametro=0.0;

        double medBola =0.0;

        String result="";

        try {

            String datoDiametro=diametro.getText().toString();

            String medidaBola = bola.getText().toString();

            if (opcion==3 && !datoDiametro.isEmpty() && !medidaBola.isEmpty()) {

                medidaDiametro = Double.parseDouble(datoDiametro);

                medBola = Double.parseDouble(medidaBola);

                int espacioEnganche=2;

                if(opcionEnganche==2){
                    espacioEnganche=0;
                }

                if (medBola > 0.0) {

                    int calculo = (int)Math.round ((((medidaDiametro * 3.14) - espacioEnganche) + medidaSeparacion) / (medBola + medidaSeparacion));

                    if (calculo > 0) {

                        result = "Necesitas ";

                        if (calculo == 1) {
                            result += "1 bola";
                        }

                        else {
                            result += calculo + " bolas";
                        }

                    }

                    resultado.setText(result);

                }

            }

            else {

                if(!datoDiametro.isEmpty() && opcion!=3){

                    String metodo="";

                    String resultado="";

                    double calculo=0;

                    medidaDiametro = Double.parseDouble(datoDiametro);

                    if(opcion==1){

                        metodo="Simple: ";

                        calculo=(medidaDiametro*10.7)/4;

                        if(opcionEnganche==2){
                            calculo+=2;
                        }

                        resultado=calcularMedida(truncar(calculo));

                    }

                    if(opcion==2){

                        metodo="Doble: ";

                        calculo=(medidaDiametro*142.5)/8.7;

                        if(opcionEnganche==2){
                            calculo-=17;
                        }

                        else{
                            calculo-=19;
                        }

                        resultado=calcularMedida(truncar(calculo));

                    }

                    result=metodo+"La cadena debe ser de "+resultado;

                }

            }

            resultado.setText(result);

        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

}