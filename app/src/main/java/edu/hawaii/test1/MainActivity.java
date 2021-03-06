package edu.hawaii.test1;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draw("content://sms", 10000);
    }

    private void draw(String s, int t) {

        final Handler handler = new Handler();
        final String string = s;
        final int time = t;


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Uri sms_content = Uri.parse(string);
                Cursor c = getContentResolver().query(sms_content, null, "read = 0", null, null);
                c.moveToFirst();
                int unreadMessagesCount = c.getCount();

                ImageView myImageView = (ImageView) findViewById(R.id.fish1);
                ImageView myImageView_2 = (ImageView) findViewById(R.id.fish2);
                ImageView myImageView_3 = (ImageView) findViewById(R.id.fish3);

                TextView myTextView = (TextView) findViewById(R.id.textView);

                myTextView.setText("Message count: " + unreadMessagesCount);

                AnimatorSet fishSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animation);;
                AnimatorSet fishSet_2 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animation);;
                AnimatorSet fishSet_3 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animation);;

                fishSet.setTarget(myImageView);
                fishSet_2.setTarget(myImageView_2);
                fishSet_3.setTarget(myImageView_3);

                Bitmap bit;
                Bitmap bit_2;
                Bitmap bit_3;

                if (unreadMessagesCount > 0) {

                    if (unreadMessagesCount == 1) {
                        bit = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
                        myImageView.setImageBitmap(bit);

                        fishSet.start();

                    }else if (unreadMessagesCount == 2) {
                        bit = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
                        bit_2 = BitmapFactory.decodeResource(getResources(), R.drawable.fish_2);

                        myImageView.setImageBitmap(bit);
                        myImageView_2.setImageBitmap(bit_2);

                        fishSet.start();
                        fishSet_2.start();

                    }else {
                        bit = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
                        bit_2 = BitmapFactory.decodeResource(getResources(), R.drawable.fish_2);
                        bit_3 = BitmapFactory.decodeResource(getResources(), R.drawable.fish_3);

                        myImageView.setImageBitmap(bit);
                        myImageView_2.setImageBitmap(bit_2);
                        myImageView_3.setImageBitmap(bit_3);

                        fishSet.start();
                        fishSet_2.start();
                        fishSet_3.start();

                    }
                }

                handler.postDelayed(this, time);

            }
        }, time);
    }
}