package pl.example.android.garbageapp.ui;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import pl.example.android.garbageapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast.makeText(this, "Przypomnienie ustawione dla STREFY III", Toast.LENGTH_LONG).show();
    }

    public void onClickSectorGreenDetails(View view) {
        animateCircularReveal(view);
        startSectorGreenDetails();
    }

    public void onClickSectorBlueDetails(View view) {
        animateCircularReveal(view);
        startSectorBlueDetails();
    }

    public void onClickSectorYellowDetails(View view) {
        animateCircularReveal(view);
        startSectorYellowDetails();
    }

    private void animateCircularReveal(View view) {
        int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);
        Animator anim = ViewAnimationUtils.createCircularReveal(view,
                (int) view.getWidth() / 2,
                (int) view.getHeight() / 2,
                0, finalRadius);
        anim.start();
    }

    private void startSectorGreenDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorGreen.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorBlueDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorBlue.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorYellowDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorYellow.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }
}
