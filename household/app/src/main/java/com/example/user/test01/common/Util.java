package com.example.user.test01.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by USER on 2016-08-02.
 */
public class Util {

    public Util() {

    }// end of Util constructor

    public static ProgressDialog pDialogShow(Context dialogContext, String dialogStr, boolean isCancelable) {
        ProgressDialog pDialog = new ProgressDialog(dialogContext);
        pDialog.setMessage(dialogStr);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(isCancelable);
        pDialog.setCanceledOnTouchOutside(!isCancelable);

        pDialog.show();

        return pDialog;
    }

    public static void showAlert(Context dialogContext, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
        builder.setMessage(message).setTitle(title)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 아무 것도 안 함
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}// end of Utill
