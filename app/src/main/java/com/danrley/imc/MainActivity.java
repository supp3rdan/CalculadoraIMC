package com.danrley.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvResultado, tvClassificacao;
    EditText etPeso, etAltura;
    InputMethodManager imn;
    LinearLayout layResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultadoImc);
        tvClassificacao = findViewById(R.id.tvResultadoClassificacao);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        layResultado = findViewById(R.id.layResultado);

        layResultado.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view){
       if(dadosPreenchidos()){
           imn.hideSoftInputFromWindow(etAltura.getWindowToken(), 0);
           float peso = Float.parseFloat(etPeso.getText().toString());
           float altura = Float.parseFloat(etAltura.getText().toString());
           if(altura == 0){
               etAltura.setError("Valor inválido");
               return;
           }
           layResultado.setVisibility(View.VISIBLE);
           float imc = peso / (altura * altura);
           String resultado = classificacao(imc);
           tvResultado.setText(String.format("%.2f", imc));
           tvClassificacao.setText(resultado);

       }
    }

    public boolean dadosPreenchidos(){
        boolean ok = true;
        if(etPeso.getText().toString().trim().isEmpty()){
            ok = false;
            etPeso.setError("Informe a nota");
            layResultado.setVisibility(View.INVISIBLE);
        }
        if(etAltura.getText().toString().trim().isEmpty()){
            ok = false;
            etAltura.setError("Informe a nota");
            layResultado.setVisibility(View.INVISIBLE);
        }
        return ok;
    }

    public String classificacao(float imc){
        String result;
        if(imc < 18.5){
            result = "Abaixo do peso normal";
        } else if(imc < 24.9){
            result = "Peso normal";
        } else if(imc < 29.9){
            result = "Excesso de peso";
        } else if(imc < 34.9){
            result = "Obesidade classe I";
        }else if(imc < 39.9){
            result = "Obesidade classe II";
        } else {
            result = "Obesidade classe III";
        }
        return result;
    }

    public void limpar(View view){
        etPeso.setText("");
        etAltura.setText("");
        layResultado.setVisibility(View.INVISIBLE);
    }
}