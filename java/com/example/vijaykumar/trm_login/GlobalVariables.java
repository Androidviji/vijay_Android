package com.example.vijaykumar.trm_login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vijay.kumar on 11/9/2017.
 */


    public class GlobalVariables {



        public static String gURL = "http://192.168.4.85:88/SvcDTLMS.svc/";
    public  static  ProgressDialog progressDialog;


    public static void showDialog(Activity activity){
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
    }




        public static String MESSAGE = "";
        public static String STATUS = "";






        public static String  getDeviceID(Context ctxt) {
            try {
                String deviceid = "";
                TelephonyManager telephonyManager = (TelephonyManager) ctxt.getSystemService(ctxt.TELEPHONY_SERVICE);
                deviceid = telephonyManager.getDeviceId();
                return deviceid;
            } catch (Exception e) {
                String strEventName = "getDeviceID";
                String strClassName = "GlobalVariables";
                String strLog = "Exception raised is null";

                if (e.getMessage() != null) {
                    strLog = e.getMessage();

                }

                return "null";
            }

        }

        }



