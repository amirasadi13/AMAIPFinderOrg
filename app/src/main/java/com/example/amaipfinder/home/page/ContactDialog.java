package com.example.amaipfinder.home.page;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amaipfinder.R;

public class ContactDialog {

    public void showDialogContact(Activity activity){

        final Dialog dialogContent = new Dialog(activity);
        dialogContent.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogContent.setCancelable(false);
        dialogContent.setContentView(R.layout.contact_dialog);


        ImageView button = dialogContent.findViewById(R.id.btn_dismiss_contact_dialog);
        button.setOnClickListener(v -> dialogContent.dismiss());

        dialogContent.show();

    }

}
