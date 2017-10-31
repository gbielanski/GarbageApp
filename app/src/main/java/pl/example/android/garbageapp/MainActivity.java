package pl.example.android.garbageapp;

import android.content.Intent;
import android.os.TokenWatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast.makeText(this, "Przypomnienie ustawione dla STREFY III", Toast.LENGTH_LONG).show();
    }

    public void startSectorGreenDetails(View view) {
        Intent intent = new Intent(MainActivity.this, ActivitySectorGreen.class);
        startActivity(intent);
    }

    public void startSectorBlueDetails(View view) {
        Intent intent = new Intent(MainActivity.this, ActivitySectorBlue.class);
        startActivity(intent);
    }

    public void startSectorYellowDetails(View view) {
        Intent intent = new Intent(MainActivity.this, ActivitySectorYellow.class);
        startActivity(intent);
    }
}
