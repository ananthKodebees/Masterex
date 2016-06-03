package com.course.masterex.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.course.masterex.model.ContentSaved;
import com.course.masterex.R;

import java.util.ArrayList;


public class SaveAdapter extends ArrayAdapter<ContentSaved>{

    public SaveAdapter(Context context, ArrayList<ContentSaved> users) {
        super(context, 0, users);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public ContentSaved getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(ContentSaved item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ContentSaved user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_saved, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPart = (TextView) convertView.findViewById(R.id.tvPart);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvcourse = (TextView) convertView.findViewById(R.id.tvcourse);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.close_image);


        imageView.setClickable(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeImage();
            }
        });


        tvName.setText(user.name);

        tvcourse.setText(user.name);
        tvDate.setText(user.participants);


        return convertView;

    }

    private void closeImage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
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


}
