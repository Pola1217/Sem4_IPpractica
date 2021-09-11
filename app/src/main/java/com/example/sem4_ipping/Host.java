package com.example.sem4_ipping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host extends AppCompatActivity {

    private TextView hosts;
    private Button back2Btn;
    //ultimo digiito de la IP
    private int Spot = 1;
    private String localIp, myHost;
    private Boolean conectado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        hosts = findViewById(R.id.pings2);
        back2Btn = findViewById(R.id.back2);

        new Thread(
                () -> {
                    while(Spot < 255){

                        try {
                            //comprobacion y barrido de todas las ip cambiando el ultimo digito
                            localIp = "192.168.2."+Spot;
                            InetAddress inetAddress = InetAddress.getByName(localIp);

                            myHost = inetAddress.getHostAddress();

                            //check si conecta la IP
                            conectado = inetAddress.isReachable(1000);

                            if(conectado){
                                myHost = inetAddress.getHostAddress();
                            }

                            //cambio visual en el textview
                            //manera diferente a la de Ping para ver si asi funciona en el Uithread
                            /*if(conectado == true){
                                hosts.append.isReachable(1000);
                            }*/


                            runOnUiThread(
                                    ()->{

                                        hosts.setText(myHost);

                                    }
                            );

                            //up the last IP spot
                            Spot ++;

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();


        back2Btn.setOnClickListener(
                (view) -> {
                    //stops thread
                    Spot = 255;

                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);

                }
        );

    }
}