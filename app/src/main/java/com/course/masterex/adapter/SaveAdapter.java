package com.course.masterex.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.course.masterex.common.Constants;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.model.ContentSaved;
import com.course.masterex.R;

import java.util.ArrayList;


public class SaveAdapter extends BaseAdapter {

    private ArrayList<ContentSaved> list;
    private final Activity context;

    public SaveAdapter(ArrayList<ContentSaved> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    public ArrayList getlist() {

        return list;
    }

    public void setId(ArrayList list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        final ContentSaved save = list.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.content_saved, null, true);

        TextView tvName = (TextView) rowView.findViewById(R.id.tvname);
        TextView tvtype = (TextView) rowView.findViewById(R.id.tvdate);
        ImageView image = (ImageView) rowView.findViewById(R.id.discover_first_image);
        ImageView delete = (ImageView) rowView.findViewById(R.id.close_image);
delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        closeImage();
    }
});
        tvName.setText(save.getName().toString());
        tvtype.setText(save.getType().toString());



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rowView;

    }

    private void closeImage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getcontext());
        alertDialogBuilder.setMessage(R.string.close_alert);
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();


    }

    private Context getcontext() {
        return context;
    }

}
