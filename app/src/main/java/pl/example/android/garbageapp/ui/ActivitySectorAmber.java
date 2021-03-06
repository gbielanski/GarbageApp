package pl.example.android.garbageapp.ui;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.databinding.ActivitySectorAmberBinding;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorAmber extends BaseActivitySector {

    private ActivitySectorAmberBinding binding;
    @Override
    protected FragmentActivity currentSector() {
        return this;
    }

    @Override
    protected SectorColor sectorColor() {
        return SectorColor.AMBER;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSectorTermsAdapter(new SectorTermsAdapter(R.color.sectorAmber));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_amber);
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
