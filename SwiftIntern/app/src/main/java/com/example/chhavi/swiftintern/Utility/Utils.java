package com.example.chhavi.swiftintern.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by chhavi on 1/7/15.
 */
public class Utils {


    public static void noNetworkMessage(final Context context, final GsonRequest<?> req) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(Constants.NO_NETWORK_FOUND).setCancelable(false)
                .setPositiveButton(" Try again ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,
                                        @SuppressWarnings("unused") final int id) {
                        AppController.getInstance().addToRequestQueue(req);

                    }
                });
        final AlertDialog alert = builder.create();
       // if (SplashScreen.isError == false) {
            alert.show();
        }
    }

