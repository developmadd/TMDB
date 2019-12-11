package com.madd.madd.tmdb.Utilities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.madd.madd.tmdb.R;


@SuppressLint("ValidFragment")
public class InformativeDialog extends Fragment {

    private AlertDialog alert;

    private Button buttonAccept;
    private TextView textViewTittle;
    private TextView textViewMessage;


    public static InformativeDialog getInstance(Context context){
        return new InformativeDialog(context);
    }

    @SuppressLint("ValidFragment")
    private InformativeDialog(Context context) {
        bindUI(context);
    }


    public void setData(String message, String title) {

        textViewTittle.setText(title);
        textViewMessage.setText(message);

        buttonAccept.setOnClickListener(v -> {
            alert.cancel();
        });
    }

    public void setData(String message, String title,
                        OnAcceptClickListener onAcceptClickListener) {

        textViewTittle.setText(title);
        textViewMessage.setText(message);

        buttonAccept.setOnClickListener(v -> {
            alert.cancel();
            onAcceptClickListener.onClick();
        });
    }

    private void bindUI(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = View.inflate(context, R.layout.dialog_informative, null);
        builder.setView(v);

        buttonAccept =  v.findViewById(R.id.BTN_Dialog_Informative_Accept);
        textViewTittle =  v.findViewById(R.id.TV_Dialog_Informative_Title);
        textViewMessage =  v.findViewById(R.id.TV_Dialog_Informative_Text);


        alert = builder.create();
        alert.show();
    }





    public interface OnAcceptClickListener {
        void onClick();
    }
}

