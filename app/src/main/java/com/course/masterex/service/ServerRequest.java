package com.course.masterex.service;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.course.masterex.adapter.CourseAdapter;
import com.course.masterex.common.Utils;
import com.course.masterex.fragment.DiscoverFragment;

import org.apache.http.NameValuePair;

import java.util.List;

public class ServerRequest extends AsyncTask<String, String, String> {


    public Activity activity;

    private ServerResponse serverResponse;

   List<NameValuePair> values;

    private static String url ;
    int method;
    private ProgressDialog pDialog;

RequestId requestId;

    public ServerRequest(Activity activity,String url,int method,ServerResponse serverResponse, RequestId requestId, List<NameValuePair>values){

        this.url = url;
        this.activity = activity;
        this.serverResponse = serverResponse;
        this.requestId = requestId;
        this.method = method;
        this.values = values;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("please wait..");
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        ServiceHandler sh = new ServiceHandler();

        String jsonStr = sh.makeServiceCall(url, method,values);


        return jsonStr ;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pDialog.dismiss();
        if(result != null ) {
            serverResponse.onResponse(requestId,result);
            Log.e("Response: ", "> " + result);
        }

    }
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;

    }

}