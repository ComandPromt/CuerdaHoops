package com.example.cuerdahoops.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cuerdahoops.MainActivity;
import com.example.cuerdahoops.R;

public class HomeFragment extends Fragment {
    private static final int VISIBLE = 0,INVISIBLE = 0;

    EditText diametro,bola,separacion;
    TextView anchoBola,resultado;
    RadioButton simple,doble,nbolas;
    RadioGroup radioGroup;
    RadioGroup enganche;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;

    }

    @SuppressLint("WrongViewCast")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        resultado= (TextView) view.findViewById(R.id.resultado);

        MainActivity.resultado=this.resultado;

        diametro = (EditText) view.findViewById(R.id.diametro);

        MainActivity.diametro=this.diametro;

        separacion = (EditText) view.findViewById(R.id.separacion);

        MainActivity.separacion=this.separacion;

        bola = (EditText) view.findViewById(R.id.medidaBola);

        MainActivity.bola=this.bola;

        radioGroup = (RadioGroup) view.findViewById(R.id.group);

        enganche= (RadioGroup) view.findViewById(R.id.enganche);

        MainActivity.rb=this.radioGroup;

        MainActivity.enganche=this.enganche;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {

                    case R.id.simple:
                      MainActivity.opcion=1;
                        break;

                    case R.id.doble:
                        MainActivity.opcion=2;
                        break;

                    case R.id.nbolas:
                        MainActivity.opcion=3;
                        break;

                }
            }
        });

        enganche.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {

                    case R.id.con_enganche:
                        MainActivity.opcionEnganche=1;
                        break;

                    case R.id.sin_enganche:
                        MainActivity.opcionEnganche=2;
                        break;

                }

            }
        });

    }

}