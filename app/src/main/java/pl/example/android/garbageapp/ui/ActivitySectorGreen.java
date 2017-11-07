package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.databinding.ActivitySectorGreenBinding;
import pl.example.android.garbageapp.utilities.InjectorUtils;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ActivitySectorGreen extends AppCompatActivity {

    private DetailActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySectorGreenBinding binding  =  DataBindingUtil.setContentView(this, R.layout.activity_sector_green);
        SectorTermsAdapter sectorTermsAdapter = new SectorTermsAdapter(R.color.colorSectorGreenPrimary);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        binding.rcSectorTerms.setLayoutManager(verticalLinearLayoutManager);
        binding.rcSectorTerms.setAdapter(sectorTermsAdapter);

        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(this.getApplicationContext(), SectorType.GREEN);
        mViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel.class);

        //TODO mViewModel observe and bind data to UI
    }

    public void showToast(View view) {
        Toast.makeText(this, "Przypomnienie ustawione dla STREFY I", Toast.LENGTH_LONG).show();
    }
}
