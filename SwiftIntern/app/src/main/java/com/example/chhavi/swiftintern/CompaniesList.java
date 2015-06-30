package com.example.chhavi.swiftintern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import com.example.chhavi.swiftintern.Utility.AppController;
import com.example.chhavi.swiftintern.Utility.GsonRequest;
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
    GsonRequest<CompaniesResponse> myReq;
    List<Organization> organisations;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companies_list);
       // CompaniesList.imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseCont‌​ext()));
        //x`getActionBar().show();
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

    public class customDrawerAdapter extends ArrayAdapter<Organization> {

        Context context;
        List<Organization> OrganisationList;
        int layoutResID;

        public customDrawerAdapter(Context context, int layoutResourceID, List<Organization> listItems) {
            super(context, layoutResourceID, listItems);
            this.context = context;
            this.OrganisationList = listItems;
            this.layoutResID = layoutResourceID;

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
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
                    .resetViewBeforeLoading(true).showImageForEmptyUri(R.drawable.ic_play_light)
                    .showImageOnFail(R.drawable.ic_play_light).showImageOnLoading(R.drawable.ic_play_light).build();

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
