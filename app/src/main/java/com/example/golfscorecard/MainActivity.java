package com.example.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.golfscorecard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_FILE = "com.example.golfscorecard.preferences";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Hole[] holes = new Hole[18];
    private HoleAdapter adapter;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Initialize holes
        int strokes = 0;
        for (int i=0; i<holes.length; i++){
            strokes = sharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            holes[i] = new Hole("Hole " + (i+1) + ":", strokes);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new HoleAdapter(holes, this);
        binding.scoreList.setAdapter(adapter);
        binding.scoreList.setHasFixedSize(true);
        //binding.scoreList.getItemAnimator().setChangeDuration(0);
        binding.scoreList.setLayoutManager(new LinearLayoutManager(this));
    }

    //save stroke count even if app is closed
    @Override
    protected void onPause() {
        super.onPause();

        for (int i=0; i<holes.length; i++){
            editor.putInt(KEY_STROKECOUNT + i, holes[i].getStrokeCount());
        }
        editor.apply();
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
        if (id == R.id.action_clear_strokes) {
            editor.clear();
            editor.apply();

            for (Hole hole : holes) {
                hole.setStrokeCount(0);
            }

            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}