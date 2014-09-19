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

public class menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Button btnreg;
        setContentView(R.layout.activity_menu);
        btnreg = (Button)findViewById(R.id.btnregmascota);

        btnreg.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                startActivity(new Intent(menu.this,reg_mascota.class));
            }
        });
    }


}
