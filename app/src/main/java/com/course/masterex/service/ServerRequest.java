package com.course.masterex.service;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.course.masterex.fragment.DiscoverFragment;

public class ServerRequest extends AsyncTask<String, String, String> {


    public Activity activity;

    private ServerResponse serverResponse;


    private static String url ;

    private ProgressDialog pDialog;


    public ServerRequest(Activity activity,String url,ServerResponse serverResponse){

        this.url = url;
        this.activity = activity;
        this.serverResponse = serverResponse;

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

        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);




        return jsonStr ;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pDialog.dismiss();
        if(result != null ) {
            serverResponse.onResponse(result);
            Log.e("Response: ", "> " + result);
        }

    }

}