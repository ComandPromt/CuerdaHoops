package com.example.cuerdahoops;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceType")

public class MainActivity extends AppCompatActivity {

    private static final int VISIBLE = 0,INVISIBLE = 0;
    RadioButton rb;
    EditText diametro,bola;
    TextView anchoBola,resultado;
    RadioButton simple,doble,nbolaas;
    RadioGroup radioGroup;

    public Double truncar(Double numero){
        return (double)((int)(numero*100)/100.0);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.group);
        simple = (RadioButton)findViewById(R.id.simple);
        doble = (RadioButton)findViewById(R.id.doble);
        nbolaas = (RadioButton)findViewById(R.id.nbolas);

        diametro = (EditText)findViewById(R.id.diametro);
        bola = (EditText)findViewById(R.id.medidaBola);

        anchoBola= (TextView)findViewById(R.id.anchoBola);
        resultado=(TextView)findViewById(R.id.resultado);
        radioGroup.clearCheck();

        simple.setSelected(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                rb = (RadioButton) group.findViewById(checkedId);

                if (null != rb && checkedId > -1) {

                    if(rb.getText().equals( getString(R.string.n_bolas))){
                        anchoBola.setVisibility(View.VISIBLE);
                        bola.setVisibility(View.VISIBLE);
                    }

                   else{
                        anchoBola.setVisibility(View.INVISIBLE);
                        bola.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

    }

    public void calculo(View view) {

        try{

            String result="";

            String diametre=diametro.getText().toString();

            String medidaBola=bola.getText().toString();

            double medidaDiametro=0.0;

            if(!diametre.isEmpty()){
                medidaDiametro=Double.parseDouble(diametre);
            }

            if(rb.getText().equals( getString(R.string.n_bolas)) && !diametre.isEmpty() && !medidaBola.isEmpty()) {

                double medBola = Double.parseDouble(medidaBola);

                if(medBola>0.0){

                    int calculo = (int) (((medidaDiametro * 3.14) /medBola) - 6);

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

            else{

                if(!diametre.isEmpty() && !rb.getText().equals(getString(R.string.n_bolas))){

                    String metodo="";

                    String resultado="";

                    double calculo=0;

                    if(rb.getText().equals( getString(R.string.simple))){
                        metodo="Simple: ";
                        calculo=(medidaDiametro*10.7)/4;

                        resultado=calcularMedida(truncar(calculo));

                    }

                    if(rb.getText().equals( getString(R.string.doble))){

                        metodo="Doble: ";

                        calculo=(medidaDiametro*142.5)/8.7;

                        calculo-=19;

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

}
