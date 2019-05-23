package com.example.riji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riji.BulletPoint_related.BulletPoint;
import com.example.riji.Month_related.Month;
import com.example.riji.Year_related.Year;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Typeface.create;

public class TableofYear extends AppCompatActivity {

    float x1, x2, y1, y2;
    private final List<Year> mYear = new ArrayList<>();
    private YearListAdapter mAdapter;
    private String mString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableof_year);

        // Get a handle to the RecyclerView.
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new YearListAdapter(this, mYear);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*final Button addBullet = findViewById(R.id.addYear);
        addBullet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //employ an alert dialogue, not simply a dialogue(imagine these as pop up window)
                //AlertDialog.Builder builder = new AlertDialog.Builder(TableofYear.this);
                //builder.setTitle("Add Bullet Point");

                //inflate the dialogue with the layout in the xml activity_display_message
                LayoutInflater layoutInflater = LayoutInflater.from(TableofYear.this);

                @SuppressLint("InflateParams") View popupInputDialogView = layoutInflater.inflate(R.layout.activity_display_message, null);
                builder.setView(popupInputDialogView);
                final EditText bullet = popupInputDialogView.findViewById(R.id.bullet);
                symbol = popupInputDialogView.findViewById(R.id.symbol);

                // Set up the buttons
                final AlertDialog dialog = builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(bullet.getText())) {
                            dialog.dismiss();
                            Toast toast = Toast.makeText(MainActivity.this, "Cannot store empty string.", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            dialog.dismiss();
                            mString = bullet.getText().toString();
                            //what shows on the screen
                            mBPViewModel.insert(new BulletPoint(bulletType, mString, id));
                        }
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

                //2. now setup to change color of the button
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                    }
                });
                dialog.show();
            }
        });*/
    }

    public void tableToday(View view) {
        startActivity(new Intent(TableofYear.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
        finish();
    }

    public void twentyNineteen(View view) {
        startActivity(new Intent(TableofYear.this, YearActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1>x2)
                {
                    Intent i = new Intent(TableofYear.this, YearActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                break;
        }return false;
    }



    /*public void generateNewYear(View v)
    {
        //the number button
        Button newButton = new Button(this);
        Typeface dosisRegular = ResourcesCompat.getFont(this, R.font.dosis);
        newButton.setTypeface(dosisRegular);
        newButton.setText("2020");
        newButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        newButton.setTextSize(60);
        LinearLayout ll = findViewById(R.id.two);
        ll.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        //findViewById(R.id.two);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams ls = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);


        //the image button
        ImageButton newImage = new ImageButton(this);
        newImage.setBackgroundResource(R.mipmap.mousy);
        newImage.setPadding(60, 0,60, 0 );
        ll.addView(newButton, lp); ll.addView(newImage, ls);

    }*/
}
