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
import pl.example.android.garbageapp.utils.NotificationUtils;


public abstract class BaseActivitySector extends AppCompatActivity {

    protected DetailActivityViewModel mViewModel;

    protected abstract FragmentActivity currentSector();

    protected abstract SectorColor sectorColor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(currentSector().getApplicationContext(), sectorColor());
        mViewModel = ViewModelProviders.of(currentSector(), factory).get(DetailActivityViewModel.class);
        mViewModel.getSectorTerms().observe(this, sectorTerms ->{
            if(sectorTerms != null)
                bindDataToUI(sectorTerms);
        });
    }

    protected abstract void bindDataToUI(List<SectorTerm> sectorTerms);

    protected boolean isMarkedForNotification(){
        int notificationSectorColor = SectorColor.toInt(SectorColor.UNSET);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        if(sharedPreferences.contains(NotificationUtils.NOTIFICATION_SECTOR_COLOR))
            notificationSectorColor = sharedPreferences.getInt(NotificationUtils.NOTIFICATION_SECTOR_COLOR, notificationSectorColor);

        return notificationSectorColor == SectorColor.toInt(sectorColor());
    }

    protected void markForNotification(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NotificationUtils.NOTIFICATION_SECTOR_COLOR, SectorColor.toInt(sectorColor()));
        editor.apply();
        if(((Switch) view).isChecked())
            Toast.makeText(currentSector(), getString(R.string.setup_notification, sectorColor().toString()), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(currentSector(), getString(R.string.remove_notification, sectorColor().toString()), Toast.LENGTH_LONG).show();

    }
}
