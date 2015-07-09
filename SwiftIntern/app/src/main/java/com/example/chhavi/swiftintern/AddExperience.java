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

/**
 * Created by chhavi on 8/7/15.
 */
public class AddExperience extends AppCompatActivity {
    private EditText experience;
    private String experienceString;
     private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_paper_layout);
        experience = (EditText)findViewById(R.id.experience_text);
        submit = (Button)findViewById(R.id.submit);
        final TextView text = (TextView)findViewById(R.id.org_name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(TextUtils.htmlEncode(experience.getText().toString()));
                experienceString = experience.getText().toString();
               // Log.e("response", experienceString);
                experienceString = TextUtils.htmlEncode(experienceString);
                experience.setText(experienceString);
                //Log.e("response", experienceString);
                //TODO a lot of work


            }
        });




     //   setContentView();
    }
}
