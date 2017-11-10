package com.example.vijaykumar.volley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

boolean result;
    TextView results;

   /* String JsonURL = "http://192.168.4.172:88/SvcDTLMS.svc/LoadAllDTCFailure/1222";*/
      String JsonURL="http://192.168.4.134:91/svcmms.svc/WorkOrderDetails/11/1131/WorkOrder";

    RequestQueue requestQueue;
  private   RecyclerView recyclervw;
    private sampleAdapter mAdapter;
    private ArrayList<sampleinfo> sample_List = new ArrayList<>();
   Button bt;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
       bt = (Button) findViewById(R.id.button_1);

       // results = (TextView) findViewById(R.id.jsonData);
       recyclervw = (RecyclerView) findViewById(R.id.recyclervw);

        sample_List.clear();
        mAdapter = new sampleAdapter(sample_List);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclervw.setLayoutManager(mLayoutManager);
        recyclervw.setItemAnimator(new DefaultItemAnimator());
        recyclervw.setAdapter(mAdapter);

     onClick();

    }

    public void onClick() {
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                bt.setVisibility(View.INVISIBLE);
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.show(); // Display Progress Dialog

                JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.cancel();

                        try
                        {


                                for (int i = 0; i < response.length(); i++)
                                {
                                    sampleinfo sam=new sampleinfo();
                                        JSONObject jsonObject = response.getJSONObject(i);
                                    sam.mId = jsonObject.getString( "WOId");
                                    sam.mName = jsonObject.getString("WONumber");
                                    sam.mNumber= jsonObject.getString("WOType");

                                    sample_List.add(sam);


                                    // data += "Color Number " + sdtid + "nColor Name: " + sdtname + "nHex Value : " + capacity + "nnn";
                                }

                           // Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                           // results.setText(data);
                            mAdapter.notifyDataSetChanged();

                        }

                        catch (JSONException e)
                        {

                            e.printStackTrace();
                        }
                    }
                },

                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                progressDialog.cancel();
                                //Log.e("Volley", "Error");
                                Toast.makeText(MainActivity.this, "time out", Toast.LENGTH_SHORT).show();
                            }
                        }
                );


                requestQueue.add(arrayreq);

            }

        });
    }}