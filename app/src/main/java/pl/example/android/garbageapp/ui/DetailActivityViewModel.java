package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorTerm;
import pl.example.android.garbageapp.data.database.SectorType;

class DetailActivityViewModel extends ViewModel {
    private final LiveData<List<SectorTerm>> mSectorTerms;
    private final SectorType sectorType;
    private final SectorTermRepository mRepository;

    DetailActivityViewModel(SectorTermRepository repository, SectorType sectorType) {
        this.sectorType = sectorType;
        mRepository = repository;
        mSectorTerms = mRepository.getCurrentSectorTerms(sectorType);
    }

    LiveData<List<SectorTerm>> getSectorTerms() {
        return mSectorTerms;
    }
}
