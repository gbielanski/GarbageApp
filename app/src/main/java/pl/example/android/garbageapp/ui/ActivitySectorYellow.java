package pl.example.android.garbageapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.databinding.ActivitySectorYellowBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorYellow extends BaseActivitySector {

    @Override
    protected FragmentActivity currentSector() {
        return this;
    }

    @Override
    protected SectorType sectorType() {
        return SectorType.YELLOW;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySectorYellowBinding binding  =  DataBindingUtil.setContentView(this, R.layout.activity_sector_yellow);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter(R.color.colorSectorYellowPrimary);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(sectorTermsAdapter);
        binding.notificationSwitch.setOnClickListener(this::markForNotification);

        if(isMarkedForNotification())
            binding.notificationSwitch.setChecked(true);

        //TODO mViewModel observe and bind data to UI
    }
}
