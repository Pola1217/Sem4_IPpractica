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
    private int Spot = 1;
    private String localIp;
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
                            //comprobacion y barrido de todas las id cambiando el ultimo digito
                            localIp = "192.168.2."+Spot;

                            InetAddress inetAddress = InetAddress.getByName(localIp);
                            String myHost = inetAddress.getHostAddress();


                            conectado = inetAddress.isReachable(500);

                            //cambio visual en el textview
                            runOnUiThread(
                                    ()->{
                                        if(conectado == true){
                                            hosts.append(""+localIp+"\n");
                                        }

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