package pl.example.android.garbageapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import pl.example.android.garbageapp.databinding.ActivitySectorBlueBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorBlue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySectorBlueBinding binding  =  DataBindingUtil.setContentView(this, R.layout.activity_sector_blue);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter();
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(sectorTermsAdapter);

    }

    public void showToast(View view) {
        Toast.makeText(this, "Przypomnienie ustawione dla STREFY II", Toast.LENGTH_LONG).show();
    }
}
