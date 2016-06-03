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

import com.course.masterex.model.ContentDiscover;
import com.course.masterex.R;

import java.util.ArrayList;


public class CourseAdapter extends ArrayAdapter<ContentDiscover>{

    public CourseAdapter(Context context, ArrayList<ContentDiscover> users) {
        super(context, 0, users);
    }



    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public ContentDiscover getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(ContentDiscover item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ContentDiscover user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_discover, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPart = (TextView) convertView.findViewById(R.id.tvPart);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        ImageView image = (ImageView) convertView.findViewById(R.id.discover_first_image);

        tvName.setText(user.name);
        tvPart.setText(user.description);
        tvDate.setText(user.participants);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });

        return convertView;

    }

    private void saveImage() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(R.string.save_alert);
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
