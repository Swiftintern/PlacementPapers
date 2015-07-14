package com.example.chhavi.swiftintern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chhavi.swiftintern.R;

import java.util.ArrayList;
import java.util.List;

import models.SavedExperience;

/**
 * Created by chhavi on 9/7/15.
 */
public class ViewSavedPapers extends AppCompatActivity {
    private ListView savedPaperList;
    private TextView noPaper;
    private  ArrayAdapter<String> adapter;
    private List<SavedExperience> savedExperienceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_saved_papers);
        getSupportActionBar().setTitle("Saved Experiences");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        savedPaperList = (ListView) findViewById(R.id.saved_papers_list);
        noPaper = (TextView)findViewById(R.id.no_save_message);
        populteList();


        savedPaperList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ViewSavedPapers.this, ExperienceDetail.class);
                i.putExtra("position", -1);
                i.putExtra("details", savedExperienceList.get(position).getText());
                i.putExtra("title", savedExperienceList.get(position).getTitle());
                startActivity(i);
            }
        });
       // Log.e("saved",savedExperienceList.get(0).getText());

    }
   void populteList() {

       savedExperienceList = SavedExperience.listAll(SavedExperience.class);
       if (savedExperienceList != null) {
           noPaper.setVisibility(View.GONE);
           ArrayList<String> titles = new ArrayList<String>();
           for (SavedExperience experience : savedExperienceList) {
               titles.add(experience.getTitle());
           }
           adapter = new ArrayAdapter<String>(this,
                   android.R.layout.simple_list_item_1, android.R.id.text1, titles);

           savedPaperList.setAdapter(adapter);
           registerForContextMenu(savedPaperList);
       }else {
           noPaper.setVisibility(View.VISIBLE);
           savedPaperList.setVisibility(View.GONE);


       }
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // return super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();

                break;



        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.saved_papers_list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
           menu.setHeaderTitle("Options");
            String[] menuItems = {"Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        switch (menuItemIndex){
            case 0:
                int position = info.position;
                SavedExperience deleteExperience = SavedExperience.findById(SavedExperience.class,savedExperienceList.get(position).getId());
                deleteExperience.delete();
                //adapter.notifyDataSetChanged();
                populteList();
                break;



        }

        return true;
    }
}
