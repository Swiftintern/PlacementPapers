package com.example.chhavi.swiftintern;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.swiftintern.placement.R;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.GsonRequest;
import com.example.chhavi.swiftintern.Utility.Utils;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import java.util.ArrayList;
import java.util.List;

import models.CompaniesResponse;
import models.Organization;

/**
 * Created by chhavi on 2/7/15.
 */
public class SearchResultsActivity extends ActionBarActivity {
    GsonRequest<CompaniesResponse> myReq;
    List<Organization> organizations;
    private TextView noOrg;
    GoogleProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_layout);
        getSupportActionBar().setTitle("Search Results");
        handleIntent(getIntent());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.e("query", query);
            String url = "http://swiftintern.com/organizations.json?limit=20&name=";
            url = url + query;
            progressBar = (GoogleProgressBar)findViewById(R.id.google_progress);
            loadContent(url);

           // setContentView(R.layout.companies_list_item);
            //use the query to search your data somehow
        }
    }

    public void loadContent(String url){
        myReq = new GsonRequest<CompaniesResponse>(Request.Method.GET, url,
                CompaniesResponse.class, createMyReqSuccessListener(), createMyReqErrorListener());
        AppController.getInstance().addToRequestQueue(myReq);


    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError instanceof NoConnectionError || volleyError instanceof TimeoutError)
                    Utils.noNetworkMessage(SearchResultsActivity.this, myReq);

            }
        };
    }

    private Response.Listener<CompaniesResponse> createMyReqSuccessListener() {
        return new Response.Listener<CompaniesResponse>() {
            @Override
            public void onResponse(CompaniesResponse companiesResponse) {
                Log.e("response", companiesResponse.toString());
                progressBar.setVisibility(View.GONE);


                noOrg = (TextView)findViewById(R.id.no_organisations_message);
                ListView searchResults = (ListView)findViewById(R.id.results_list);

                organizations = companiesResponse.getOrganizations();
                if(organizations!=null) {
                    ArrayList<String> names = new ArrayList<String>();
                    for (Organization organization : organizations) {
                        names.add(organization.getName());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchResultsActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);

                    searchResults.setAdapter(adapter);
                    searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String companyId = organizations.get(position).getId();
                            Intent i = new Intent(SearchResultsActivity.this, Paper.class);
                            i.putExtra("company_id", companyId);
                            startActivity(i);
                        }
                    });
                }
                else{
                    noOrg.setVisibility(View.VISIBLE);


                    //TODO add no organisations found mesage
                }


            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // return super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();

        }
        return true;
    }
}
