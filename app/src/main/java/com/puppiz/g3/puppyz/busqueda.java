package com.puppiz.g3.puppyz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.puppiz.g3.puppyz.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class busqueda extends Activity {

    String Id_usuario, Id_foto, estado,descripcion;
    Button btnbusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        //descripcion = (EditText) findViewById(R.id.)

    }
}