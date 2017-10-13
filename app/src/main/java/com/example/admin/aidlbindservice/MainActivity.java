package com.example.admin.aidlbindservice;

import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.example.client.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivityTag";
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            return aString;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
