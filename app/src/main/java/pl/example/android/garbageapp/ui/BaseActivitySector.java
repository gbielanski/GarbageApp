package pl.example.android.garbageapp.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.utilities.InjectorUtils;


public abstract class BaseActivitySector extends AppCompatActivity {

    protected DetailActivityViewModel mViewModel;

    protected abstract FragmentActivity currentSector();

    protected abstract SectorType sectorType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(currentSector().getApplicationContext(), sectorType());
        mViewModel = ViewModelProviders.of(currentSector(), factory).get(DetailActivityViewModel.class);
    }
}
