package com.example.chhavi.swiftintern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.AppPreferences;
import com.example.chhavi.swiftintern.Utility.Constants;
import com.example.chhavi.swiftintern.Utility.GsonRequest;

import java.util.HashMap;
import java.util.Map;

import models.AddPaperExperience;
import models.LoginResponse;

/**
 * Created by chhavi on 8/7/15.
 */
public class AddExperience extends AppCompatActivity {
    private EditText experience;
    private String experienceString;
     private Button submit;
    private String orgId;
    private EditText title;
    GsonRequest myReq;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_paper_layout);
        orgId = getIntent().getExtras().getString("organisation_id");
        userId = AppPreferences.getUserId(AddExperience.this);
        Log.e("details",orgId+" "+userId);
        experience = (EditText)findViewById(R.id.experience_text);
        title = (EditText)findViewById(R.id.title);
        submit = (Button)findViewById(R.id.submit);
        final TextView text = (TextView)findViewById(R.id.org_name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orgTitle = title.getText().toString();
                if(experience.getText().toString()!=null && !experience.getText().toString().equals("") && orgTitle!=null && !orgTitle.equals("")){
                  //  experienceString = experience.getText().toString().replaceAll("\\n", "<br>");
                    //
                    //experienceString = TextUtils.htmlEncode(experienceString);
                    //Log.e("response", experienceString);
                    experienceString = Html.toHtml(experience.getText());
                    //Log.e("response", experienceString);

                    /*text.setText(experienceString);
                    text.setText(Html.fromHtml(experienceString));*/
                    saveExperience(orgTitle,experienceString);
                }else{
                    Toast.makeText(AddExperience.this,"Please fill all details",Toast.LENGTH_LONG);
                }
            //

             //   experience.setText(experienceString);
                //Log.e("response", experienceString);
                //TODO a lot of work


            }
        });




     //   setContentView();
    }
    private void saveExperience(String title,String details){
        Map<String,String> params = new HashMap<String, String>();
        params.put("title", title);
        params.put("details", details);
        params.put("user_id", userId);
        String url = "http://swiftintern.com/organizations/saveExperience/"+orgId+".json";
        myReq = new GsonRequest<AddPaperExperience>(Request.Method.POST, url,
                AddPaperExperience.class,params,createMyReqSuccessListener(), createMyReqErrorListener());
        AppController.getInstance().addToRequestQueue(myReq);



    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(AddExperience.this, Constants.VOLLEY_ERROR, Toast.LENGTH_LONG).show();

            }
        };
    }

    private Response.Listener<AddPaperExperience> createMyReqSuccessListener() {
        return new Response.Listener<AddPaperExperience>() {
            @Override
            public void onResponse(AddPaperExperience addPaperExperience) {
                boolean success = addPaperExperience.isSuccess();
                if(success){
                    Toast.makeText(AddExperience.this, "Your Experience has been added successfully", Toast.LENGTH_LONG).show();
                    finish();

                }else{
                    Toast.makeText(AddExperience.this, "An error occured, please try again", Toast.LENGTH_LONG).show();


                }


            }
        };
    }
}
