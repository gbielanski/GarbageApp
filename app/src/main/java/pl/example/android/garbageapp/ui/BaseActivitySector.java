package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
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

    private SectorTermsAdapter mSectorTermsAdapter;

    protected RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            checkEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkEmpty();
        }
    };

    public SectorTermsAdapter getSectorTermsAdapter() {
        return mSectorTermsAdapter;
    }

    public void setSectorTermsAdapter(SectorTermsAdapter mSectorTermsAdapter) {
        this.mSectorTermsAdapter = mSectorTermsAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(currentSector().getApplicationContext(), sectorColor());
        mViewModel = ViewModelProviders.of(currentSector(), factory).get(DetailActivityViewModel.class);
        mViewModel.getSectorTerms().observe(this, sectorTerms -> {
            if (sectorTerms != null)
                bindDataToUI(sectorTerms);
        });
    }

    protected abstract void bindDataToUI(List<SectorTerm> sectorTerms);

    protected boolean isMarkedForNotification() {
        int notificationSectorColor = SectorColor.toInt(SectorColor.UNSET);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        if (sharedPreferences.contains(NotificationUtils.NOTIFICATION_SECTOR_COLOR))
            notificationSectorColor = sharedPreferences.getInt(NotificationUtils.NOTIFICATION_SECTOR_COLOR, notificationSectorColor);

        return notificationSectorColor == SectorColor.toInt(sectorColor());
    }

    private void changeReminderForSectorColor(SectorColor sectorColor){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentSector());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NotificationUtils.NOTIFICATION_SECTOR_COLOR, SectorColor.toInt(sectorColor));
        editor.apply();
    }

    protected void markForNotification(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            changeReminderForSectorColor(sectorColor());
            notifyUserReminderHasChanged(R.string.setup_notification);

        }else {
            changeReminderForSectorColor(SectorColor.UNSET);
            notifyUserReminderHasChanged(R.string.remove_notification);
        }
    }

    private void notifyUserReminderHasChanged(int notificationMessage) {
        Toast.makeText(currentSector(), getString(notificationMessage, sectorColor().toString()), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSectorTermsAdapter.registerAdapterDataObserver(mAdapterDataObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSectorTermsAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
    }

    abstract void checkEmpty();
}
