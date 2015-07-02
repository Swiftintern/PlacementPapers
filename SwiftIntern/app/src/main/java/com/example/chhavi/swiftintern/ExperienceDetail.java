package com.example.chhavi.swiftintern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by chhavi on 1/7/15.
 */
public class ExperienceDetail extends ActionBarActivity {
    TextView experienceDetail;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
        setContentView(R.layout.paper_layout);
        experienceDetail = (TextView)findViewById(R.id.paper_text);
        String detail = Paper.experiences.get(position).getDetails();
        experienceDetail.setText(Html.fromHtml(detail));
        getSupportActionBar().setTitle(Paper.organization.getName());
     //   getSupportActionBar().setTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
