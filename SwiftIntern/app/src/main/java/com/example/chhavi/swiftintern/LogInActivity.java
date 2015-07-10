package com.example.chhavi.swiftintern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chhavi on 10/7/15.
 */
public class LogInActivity extends Activity {
        private Spinner accountNames;
        private Button registerButton;
        private TextView nameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        registerButton = (Button)findViewById(R.id.register_button);
        nameText = (TextView)findViewById(R.id.name_text);
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);

        Account[] list = manager.getAccountsByType("com.google");
        ArrayList<String> acounts = new ArrayList<String>();
        for(Account account:list){
            acounts.add(account.name);
        }

        accountNames = (Spinner)findViewById(R.id.account_names_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, acounts);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountNames.setAdapter(dataAdapter);
        Log.e("account", acounts.get(0));

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String email = accountNames.getSelectedItem().toString();
                if(name!=null && !name.equals("") && email!=null && !email.equals("")){
                    Log.e("credentials",name+email);

                }else{
                    Toast.makeText(LogInActivity.this,"Please enter your credentials",Toast.LENGTH_LONG).show();


                }

            }
        });

    }
}
