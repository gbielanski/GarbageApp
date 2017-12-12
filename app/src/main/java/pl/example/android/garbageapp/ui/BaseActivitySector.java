package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.utilities.InjectorUtils;


public abstract class BaseActivitySector extends AppCompatActivity {

    public static final String NOTIFICATION_SECTOR_TYPE = "NOTIFICATION_SECTOR_TYPE";

    protected DetailActivityViewModel mViewModel;

    protected abstract FragmentActivity currentSector();

    protected abstract SectorColor sectorType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(currentSector().getApplicationContext(), sectorType());
        mViewModel = ViewModelProviders.of(currentSector(), factory).get(DetailActivityViewModel.class);
        mViewModel.getSectorTerms().observe(this, sectorTerms ->{
            if(sectorTerms != null)
                bindDataToUI(sectorTerms);
        });
    }

    protected abstract void bindDataToUI(List<SectorTerm> sectorTerms);

    protected boolean isMarkedForNotification(){
        int notificationSectorType = SectorColor.toInt(SectorColor.UNSET);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        if(sharedPreferences.contains(NOTIFICATION_SECTOR_TYPE))
            notificationSectorType = sharedPreferences.getInt(NOTIFICATION_SECTOR_TYPE, notificationSectorType);

        return notificationSectorType == SectorColor.toInt(sectorType());
    }

    protected void markForNotification(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NOTIFICATION_SECTOR_TYPE, SectorColor.toInt(sectorType()));
        editor.apply();
        if(((Switch) view).isChecked())
            Toast.makeText(currentSector(), getString(R.string.setup_notification, sectorType().toString()), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(currentSector(), getString(R.string.remove_notification, sectorType().toString()), Toast.LENGTH_LONG).show();

    }
}
