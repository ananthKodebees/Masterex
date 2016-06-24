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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.course.masterex.common.Constants;
import com.course.masterex.common.Utils;
import com.course.masterex.model.ContentDiscover;
import com.course.masterex.R;
import com.course.masterex.preference.AppPreference;
import com.course.masterex.service.RequestId;
import com.course.masterex.service.ServerRequest;
import com.course.masterex.service.ServerResponse;
import com.course.masterex.service.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CourseAdapter extends BaseAdapter implements ServerResponse {

    private ArrayList<ContentDiscover> list;
    private final Activity context;

String url = Constants.SavedUrL;



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

        final ContentDiscover user = list.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.content_discover, null, true);

        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvPart = (TextView) rowView.findViewById(R.id.tvPart);
        TextView tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        ImageView image = (ImageView) rowView.findViewById(R.id.discover_first_image);

        tvName.setText(user.getType().toString());
        tvPart.setText(user.getName().toString());
        tvDate.setText(user.getName().toString());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                final String id = sharedPreferences.getString("Id", "");
                Log.e("ID", id);
                String courseId = user.getId();
                saveCourse(courseId,id);
            }
        });

        return rowView;

    }

    private void saveCourse(final String courseId,final String id) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getcontext());
       alertDialogBuilder.setMessage(R.string.save_alert);

       alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               List<NameValuePair> values = new ArrayList<NameValuePair>();
               values.add(new BasicNameValuePair("userid", id));
               Log.e("Id", id);
               Log.e("courseId", courseId);
               values.add(new BasicNameValuePair("courseid", courseId));

               ServerRequest serverRequest = new ServerRequest(context,url,ServiceHandler.POST, CourseAdapter.this, RequestId.SAVE_REQUEST,values);
               serverRequest.execute("");

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






    @Override
    public void onResponse(RequestId requestId, String response) {
        try {
            JSONObject obj  = new JSONObject(response);

            boolean status = obj.getBoolean("status");
            if(status){
                String message = obj.getString("message");
                Utils.Toast(getcontext(),message);
                Log.e("message", message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

