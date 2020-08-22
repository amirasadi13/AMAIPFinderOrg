package com.example.amaipfinder.home.page;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.amaipfinder.R;

public class ViewDialog {

    public void showDialog(Activity activity ,String msg){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.error_dialog);

        TextView textView = dialog.findViewById(R.id.tv_msg_error);
        textView.setText(msg);

        Button button = dialog.findViewById(R.id.btn_dismiss_dialog);
        button.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }
}
