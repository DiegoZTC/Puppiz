package com.puppiz.g3.puppyz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.puppiz.g3.puppyz.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import library.configuracion;

public class reg_mascota extends Activity {
    String id;
    RadioButton rp,rg,ro;
    private Button btnregmascota;
    EditText nombre,tipo,descripcion,color;
    String cod_usuario;
    String tip;
    configuracion URL = new configuracion();
    String URL_API =  URL.getIp();
    String URL_data;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg_mascota);
        nombre = (EditText) findViewById(R.id.mascota);
        rp = (RadioButton) findViewById(R.id.rbbperro);
        rg = (RadioButton) findViewById(R.id.rbgato);
        ro = (RadioButton) findViewById(R.id.btttres);
        color = (EditText)findViewById(R.id.color);
        btnregmascota = (Button) findViewById(R.id.btnreg);


        //reuperar id usuario
        try {

            SharedPreferences prefs =
                    getSharedPreferences("Login",
                            Context.MODE_PRIVATE);
            id = prefs.getString("id","Ninguno");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //determinar  variables para confirmación de identifación
        cod_usuario = id;
        nombre.setText(id);
        btnregmascota.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                String nom=nombre.getText().toString();
                String col = color.getText().toString();

                if (rp.isChecked())
                    tip  = "1";
                if (rg.isChecked())
                    tip  = "2";
                if (ro.isChecked())
                    tip  = "3";
                String des= descripcion.getText().toString();


                if (nombre.equals("") && des.equals("") && tip.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Error:Ingrese la información necesaria",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                        new statusregis().execute(id,nom,tip,des,col);
                }
            }
        });
    }
                //clase asincrona para el login
                class statusregis extends AsyncTask<String,Integer,Boolean> {


                    protected Boolean doInBackground(String... params) {
                        boolean result = true;
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost post = new HttpPost(URL_API +"pet/");
                        post.setHeader("Content-Type","application/json");
                        try {
                            //Construimos el objeto cliente en formato JSON
                            JSONObject dato = new JSONObject();

                            dato.put("user_id", params[0].toString());
                            dato.put("name", params[1].toString());
                            dato.put("type", params[2].toString());
                            dato.put("color", params[3].toString());
                            dato.put("details", params[4].toString());

                            StringEntity entity = new StringEntity(dato.toString());
                            post.setEntity(entity);

                            HttpResponse resp = httpClient.execute(post);
                            String respStr = EntityUtils.toString(resp.getEntity());

                            if (!respStr.equals("true"))
                                result = false;
                        } catch (Exception ex) {
                            Log.e("Service", "Erro!", ex);
                        }
                        return result;
                    }

                    protected void onPostExecute(Boolean result) {
                        if (result) {
                            Log.e("Estado","Insertado OK");
                            startActivity(new Intent(reg_mascota.this, menu.class));
                            Toast.makeText(getApplicationContext(),"Registro correcto",
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                }

}