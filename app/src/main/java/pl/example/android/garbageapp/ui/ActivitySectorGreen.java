package pl.example.android.garbageapp.ui;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.databinding.ActivitySectorGreenBinding;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorGreen extends BaseActivitySector {

    private ActivitySectorGreenBinding binding;
    @Override
    protected FragmentActivity currentSector() {
        return this;
    }

    @Override
    protected SectorColor sectorColor() {
        return SectorColor.GREEN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSectorTermsAdapter(new SectorTermsAdapter(R.color.sectorGreen));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_green);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(getSectorTermsAdapter());

        if(isMarkedForNotification())
            binding.notificationSwitch.setChecked(true);

        binding.notificationSwitch.setOnCheckedChangeListener(this::markForNotification);

    }

    @Override
    protected void bindDataToUI(List<SectorTerm> sectorTerms) {
        SectorTermsAdapter adapter = (SectorTermsAdapter) binding.rcSectorTerms.getAdapter();
        adapter.setData(sectorTerms);
    }

    @Override
    void checkEmpty() {
        binding.progressBar.setVisibility(getSectorTermsAdapter().getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
