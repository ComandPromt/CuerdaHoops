package com.example.cuerdahoops;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cuerdahoops.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import static com.example.cuerdahoops.R.id;
import static com.example.cuerdahoops.R.layout;

public class MainActivity extends AppCompatActivity {
    private HashSet listaSinDobles = new HashSet();
    public static LinkedList<String> listaABorrar=new LinkedList<String>();
    public static ListView lista;
    private AppBarConfiguration mAppBarConfiguration;
    public static EditText diametro,separacion;
    public static EditText bola;
    public static TextView resultado;
    public static RadioGroup rb;
    public static RadioGroup enganche;
    public static int opcion=1;
    public static int opcionEnganche=1;
    public static View view;
    public static String selectedItem;
    public static LinkedList<String>seleccionABorrar=new LinkedList<String>();
    public String btoString( InputStream inputStream ) throws IOException
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len=0;
        while ( (len=inputStream.read(bytes))>0 )
        {
            b.write(bytes,0,len);
        }
        return new String( b.toByteArray(),"UTF8");
    }

    public LinkedList<String> leerDatoArhivo() {

        LinkedList<String>lista=new LinkedList<String>();

        String archivos [] = fileList();

        if(archivoExiste(archivos, "cuerdahoops.txt")){

            try {

                InputStreamReader archivo = new InputStreamReader(openFileInput("cuerdahoops.txt"));

                BufferedReader br = new BufferedReader(archivo);

                String linea = br.readLine();

                String bitacoraCompleta = "";


                while(linea != null){

                    bitacoraCompleta = linea + "\n";

                    linea = br.readLine();

                        lista.add(bitacoraCompleta);

                }

                br.close();

                archivo.close();

            }catch (IOException e){

            }

        }

        return lista;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                id.nav_home, id.nav_gallery)
                .setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, id.nav_host_fragment);
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
        NavController navController = Navigation.findNavController(this, id.nav_host_fragment);
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

    @RequiresApi(api = Build.VERSION_CODES.N)

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

        String medidas="";

        try {


            String datoDiametro = diametro.getText().toString();

            String medidaBola = bola.getText().toString();

            String datoEnganche=" || Con enganche";

            if (opcionEnganche == 2) {
                datoEnganche=" || Sin enganche";
            }

            String datoBola="";

            if(!medidaBola.isEmpty()){
                datoBola=" || bola --> "+medidaBola;
            }

            medidas="Diametro --> "+datoDiametro+datoBola+" || separacion --> "+medidaSeparacion+datoEnganche;

            if(!datoDiametro.isEmpty() && !medidaBola.isEmpty() && opcion == 3){

                    medidaDiametro = Double.parseDouble(datoDiametro);

                    medBola = Double.parseDouble(medidaBola);

                    int espacioEnganche = 2;

                    if (opcionEnganche == 2) {
                        espacioEnganche = 0;
                    }

                    if (medBola > 0.0) {

                        int calculo = (int) Math.round((((medidaDiametro * 3.14) - espacioEnganche) + medidaSeparacion) / (medBola + medidaSeparacion));

                        if (calculo > 0) {

                            result = "Necesitas ";

                            if (calculo == 1) {
                                result += "1 bola";
                            }

                            else {
                                result += calculo + " bolas";
                            }

                        }

                    }

                }

                else {

                    if(opcion<3 && !datoDiametro.isEmpty()) {

                        String metodo = "";

                        String resultado = "";

                        double calculo = 0;

                        medidaDiametro = Double.parseDouble(datoDiametro);

                        if (opcion == 1) {

                            metodo = "Simple: ";

                            calculo = (medidaDiametro * 10.7) / 4;

                            if (opcionEnganche == 2) {
                                calculo += 2;
                            }


                        }

                        if (opcion == 2) {

                            metodo = "Doble: ";

                            calculo = (medidaDiametro * 142.5) / 8.7;

                            if (opcionEnganche == 2) {
                                calculo -= 17;
                            }

                            else {
                                calculo -= 19;
                            }

                        }

                        resultado = calcularMedida(truncar(calculo));

                        result = metodo + " cadena --> " + resultado;

                }

            }

            result += ".";

            if (!result.equals(".")) {

                resultado.setText(result);

                guardarArchivo(medidas+"\n"+result);

            }

        }

        catch(Exception e){
          //
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private void guardarArchivo(String dato) {

        try {

            LinkedList<String>lectura=leerDatoArhivo();

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("cuerdahoops.txt", MODE_PRIVATE));

           for(int i=0;i<lectura.size();i++){

              archivo.write(lectura.get(i));

            }

            if(!lectura.contains(dato)){
                archivo.write(dato);
               archivo.write("\n\n");
            }

            archivo.flush();

            archivo.close();

        }

        catch (IOException e){

        }

    }

    public static boolean archivoExiste(String archivos[], String NombreArchivo){
        for(int i = 0; i < archivos.length; i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    public void borrar(MenuItem item) {

        if(selectedItem.length()>1){

            int numItemsSelected = seleccionABorrar.size();

            String textoConfirmacion = "";

            if (numItemsSelected == 1) {
                textoConfirmacion = "¿Desea borrar a " + selectedItem + "?";
            }

            if (numItemsSelected > 1) {
                textoConfirmacion = "¿Desea borrar " + numItemsSelected + " elementos?";
            }

            if (!textoConfirmacion.isEmpty()) {

                final AlertDialog show = new AlertDialog.Builder(view.getContext())
                    .setTitle("Confirmacion")
                    .setMessage(textoConfirmacion)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            for (int i = 0; i < seleccionABorrar.size(); i++) {

                                listaABorrar.remove(listaABorrar.indexOf(seleccionABorrar.get(i)));

                            }

                            try {

                                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("cuerdahoops.txt", MODE_PRIVATE));

                                for (int i = 0; i < listaABorrar.size(); i++) {

                                    archivo.write(listaABorrar.get(i));

                                }

                                archivo.flush();

                                archivo.close();

                                seleccionABorrar.clear();

                            } catch (Exception e) {

                            }

                        }

                    })

                    .setNegativeButton(android.R.string.no, null).show();
            }

        }

    }

    public void about(MenuItem item) {
        Toast.makeText(getApplicationContext(),"smr2gocar@gmail.com",Toast.LENGTH_SHORT).show();
    }

    public void limpiar(MenuItem item) {
        final AlertDialog show = new AlertDialog.Builder(view.getContext())
                .setTitle("Confirmacion")
                .setMessage("¿Desea limpiar la lista?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                            listaABorrar.clear();
                            seleccionABorrar.clear();
                            GalleryFragment.listaBd.clear();

                        try {

                            OutputStreamWriter archivo = null;

                            archivo = new OutputStreamWriter(openFileOutput("cuerdahoops.txt", MODE_PRIVATE));

                            archivo.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                })

                .setNegativeButton(android.R.string.no, null).show();
    }
}