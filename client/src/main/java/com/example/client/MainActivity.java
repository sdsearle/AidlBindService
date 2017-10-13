package com.example.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvConnection;
    TextView tvString;
    TextView tvPid;

    IMyAidlInterface mIRemoteService;
    private String TAG = "MainActivityTag";
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = IMyAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: Connected");
            tvConnection.setText("Connected");

        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            mIRemoteService = null;
            tvConnection.setText("DisConnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bindService(new Intent(this, MyRemoteService.class), mConnection, Context.BIND_AUTO_CREATE);

        tvConnection = (TextView) findViewById(R.id.tvConnection);
        tvPid = (TextView) findViewById(R.id.tvPid);
        tvString = (TextView) findViewById(R.id.tvString);
    }

    public void connect(View view) {
        ComponentName aidlComponent = new ComponentName("com.example.admin.aidlbindservice",
                "com.example.admin.aidlbindservice.RemoteService");
        Intent remoteIntent = new Intent();
        remoteIntent.setComponent(aidlComponent);
        bindService(remoteIntent, mConnection, BIND_AUTO_CREATE);
    }

    public void getInfo(View view) throws RemoteException {
        tvPid.setText(mIRemoteService.getPid() + "");
        tvString.setText(mIRemoteService.basicTypes(0,0,true,0f,0d,"Returned String"));
    }
}
