package com.stankoech.gadsaadleaderboard.submission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stankoech.gadsaadleaderboard.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProjectSubmission extends AppCompatActivity {

    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;

    EditText fn,ln,em,link;
    String FirstName;
    String LastName ;
    String Email;
    String projectLink;
    String URL_SUBMIT_POST= "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        Button btn_submit = findViewById(R.id.btn_submit);
        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
      fn = findViewById(R.id.editTextTextPersonName);
        ln = findViewById(R.id.editTextTextPersonName5);
        em = findViewById(R.id.editTextTextPersonName3);
        link = findViewById(R.id.editTextTextPersonName4);


        FirstName = fn.getText().toString();

        LastName = ln.getText().toString();
        Email = em.getText().toString();
        projectLink = link.getText().toString();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SutmitFolder();
                }

        });

    }

    private void SutmitFolder() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProjectSubmission.this);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        alertDialog.getWindow().setLayout(700, 700);
        LayoutInflater inflater = ProjectSubmission.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_name, null);
        alertDialog.getWindow().setContentView(dialogView);
        Button btnSubmit = dialogView.findViewById(R.id.btn_sure);
        ImageView tv = dialogView.findViewById(R.id.close);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                jsonrequest();
            }
        });



    }






    private void jsonrequest() {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Submitting.....");

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        //add to cart favorite
        String URL_FAVORITE_POST= "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FAVORITE_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                        progressDialog.dismiss();
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.my_toast,
                                (ViewGroup) findViewById(R.id.mylayout));

                        Toast custToast = new Toast(ProjectSubmission.this);
                        custToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        custToast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
                        custToast.setDuration(Toast.LENGTH_LONG);
                        custToast.setView(view);
                        custToast.show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Toast.makeText(ProjectSubmission.this,"Adding item to favorite failed"+error.toString(),Toast.LENGTH_LONG).show();
//                        progressDialog.dismiss();
//                        //Log.e("VOLLEY", error.toString());




                        progressDialog.dismiss();
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.my_toast_fail,
                                (ViewGroup) findViewById(R.id.mylayout_fail));

                        Toast custToast = new Toast(ProjectSubmission.this);
                        custToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        custToast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
                        custToast.setDuration(Toast.LENGTH_LONG);
                        custToast.setView(view);
                        custToast.show();





                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("entry.1877115667",FirstName);
                params.put("entry.2006916086",LastName);
                params.put("entry.1824927963",Email);
                params.put("entry.284483984",projectLink);
                // params.put("quantity", String.valueOf(number[0]));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}