package com.brian.whiteboardtest;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    WhiteBoardView whiteBoardCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addListeners();
    }


    public void addListeners() {

        whiteBoardCurrent = (WhiteBoardView) findViewById(R.id.customwhiteboard1);


        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                whiteBoardCurrent.undo();

            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                whiteBoardCurrent.redo();

            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                whiteBoardCurrent.captureAndSend();
                Intent sendpicIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendpicIntent.setType("image/jpeg");
                String aEmailList[] = { "bnsawyer@aol.com" };
                sendpicIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);

                sendpicIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Look--a pic!");
                sendpicIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "attached here");

                File sdCard = Environment.getExternalStorageDirectory();
                String PATH=sdCard.toString()+"/screencap.jpg";

                  sendpicIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file://"+PATH));
               startActivity(Intent.createChooser(sendpicIntent, "Send mail..."));

            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                whiteBoardCurrent.erase();

            }
        });

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


            }
        });




        Button color1 = (Button) findViewById(R.id.color1);
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFF000000);

            }
        });

        final Button color2 = (Button) findViewById(R.id.color2);
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFF333333);

            }
        });
        Button color3 = (Button) findViewById(R.id.color3);
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFF666666);

            }
        });
        Button color4 = (Button) findViewById(R.id.color4);
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFF0000FF);

            }
        });
        Button color5 = (Button) findViewById(R.id.color5);
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFFFF0000);

            }
        });
        Button color6 = (Button) findViewById(R.id.color6);
        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFF00FF00);

            }
        });
        Button color7 = (Button) findViewById(R.id.color7);
        color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFFDDAA00);

            }
        });
        Button color8 = (Button) findViewById(R.id.color8);
        color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFFFFFF00);

            }
        });
        Button color9 = (Button) findViewById(R.id.color9);
        color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                whiteBoardCurrent.setPenColor(0xFFFFFF);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class WhiteBoardBackground extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
              try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {
            whiteBoardCurrent.invalidate();

        }
    }
}
