package pl.example.android.garbageapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.databinding.ActivitySectorBlueBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorBlue extends BaseActivitySector {

    private ActivitySectorBlueBinding binding;

    @Override
    protected FragmentActivity currentSector() {
        return this;
    }

    @Override
    protected SectorColor sectorColor() {
        return SectorColor.BLUE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_blue);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter(R.color.colorSectorBluePrimary);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(sectorTermsAdapter);
        binding.notificationSwitch.setOnClickListener(this::markForNotification);

        if(isMarkedForNotification())
            binding.notificationSwitch.setChecked(true);

    }

    @Override
    protected void bindDataToUI(List<SectorTerm> sectorTerms) {
        SectorTermsAdapter adapter = (SectorTermsAdapter) binding.rcSectorTerms.getAdapter();
        adapter.setData(sectorTerms);
    }
}
