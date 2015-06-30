package com.example.chhavi.swiftintern;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.GsonRequest;

import java.util.List;

import models.Experience;
import models.PapersResponse;

/**
 * Created by chhavi on 24/6/15.
 */
public class Paper extends ActionBarActivity {
    String companyId;
    GsonRequest<PapersResponse> myReq;
    TextView paperText;
    List<Experience> experiences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.paper_layout);
        paperText = (TextView)findViewById(R.id.paper_text);
        Bundle extras = getIntent().getExtras();
         companyId = extras.getString("company_id");
        String url = "http://swiftintern.com/organization/detail/"+companyId+".json";
        loadContent(url);

    }

    public void loadContent(String url){
        myReq = new GsonRequest<PapersResponse>(Request.Method.GET, url,
                PapersResponse.class, createMyReqSuccessListener(), createMyReqErrorListener());
        AppController.getInstance().addToRequestQueue(myReq);




    }

    private Response.ErrorListener createMyReqErrorListener() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            };

    }

    private Response.Listener<PapersResponse> createMyReqSuccessListener() {
        return new Response.Listener<PapersResponse>() {
            @Override
            public void onResponse(PapersResponse papersResponse) {
                experiences = papersResponse.getExperiences();

                Experience experience = experiences.get(0);
                paperText.setText(Html.fromHtml(experience.getDetails()));

            }
        };

    }
}
