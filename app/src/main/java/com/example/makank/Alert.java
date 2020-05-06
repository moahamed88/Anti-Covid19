package com.example.makank;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;

import com.example.makank.ui.activity.HomeActivity;
import com.example.makank.ui.activity.VolunteerActivity;
import com.example.makank.ui.contact.ContactFragment;
import com.example.makank.ui.news.NewsFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Mustafa on 4/26/2020.
 */

public class Alert {
    private Activity activity;
    private AlertDialog dialog;
    int flag;
    public Alert(Activity myaActivity) {
        activity = myaActivity;
    }

    public void showAlertSuccess(String y) {
        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("تم اتمام العملية بنجاح")
                .setContentText(y)
                .setConfirmText("موافق")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                    }
                })
                .show();
    }

    public void showAlertSuccess(String title, String message, String btnText) {
        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(btnText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                    }
                })
                .show();
    }

    public void showAlertNormal(String title, String message, String btnText) {

        new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(btnText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    //    Intent intent=new Intent(activity, HomeActivity.class);
                       // activity.startActivity(intent);
                    }
                })
                .show();

    }

    public void showAlertError(String x) {
        Typeface typeface;
        new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(x)
                //.showCancelButton(true)
                .setConfirmText("موافق")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();
    }

}