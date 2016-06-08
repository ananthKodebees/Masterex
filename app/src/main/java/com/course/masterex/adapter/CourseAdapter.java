package com.course.masterex.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.course.masterex.model.ContentDiscover;
import com.course.masterex.R;

import java.util.ArrayList;


public class CourseAdapter extends BaseAdapter{

    private ArrayList<ContentDiscover> list;
    private final Activity context;


   public CourseAdapter(ArrayList<ContentDiscover> list , Activity context) {
       this.list = list;
       this.context = context;
   }


    public ArrayList getlist(){

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

        ContentDiscover user = list.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.content_discover, null, true);


        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvPart = (TextView) rowView.findViewById(R.id.tvPart);
        TextView tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        ImageView image = (ImageView) rowView.findViewById(R.id.discover_first_image);

        tvName.setText(user.getType().toString());
        tvPart.setText(user.getName().toString());
        tvDate.setText(user.getName().toString());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });

        return rowView;

    }

    private void saveImage() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getcontext());
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


    public Activity getcontext() {
        return context;
    }
}

