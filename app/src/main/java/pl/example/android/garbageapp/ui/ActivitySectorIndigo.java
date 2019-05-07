package pl.example.android.garbageapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.databinding.ActivitySectorIndigoBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorIndigo extends BaseActivitySector {

    private ActivitySectorIndigoBinding binding;
    @Override
    protected FragmentActivity currentSector() {
        return this;
    }

    @Override
    protected SectorColor sectorColor() {
        return SectorColor.INDIGO;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSectorTermsAdapter(new SectorTermsAdapter(R.color.sectorIndigo));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_indigo);
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
