package com.example.vijaykumar.myapplication3;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {
    Database_Helper dh;
    EditText id, named, marks;
    Button ins, fetch, del, updt;
    String test="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new Database_Helper(MainActivity.this);
        id = (EditText) findViewById(R.id.editText_1);
        named = (EditText) findViewById(R.id.editText_2);
        marks = (EditText) findViewById(R.id.editText_3);
        updt = (Button) findViewById(R.id.button_upd);
        del = (Button) findViewById(R.id.button_del);
        fetch = (Button) findViewById(R.id.button_get);
        ins = (Button) findViewById(R.id.button_ins);
        datastore();
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                if (test.trim().equals("")) {
                    //nothing
                } else {*/

                    if (test.contains(named.getText().toString())) {
                        Toast.makeText(MainActivity.this, "name already exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
               /* }*/

                boolean isInsert = dh.insertData(named.getText().toString(), marks.getText().toString());
                if (isInsert == true) {


                    Toast.makeText(MainActivity.this, "data inserted succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, " not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dh.getData();
                if (res.getCount() == 0) {
                    showMessage("error", "nothing to display");
                    return;
                } else {
                    StringBuffer buf = new StringBuffer();
                    while (res.moveToNext()) {
                        buf.append("id:" + res.getString(0) + "\n");
                        buf.append("name:" + res.getString(1) + "\n");
                        buf.append("marks:" + res.getString(2) + "\n");
                    }

                    test = buf.toString();
                    showMessage("data", buf.toString());
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isDeleted = dh.deletData(id.getText().toString());
                if (isDeleted > 0) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = dh.updateData(id.getText().toString(), named.getText().toString(), marks.getText().toString());
                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Updated succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void showMessage(String title, String message) {

        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(message);
        build.show();
    }


    public void datastore() {
        Cursor res = dh.getData();
        if (res.getCount() == 0) {
            showMessage("error", "nothing to display");
            return;
        } else {
            StringBuffer buf = new StringBuffer();
            while (res.moveToNext()) {
                buf.append("id:" + res.getString(0) + "\n");
                buf.append("name:" + res.getString(1) + "\n");
                buf.append("marks:" + res.getString(2) + "\n");
            }

            test = buf.toString();
        }
    }

}
