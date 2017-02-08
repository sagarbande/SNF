package com.example.admin.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText Edit1;
    EditText Edit2;
    android.widget.TextView TextView;
    Button calculate;
    double value1, value2;
    double value3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Bind the EditText views

        Edit1 = (EditText) findViewById(R.id.edit1);
        Edit2 = (EditText) findViewById(R.id.edit2);
        TextView = (TextView) findViewById(R.id.textView);

        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Edit1.getText().toString().isEmpty()) {
                    Edit1.setError("Enter CLR");
                } else if (Edit2.getText().toString().isEmpty()) {
                    Edit2.setError("Enter FAT");
                } else {
                    calculate();
                }

            }
        });
    }


    public void calculate() {
        try {
            value1 = Double.valueOf(Edit1.getText().toString());
            value2 = Double.valueOf(Edit2.getText().toString());

            Log.d("value1", String.valueOf(value1));
            Log.d("value2", String.valueOf(value2));

            value3 = (0.25 * value1 + value2 * 0.25 + 0.44);

            //When the button is clicked, call the calculate method.

            TextView.setText(String.format("SNF: %.2f", value3));
        } catch (NumberFormatException e) {
            Log.d("value1", String.valueOf(value1));
            Log.d("value2", String.valueOf(value2));

            Log.d("Exception", String.valueOf(e));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate main_menu.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mainMenuAbout:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.mainMenuContactUs:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:07803095866"));
                startActivity(callIntent);
                return true;
            case R.id.mainMenuShare:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "App Developed by Sagar,https://drive.google.com/open?id=0B4Q_PK1-dHhbQjJJZmVIRWd2VjQ");
                startActivity(intent);
                return true;
            case R.id.mainMenuExit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}