package com.puppiz.g3.puppyz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.*;
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
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import library.Httppostaux;
import library.configuracion;
import android.content.SharedPreferences;

public class login extends Activity {

    Button btnregistro;
    Button btnlogin;
    EditText user;
    EditText pass;
    private ProgressDialog pDialog;
    configuracion URL = new configuracion();
    String URL_API =  URL.getIp();
    String URL_data;
    // codigo para almacenar en memoria interna
    //private EditText textBox;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //Guardamos las preferencias
        SharedPreferences prefs =
                getSharedPreferences("Login", Context.MODE_PRIVATE);
         editor = prefs.edit();

        //determinar  variables para confirmación de identifación
        user= (EditText) findViewById(R.id.edituser);
        pass= (EditText) findViewById(R.id.editpassword);
        //referenciando botones
        btnregistro  = (Button) findViewById(R.id.btnregistro);
        btnlogin = (Button) findViewById(R.id.btnlogin);


        btnregistro.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                startActivity(new Intent(login.this, registro.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                String usuario=user.getText().toString();
                String passw=pass.getText().toString();
                if (checklogindata(usuario,passw)== true)
                {new statuslogin().execute(usuario,passw);}
                else
                {Log.e("entro en vacio","vacio");
                    err_login();}
              //  startActivity(new Intent(login.this, menu.class));
            }
        });
    }

    //clase asincrona para el login


    class statuslogin extends  AsyncTask<String,Void,JSONArray>{
        String usuariologeado;
        InputStream is;
        String resultado ="fallo";
        BufferedReader brf;
        JSONArray lista;
        @Override
        protected void onPreExecute(){
            pDialog = new ProgressDialog(login.this);
            pDialog.setMessage("Autenticando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected  JSONArray doInBackground(String... params) {
            String user = params[0];
            String passw = params[1];
            JSONArray arrayJson = null;

            //crear la conexion http
            HttpClient cliente = new DefaultHttpClient();
            URL_data = URL_API+"user/"+user+"/"+passw;
           // /api/user/Beta
            Log.e("url...",URL_data.toString());
            HttpGet peticionGet = new HttpGet(URL_data);

            try {
                HttpResponse response = cliente.execute(peticionGet);
                brf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sbt = new StringBuffer();
                String linea = "";
                String NL = "";
                while ((linea = brf.readLine()) != null) {
                    sbt.append(linea + NL);
                }
                brf.close();
                resultado = sbt.toString();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                lista = new JSONArray(resultado);
            } catch (JSONException e) {
                e.printStackTrace();

            }
            return lista;
        }

        protected void onPostExecute(JSONArray resultado){

            pDialog.dismiss();//ocultamos progess dialog.
            if (resultado.length() >0) {
                try {
                    JSONObject objetojson = resultado.getJSONObject(0);
                    String id = null;
                    id = (objetojson.getString("_id"));
                    if (id != null) {
                        usuariologeado = (objetojson.getString("name"));
                        Log.e("logeado", usuariologeado.toString());


                        //guardar  usuario iniiado.

                        try{

                            editor.putString("id",objetojson.getString("_id"));
                            editor.putString("nombre",objetojson.getString("name"));
                            editor.putString("nickname",objetojson.getString("username"));

                            editor.commit();
                            startActivity(new Intent(login.this, menu.class));

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    } else
                        Log.e("error login", "error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectas",
                        Toast.LENGTH_LONG).show();
            }

        }

    }


    //vibra y muestra un Toast
    public void err_login(){
        Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Campos vacíos", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public boolean checklogindata(String username ,String password ){

        if 	(username.equals("") || password.equals("")){
            Log.e("Login ui", "checklogindata user or pass error");
            return false;
        }else{
            return true;
        }

    }
}
