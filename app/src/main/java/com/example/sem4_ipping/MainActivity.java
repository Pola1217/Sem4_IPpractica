package com.example.sem4_ipping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText ping1;
    private EditText ping2;
    private EditText ping3;
    private EditText ping4;
    private TextView myIP;
    private String Sip1, Sip2, Sip3, Sip4;
    private String ip;
    private Button pingBtn;
    private Button hostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //IP pings
        ping1 = findViewById(R.id.ip1);
        ping2 = findViewById(R.id.ip2);
        ping3 = findViewById(R.id.ip3);
        ping4 = findViewById(R.id.ip4);

        //my IP
        //192.168.100.111
        myIP = findViewById(R.id.myIP);

        //buttons
        pingBtn = findViewById(R.id.ping);
        hostBtn = findViewById(R.id.host);

        //IP
        myIP();

        pingBtn.setOnClickListener(

                (view)->{

                    Sip1 = ping1.getText().toString();
                    Sip2 = ping2.getText().toString();
                    Sip3 = ping3.getText().toString();
                    Sip4 = ping4.getText().toString();

                    ip = Sip1+"."+Sip2+"."+Sip3+"."+Sip4;

                    //brings text from the IPs screen

                    Intent i = new Intent(this, Pings.class);

                    i.putExtra("ips", ip);

                    startActivity(i);



                }

        );

        hostBtn.setOnClickListener(

                (view)->{

                    Intent i = new Intent(this, Host.class);
                    startActivity(i);

                }

        );

    }

    public void myIP(){
        new Thread(
                ()->{
                    try {
                        //Get computer IP
                        InetAddress myIPs = InetAddress.getLocalHost();

                        runOnUiThread(
                                ()->{
                                    myIP.setText(myIPs.getHostAddress());
                                }
                        );
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

}