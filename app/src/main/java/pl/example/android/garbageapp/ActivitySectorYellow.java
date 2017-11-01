package pl.example.android.garbageapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import pl.example.android.garbageapp.databinding.ActivitySectorBlueBinding;
import pl.example.android.garbageapp.databinding.ActivitySectorYellowBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorYellow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySectorYellowBinding binding  =  DataBindingUtil.setContentView(this, R.layout.activity_sector_yellow);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter(R.color.colorSectorYellowPrimary);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(sectorTermsAdapter);
    }

    public void showToast(View view) {
        Toast.makeText(this, "Przypomnienie ustawione dla STREFY III", Toast.LENGTH_LONG).show();
    }
}
