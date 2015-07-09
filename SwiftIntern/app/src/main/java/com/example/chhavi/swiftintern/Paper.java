package com.example.chhavi.swiftintern;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.GsonRequest;
import com.example.chhavi.swiftintern.Utility.Utils;
import com.google.android.gms.analytics.HitBuilders;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import models.Experience;
import models.Organization;
import models.PapersResponse;

/**
 * Created by chhavi on 24/6/15.
 */
public class Paper extends ActionBarActivity {
    String companyId;
    GsonRequest<PapersResponse> myReq;
    TextView paperText;
    TextView orgName;
    ImageView orgImage;
    TextView webName;
    ListView experienceList;
    LinearLayout mainLayout;
    GoogleProgressBar progressBar;
   public static Organization organization;
    public static List<Experience> experiences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.experience_list_layout);
        paperText = (TextView)findViewById(R.id.paper_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
         companyId = extras.getString("company_id");
        String url = "http://swiftintern.com/organization/detail/"+companyId+".json";

        orgName = (TextView)findViewById(R.id.company_name);
        orgImage = (ImageView)findViewById(R.id.company_image);
        webName = (TextView)findViewById(R.id.website_name);
        experienceList = (ListView)findViewById(R.id.experience_list);
        mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        progressBar = (GoogleProgressBar)findViewById(R.id.google_progress);
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
                    if(volleyError instanceof NoConnectionError || volleyError instanceof TimeoutError)
                        Utils.noNetworkMessage(Paper.this, myReq);


                }
            };

    }

    private Response.Listener<PapersResponse> createMyReqSuccessListener() {
        return new Response.Listener<PapersResponse>() {
            @Override
            public void onResponse(PapersResponse papersResponse) {

                makeLayout(papersResponse);
                progressBar.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);


            }
        };

    }

    public void makeLayout(PapersResponse papersResponse ){
         organization = papersResponse.getOrganization();
        experiences = papersResponse.getExperiences();
        orgName.setText(organization.getName());
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
                .resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.final_image)
                .showImageOnFail(R.drawable.final_image).showImageOnLoading(R.drawable.final_image).build();

        imageLoader.displayImage(organization.getImage(), orgImage, options);
        if(organization.getWebsite()!=null){
            webName.setText(organization.getWebsite());
        }
        ArrayList<String> titles = new ArrayList<String>();
        for(Experience experience:experiences){
            String title = experience.getTitle();
            title = title.replace("&amp;", "&");
            titles.add(title);

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, titles);

        experienceList.setAdapter(adapter);
        experienceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Paper.this, ExperienceDetail.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });

    //    Experience experience = experiences.get(0);
     //   paperText.setText(Html.fromHtml(experience.getDetails()));



     //   int pos = (Integer)v.getTag();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.paper_list_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // return super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                AppController.tracker().send(new HitBuilders.EventBuilder("ui", "open")
                        .setLabel("settings")
                        .build());
                break;
            case R.id.add_paper:
                Intent i = new Intent(Paper.this,AddExperience.class);
                startActivity(i);
                break;


        }
        return true;
    }




}
