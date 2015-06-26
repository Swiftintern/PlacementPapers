package com.example.chhavi.swiftintern;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

/**
 * Created by chhavi on 31/5/15.
 */
public class NavigationDrawer extends MaterialNavigationDrawer {
    SharedPreferences sp;
    @Override
    public void init(Bundle bundle) {
        getSupportActionBar().hide();
   //     this.addSection(newSection("HOME", new CompaniesList()));
  //      this.addSection(newSection("PROFILE", new ProfileFragment()));
    //    this.addSection(newSection("REWARDS", new Rewards()));
     //   this.addSection(newSection("INVITE FRIENDS", new InviteFriends()));
       // this.addSection(newSection("ABOUT US", new FragmentOne()));
       this.addAccount(new MaterialAccount(this.getResources(), "Chhavi Gupta", "coolaqua12@gmail.com",null,android.R.drawable.ic_menu_always_landscape_portrait));

    }
}
