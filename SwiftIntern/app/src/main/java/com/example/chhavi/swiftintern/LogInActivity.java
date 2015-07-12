package com.example.chhavi.swiftintern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.AppPreferences;
import com.example.chhavi.swiftintern.Utility.Constants;
import com.example.chhavi.swiftintern.Utility.GsonRequest;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.CompaniesResponse;
import models.LoginResponse;
import models.User;

/**
 * Created by chhavi on 10/7/15.
 */
public class LogInActivity extends Activity {
        private Spinner accountNames;
        private Button registerButton;
        private TextView nameText;
    GoogleProgressBar progressBar;

    private Handler mHandler;
    private GsonRequest myReq;
    private  String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        progressBar = (GoogleProgressBar)findViewById(R.id.google_progress);
        progressBar.setVisibility(View.GONE);
        mHandler = new Handler();
        registerButton = (Button)findViewById(R.id.register_button);
        nameText = (TextView)findViewById(R.id.name_text);
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);

        Account[] list = manager.getAccountsByType("com.google");
        ArrayList<String> acounts = new ArrayList<String>();
        for(Account account:list){
            acounts.add(account.name);
        }

        accountNames = (Spinner)findViewById(R.id.account_names_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, acounts);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountNames.setAdapter(dataAdapter);
        Log.e("account", acounts.get(0));

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String email = accountNames.getSelectedItem().toString();
                if(name!=null && !name.equals("") && email!=null && !email.equals("")){
                    Log.e("credentials",name+email);
                    url = "http://swiftintern.com/app/student.json";
                    loadContent(name,email);
                    registerButton.setClickable(false);
                    progressBar.setVisibility(View.VISIBLE);

                }else{
                    Toast.makeText(LogInActivity.this,"Please enter your credentials",Toast.LENGTH_LONG).show();


                }

            }
        });

    }

    void loadContent(String name,String email){
        Map<String,String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("email", email);

        myReq = new GsonRequest<LoginResponse>(Request.Method.POST, url,
                LoginResponse.class,params,createMyReqSuccessListener(), createMyReqErrorListener());
        AppController.getInstance().addToRequestQueue(myReq);
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error",volleyError.toString());
                Toast.makeText(LogInActivity.this, Constants.VOLLEY_ERROR,Toast.LENGTH_LONG).show();
                registerButton.setClickable(true);

            }
        };
    }

    private Response.Listener<LoginResponse> createMyReqSuccessListener() {
        return new Response.Listener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                final User user = loginResponse.getUser();
             String id =    user.getId();
                Log.e("id", id);
                AppPreferences.setBasicProfile(LogInActivity.this, user.getName(), user.getEmail(), user.getId());
                AppPreferences.setLoggedInAsTrue(LogInActivity.this);
                Toast.makeText(LogInActivity.this, "Welcome " + user.getName(), Toast.LENGTH_LONG).show();
             //   mHandler.postDelayed(mUpdateTimeTask,1000);
                Intent i = new Intent(LogInActivity.this,CompaniesList.class);
                startActivity(i);
                finish();


/*
                Thread loginThread = new Thread(){
                    @Override
                    public void run() {
                       // super.run();


                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {


                        }
                    }
                };

                loginThread.start();*/


            }
        };
    }
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            // do what you need to do here after the delay

        }
    };
}
