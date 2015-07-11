package com.example.chhavi.swiftintern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chhavi.swiftintern.Utility.Constants;

import models.SavedExperience;

/**
 * Created by chhavi on 1/7/15.
 */
public class ExperienceDetail extends ActionBarActivity {
    TextView experienceDetail;
    int position;
    private String paperId;
    private String paperTitle;
    private String detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
        setContentView(R.layout.paper_layout);
        experienceDetail = (TextView)findViewById(R.id.paper_text);
        if(position!=-1) {
            detail = Paper.experiences.get(position).getDetails();
            paperId = Paper.experiences.get(position).getId();
            paperTitle = Paper.experiences.get(position).getTitle();

        }else{
            detail = extras.getString("details");
            paperTitle = extras.getString("title");
        }

        experienceDetail.setText(Html.fromHtml(detail));
        getSupportActionBar().setTitle(paperTitle);
     //   getSupportActionBar().setTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // return super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                break;
            case R.id.save_paper:
               // SavedExperience savedExperience = new SavedExperience("Title here", "2nd edition");
                SavedExperience savedExperience = new SavedExperience(detail,paperTitle,paperId);
                savedExperience.save();
                Toast.makeText(ExperienceDetail.this, Constants.SAVE_PAPER,Toast.LENGTH_SHORT).show();
               // Log.e("sometjiing","title");
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(position!=-1) {
            inflater.inflate(R.menu.experience_detail, menu);
        }
        return true;
    }

}
