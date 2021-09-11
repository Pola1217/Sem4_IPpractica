package com.example.sem4_ipping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pings extends AppCompatActivity {

    private TextView pings;
    private Button backBtn;
    private String ip;
    private String ping;
    private int maxIp;
    private Boolean conectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pings);

        pings = findViewById(R.id.pings);
        backBtn = findViewById(R.id.back);

        ping = "Buscando\n";

        //max para recibido o perdido
        maxIp = 0;

        //main IP
        ip = getIntent().getExtras().getString("ip");

       new Thread(
                () -> {
                    while (maxIp < 5) {
                        try {
                            //check connection
                            maxIp++;
                            InetAddress ipAddress = InetAddress.getByName(ip);
                            Log.d("conectado","conectado"+ipAddress);

                            conectado = ipAddress.isReachable(9000);

                            if (conectado == true) {
                                ping += "Recibido\n";
                            } else {
                                ping += "Perdido\n";
                            }

                            //cambio visual del TextView
                            runOnUiThread(
                                    ()->{
                                        pings.setText(ping);
                                    });

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
       ).start();


        backBtn.setOnClickListener(
                (view) -> {
                    Intent i = new Intent(this, MainActivity.class);
                   //stop thread
                    maxIp = 6;
                    startActivity(i);

                }
        );


    }



}