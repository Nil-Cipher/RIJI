package com.example.riji;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.riji.Adapters.DayListAdapter;
import com.example.riji.Day_related.Day;
import com.example.riji.HandlerThreads.WorkerThreadMonth;
import com.example.riji.Month_related.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MonthActivity extends AppCompatActivity implements WorkerThreadMonth.Callback, DayListAdapter.onNoteListener {

    private static final String DEBUG_TAG = "Gestures";
    private final List<Day> mDays = new ArrayList<>();
    //get current time
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    //getTime() returns the current date in default time zone
    int day = calendar.get(Calendar.DATE);
    //Note: +1 the month for current month
    int month = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR);
    float x1, x2, y1, y2;
    private GestureDetectorCompat mDetector;
    private RecyclerView mRecyclerView;
    private DayListAdapter mAdapter;
    private Month month1;
    private long month_id;
    private WorkerThreadMonth mWorkerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        monthBackYear();

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new DayListAdapter(this, mDays, this.getApplication(), this);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle bund = getIntent().getExtras();
        //get the current year and month
        if (bund != null) {
            year = bund.getInt("year");
            month = bund.getInt("month");
        }
        Button backButton = findViewById(R.id.Twenty19);
        backButton.setText(Integer.toString(year));

        TextView theMonth = findViewById(R.id.JAN);
        switch (month) {
            case 1:
                theMonth.setText("January");
                break;
            case 2:
                theMonth.setText("Feburary");
                break;
            case 3:
                theMonth.setText("March");
                break;
            case 4:
                theMonth.setText("April");
                break;
            case 5:
                theMonth.setText("May");
                break;
            case 6:
                theMonth.setText("June");
                break;
            case 7:
                theMonth.setText("July");
                break;
            case 8:
                theMonth.setText("August");
                break;
            case 9:
                theMonth.setText("September");
                break;
            case 10:
                theMonth.setText("October");
                break;
            case 11:
                theMonth.setText("November");
                break;
            case 12:
                theMonth.setText("December");
                break;
        }

        mWorkerThread = new WorkerThreadMonth(new Handler(), this, this);
        mWorkerThread.start();
        mWorkerThread.prepareHandlerMonth();
        mWorkerThread.queueMonth(year, month);

        SearchView sv = findViewById(R.id.search_bar);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bund = new Bundle();
                bund.putString("term","%"+query+"%");

                Intent i = new Intent(MonthActivity.this, SearchActivity.class);
                i.putExtras(bund);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void monthBackYear() {
        Button backButton = findViewById(R.id.Twenty19);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert year and month data to be transfered to MonthActivity class
                Bundle bund = new Bundle();
                bund.putInt("year", year);
                Intent j = new Intent(MonthActivity.this, YearActivity.class);
                j.putExtras(bund);
                startActivity(j);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });
    }

    public void monthToday(View view) {
        startActivity(new Intent(MonthActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
        finish();
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x1 > x2) {
                    //insert year and month data to be transfered to MonthActivity class
                    Bundle bund = new Bundle();
                    bund.putInt("year", year);
                    bund.putInt("month", month);
                    bund.putInt("day", 1);

                    Intent i = new Intent(MonthActivity.this, MainActivity.class);
                    i.putExtras(bund);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
                if (x1 < x2) {
                    //insert year and month data to be transfered to MonthActivity class
                    Bundle bund = new Bundle();
                    bund.putInt("year", year);
                    Intent j = new Intent(MonthActivity.this, YearActivity.class);
                    j.putExtras(bund);
                    startActivity(j);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }break;
        }return false;
    }

    @Override
    protected void onDestroy() {
        mWorkerThread.quit();
        super.onDestroy();
    }

    //bug here, list is null
    @Override
    public void onDayFound(final LiveData<List<Day>> days) {
        days.observe(this, new Observer<List<Day>>() {
            @Override
            public void onChanged(@Nullable List<Day> days) {
                // Update the cached copy of days
                mAdapter.setDays(days);
            }
        });
    }

    @Override
    public void onMonthFound(Month month, long month_id) {
        month1 = month;
        this.month_id = month_id;
        mWorkerThread.prepareHandlerDay();
        mWorkerThread.queueDay(year, this.month);
    }

    @Override
    public void onNoteClick(int position) {

    }
}
