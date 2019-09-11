package com.bookapp.bookapp.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bookapp.bookapp.listeners.OnFetchedListener;

public class DataFetchedReciever extends BroadcastReceiver {

    private Context recieverContext;
    private OnFetchedListener listener;

    public DataFetchedReciever(Context context, OnFetchedListener onFetchedListener){
        this.recieverContext = context;
        this.listener = onFetchedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "DataFetched")
        listener.onAuthorsFetched();
    }
}
