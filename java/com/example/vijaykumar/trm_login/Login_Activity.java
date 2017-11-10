package com.example.vijaykumar.trm_login;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {
EditText txtusername,txtpwd;
    TextView signin;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);

        txtusername=(EditText)findViewById(R.id.login_usr);
        txtpwd=(EditText)findViewById(R.id.login_psw);
        signin=(TextView) findViewById(R.id.txtSignIn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogin();
            }
        });
    }
    public void getLogin() {
        GlobalVariables.showDialog(Login_Activity.this);

       String url = GlobalVariables.gURL+"LoginUserPost/";
       url = url.replaceAll(" ", "%20");

        Map<String, String> jsonParams = new LinkedHashMap<String, String>();
        jsonParams.put("strPwdMob",txtpwd.getText().toString().trim());
        jsonParams.put("strUsernameMOb", txtusername.getText().toString().trim());
       jsonParams.put("strDeviceIdMob", GlobalVariables.getDeviceID(Login_Activity.this));
       //jsonParams.put("strToken","A");
       //  String url = GlobalVariables.gURL+"LoginUser/"+txtusername.getText()+"/"+GlobalVariables.getDeviceID(getApplicationContext())+"/"+txtpwd.getText().toString().trim();
      //  url = url.replaceAll(" ", "%20");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GlobalVariables.progressDialog.cancel();
                try {

                    GlobalVariables.MESSAGE=response.getString("Message").trim();
                    GlobalVariables.STATUS=response.getString("Status");
                    Toast.makeText(Login_Activity.this, GlobalVariables.MESSAGE, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                GlobalVariables.progressDialog.cancel();
                Toast.makeText(Login_Activity.this, "time out", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(req);

    }
}
