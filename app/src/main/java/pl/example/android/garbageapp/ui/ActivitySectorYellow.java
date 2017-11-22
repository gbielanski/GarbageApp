package pl.example.android.garbageapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.databinding.ActivitySectorYellowBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorYellow extends BaseActivitySector {

    private ActivitySectorYellowBinding binding;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_yellow);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter(R.color.colorSectorYellowPrimary);
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
