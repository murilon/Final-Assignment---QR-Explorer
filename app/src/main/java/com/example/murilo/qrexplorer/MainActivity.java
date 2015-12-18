package com.example.murilo.qrexplorer;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton cameraButton = (ImageButton)findViewById(R.id.buttonCamera);
        ImageButton searchButton = (ImageButton)findViewById(R.id.buttonSearch);
        ImageButton rankingButton = (ImageButton)findViewById(R.id.buttonRanking);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameralistener();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchListener();
            }
        });
        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankingListener();
            }
        });
    }

    void cameralistener(){



        try {
            /**
             * Call the Zxing Bar Code Scanner Intent
             */
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");

            /**
             *  Start the Scanner Activity, if the scanner get something, return 0
             */
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error!");
            builder.setMessage("Please download Bar Code Scanner on Play Store");
            builder.setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });

            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }


    }

    void searchListener(){

        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }

    void rankingListener(){

        Intent intent = new Intent(this,RankingActivity.class);
        startActivity(intent);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        if (requestCode == 0) {

            /**
             * As defined before, if the activity returns 0, the scan was successful
             */

            if (resultCode == RESULT_OK) {
                alertDialog.setTitle("QR Code Scan Result");
                alertDialog.setMessage(intent.getStringExtra("SCAN_RESULT"));
                alertDialog.show();
            } else if (resultCode == RESULT_CANCELED) {

                alertDialog.setTitle("QR Code Scan Result");
                alertDialog.setMessage("Scan cancelled");
                alertDialog.show();
            }
        }
    }


}
