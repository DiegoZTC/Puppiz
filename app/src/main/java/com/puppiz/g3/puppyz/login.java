package com.puppiz.g3.puppyz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.puppiz.g3.puppyz.R;

public class login extends Activity {

    Button btnregistro;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        btnregistro  = (Button) findViewById(R.id.btnregistro);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnregistro.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(login.this, registro.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(new Intent(login.this, menu.class));
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
