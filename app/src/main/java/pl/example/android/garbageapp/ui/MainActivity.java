package pl.example.android.garbageapp.ui;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import pl.example.android.garbageapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eight_sectors);
    }

    public void onClickSectorPinkDetails(View view) {
        animateCircularReveal(view);
        startSectorPinkDetails();
    }

    public void onClickSectorDeepPurpleDetails(View view) {
        animateCircularReveal(view);
        startSectorDeepPurpleDetails();
    }

    public void onClickSectorGreenDetails(View view) {
        animateCircularReveal(view);
        startSectorGreenDetails();
    }

    public void onClickSectorAmberDetails(View view) {
        animateCircularReveal(view);
        startSectorAmberDetails();
    }

    public void onClickSectorOrangeDetails(View view) {
        animateCircularReveal(view);
        startSectorOrangeDetails();
    }

    public void onClickSectorDeepOrangeDetails(View view) {
        animateCircularReveal(view);
        startSectorDeepOrangeDetails();
    }

    public void onClickSectorPurpleDetails(View view) {
        animateCircularReveal(view);
        startSectorPurpleDetails();
    }

    public void onClickSectorIndigoDetails(View view) {
        animateCircularReveal(view);
        startSectorIndigoDetails();
    }

    private void animateCircularReveal(View view) {
        int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);
        Animator anim = ViewAnimationUtils.createCircularReveal(view,
                (int) view.getWidth() / 2,
                (int) view.getHeight() / 2,
                0, finalRadius);
        anim.start();
    }

    private void startSectorPinkDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorPink.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorDeepPurpleDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorDeepPurple.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorGreenDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorGreen.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorAmberDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorAmber.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorOrangeDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorOrange.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorDeepOrangeDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorDeepOrange.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorPurpleDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorPurple.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    private void startSectorIndigoDetails() {
        Intent intent = new Intent(MainActivity.this, ActivitySectorIndigo.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent, bundle);
    }

    public void onClickPolicy(View view) {
        String URL = "https://gbielanski.wixsite.com/wystaw-smieci";
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(myIntent);
    }
}
