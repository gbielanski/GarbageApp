package pl.example.android.garbageapp.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorType;
import pl.example.android.garbageapp.utilities.InjectorUtils;
import pl.example.android.garbageapp.utilities.SectorTermsUtil;


public abstract class BaseActivitySector extends AppCompatActivity {

    public static final String NOTIFICATION_SECTOR_TYPE = "NOTIFICATION_SECTOR_TYPE";

    protected DetailActivityViewModel mViewModel;

    protected abstract FragmentActivity currentSector();

    protected abstract SectorType sectorType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(currentSector().getApplicationContext(), sectorType());
        mViewModel = ViewModelProviders.of(currentSector(), factory).get(DetailActivityViewModel.class);
    }

    protected boolean isMarkedForNotification(){
        int notificationSectorType = SectorType.toInt(SectorType.UNSET);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        if(sharedPreferences.contains(NOTIFICATION_SECTOR_TYPE))
            notificationSectorType = sharedPreferences.getInt(NOTIFICATION_SECTOR_TYPE, notificationSectorType);

        return notificationSectorType == SectorType.toInt(sectorType());
    }

    protected void markForNotification(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NOTIFICATION_SECTOR_TYPE, SectorType.toInt(sectorType()));
        editor.apply();
        if(((Switch) view).isChecked())
            Toast.makeText(currentSector(), getString(R.string.setup_notification, sectorType().toString()), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(currentSector(), getString(R.string.remove_notification, sectorType().toString()), Toast.LENGTH_LONG).show();

    }
}
