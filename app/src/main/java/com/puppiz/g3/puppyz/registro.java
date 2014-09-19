package com.puppiz.g3.puppyz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.puppiz.g3.puppyz.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import library.configuracion;

public class registro extends Activity {
    Button btnregis;
    EditText password,password2,email,name,username;
    configuracion URL = new configuracion();
    String URL_API =  URL.getIp();
    String URL_data;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registro);
        //determinar  variables para confirmación de identifación
        username = (EditText) findViewById(R.id.nickname);
        name= (EditText) findViewById(R.id.nombre);
        email= (EditText) findViewById(R.id.mail);
        password= (EditText) findViewById(R.id.pass1);
        password2 = (EditText) findViewById(R.id.pass2);
        //referenciando botones
        btnregis  = (Button) findViewById(R.id.btnreg);

        btnregis.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                String nickname=username.getText().toString();
                String nombre=name.getText().toString();
                String correo=email.getText().toString();
                String contrase=password.getText().toString();
                String contrase2= password2.getText().toString();

                if (nickname.equals("") && correo.equals("") && contrase.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Error:Ingrese la información necesaria",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (contrase.equals(contrase2))
                    {
                        new statusregis().execute(nickname,nombre,correo,contrase);
                        Toast.makeText(getApplicationContext(),"Cuenta creada..",
                                Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Contraseñas no coiciden",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    //clase asincrona para el login
    class statusregis extends AsyncTask<String,Integer,Boolean> {


        protected Boolean doInBackground(String... params) {
            boolean result = true;
            try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(URL_API +"user/");
            post.setHeader("Content-Type","application/json");

                //Construimos el objeto cliente en formato JSON
                JSONObject dato = new JSONObject();

                dato.put("username", params[0].toString());
                dato.put("name", params[1].toString());
                dato.put("email", params[2].toString());
                dato.put("password", params[3].toString());

                StringEntity entity = new StringEntity(dato.toString());
                post.setEntity(entity);

                HttpResponse resp = httpClient.execute(post);
                String respStr = EntityUtils.toString(resp.getEntity());

                if (!respStr.equals("true"))
                    result = false;
            } catch (Exception ex) {
                Log.e("Service", "Error!", ex);
            }
            return result;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                Log.e("Estado","Insertado OK");
                startActivity(new Intent(registro.this,login.class));
            }
        }
    }

}
