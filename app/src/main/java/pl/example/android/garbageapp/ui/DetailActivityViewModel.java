package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorType;

public class DetailActivityViewModel extends ViewModel {
    private final LiveData<SectorTerm> mSectorTerm;
    private final SectorType sectorType;
    //TODO private final SectorTermRepository mRepository;

    public DetailActivityViewModel(/* repository here,*/ SectorType sectorType) {
        this.sectorType = sectorType;

        //TODO get live data from repository
        mSectorTerm = null;

    }
}
