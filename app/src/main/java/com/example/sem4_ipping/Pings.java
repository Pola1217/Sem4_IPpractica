package com.example.sem4_ipping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pings extends AppCompatActivity {

    private TextView pings;
    private Button backBtn;
    private String Ips;
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

        //max for recibido o perdido
        maxIp = 0;

        //main IP
        Ips = getIntent().getExtras().getString("Ips");

       new Thread(
                () -> {
                    while (maxIp < 5) {
                        try {
                            //check connection
                            maxIp++;
                            InetAddress ipAddress = InetAddress.getByName(Ips);

                            conectado = ipAddress.isReachable(500);



                            //visual changes on textview
                            runOnUiThread(
                                    ()-> {

                                        if (conectado) {
                                            ping += "Recibido\n";
                                        } else {
                                            ping += "Perdido\n";
                                        }
                                    /*()->{
                                        pings.setText(ping);
                                    }*/
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