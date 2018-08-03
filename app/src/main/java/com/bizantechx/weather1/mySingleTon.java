package com.bizantechx.weather1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by welcum on 4/14/2018.
 */

public class mySingleTon {
    private static mySingleTon mInstance;
    private static RequestQueue requestQueue;
    private static Context mcontext ;

    private mySingleTon(Context context)
    {
        mcontext = context;
        requestQueue=getrequestqueue();
    }
    public RequestQueue getrequestqueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized mySingleTon getmInstance(Context context)
    {
        if(mInstance==null)
            mInstance=new mySingleTon(context);
        return mInstance;
    }
    public void addToRequestQueue(Request request )
    {
        requestQueue.add(request);
    }
}
