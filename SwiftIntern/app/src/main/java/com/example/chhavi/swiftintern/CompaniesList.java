package com.example.chhavi.swiftintern;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.Volley;
import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.GsonRequest;
import com.example.chhavi.swiftintern.Utility.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import models.CompaniesResponse;
import models.Organization;

/**
 * Created by chhavi on 18/6/15.
 */
public class CompaniesList extends ActionBarActivity implements AdapterView.OnItemClickListener {
GridView companiesList;
    SearchView searchView;
    GsonRequest<CompaniesResponse> myReq;
    List<Organization> organisations;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companies_list);
       // CompaniesList.imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseCont‌​ext()));
        //x`getActionBar().show();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            TextView text = (TextView)findViewById(R.id.textView);
            text.setText(extras.getString("experience"));

        }
        companiesList = (GridView)findViewById(R.id.companies_list);
        String url = "http://swiftintern.com/organizations/placementpapers.json";
        loadContent(url);

      /*  ArrayList<drawerItem> list = new ArrayList<drawerItem>();
        list.add(new drawerItem("Google",R.drawable.google));
        list.add(new drawerItem("Facebook",R.drawable.facebook));
        list.add(new drawerItem("Amazon",R.drawable.amazon));
        list.add(new drawerItem("Snapdeal",R.drawable.google));

        list.add(new drawerItem("Flipkart",R.drawable.amazon));
        list.add(new drawerItem("SanDisk", R.drawable.facebook));*/


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
                        Utils.noNetworkMessage(CompaniesList.this, myReq);

                }
            };

    }

    private Response.Listener<CompaniesResponse> createMyReqSuccessListener() {


        return new Response.Listener<CompaniesResponse>(){


            @Override
            public void onResponse(CompaniesResponse companiesResponse) {
            organisations  = companiesResponse.getOrganizations();

                customDrawerAdapter adapter = new customDrawerAdapter(CompaniesList.this,R.layout.companies_list_item,organisations);
                companiesList.setAdapter(adapter);
                companiesList.setOnItemClickListener(CompaniesList.this);

             //   Log.e("Name",organisations.get(0).getName());
                
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String companyId = organisations.get(position).getId();
        Intent i = new Intent(CompaniesList.this, Paper.class);
        i.putExtra("company_id",companyId);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.companies_list_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
         searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


    /*  @Nullable
    @Override
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.companies_list,null);
        companiesList = (ListView)v.findViewById(R.id.companies_list);
        ArrayList<drawerItem> list = new ArrayList<drawerItem>();
        list.add(new drawerItem("Google",R.drawable.google));
        list.add(new drawerItem("Facebook",R.drawable.facebook));
        list.add(new drawerItem("Amazon",R.drawable.amazon));
        list.add(new drawerItem("Snapdeal",R.drawable.google));

        list.add(new drawerItem("Flipkart",R.drawable.amazon));
        list.add(new drawerItem("SanDisk",R.drawable.facebook));

        customDrawerAdapter adapter = new customDrawerAdapter(getActivity(),R.layout.companies_list_item,list);
        companiesList.setAdapter(adapter);
        return v;
    }*/

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }

    public class customDrawerAdapter extends ArrayAdapter<Organization> {
        ImageLoader imageLoader;
        Context context;
        List<Organization> OrganisationList;
        int layoutResID;

        public customDrawerAdapter(Context context, int layoutResourceID, List<Organization> listItems) {
            super(context, layoutResourceID, listItems);
            this.context = context;
            this.OrganisationList = listItems;
            this.layoutResID = layoutResourceID;
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            DrawerItemHolder drawerHolder;
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                drawerHolder = new DrawerItemHolder();

                view = inflater.inflate(layoutResID, parent, false);
                drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
                drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

                view.setTag(drawerHolder);

            } else {
                drawerHolder = (DrawerItemHolder) view.getTag();

            }

            Organization organization = (Organization) this.OrganisationList.get(position);

         //   drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
                    .resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.final_image)
                    .showImageOnFail(R.drawable.final_image).showImageOnLoading(R.drawable.final_image).build();

            imageLoader.displayImage(organization.getImage(), drawerHolder.icon, options);
            drawerHolder.ItemName.setText(organization.getName());

            return view;
        }

        private  class DrawerItemHolder {
            TextView ItemName;
            ImageView icon;
        }
    }
}
