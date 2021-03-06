package pl.example.android.garbageapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.data.database.SectorTerm;

class DetailActivityViewModel extends ViewModel {
    private final LiveData<List<SectorTerm>> mSectorTerms;
    private final SectorTermRepository mRepository;

    DetailActivityViewModel(SectorTermRepository repository, SectorColor sectorColor) {
        mRepository = repository;
        mSectorTerms = mRepository.getCurrentSectorTerms(sectorColor);
    }

    LiveData<List<SectorTerm>> getSectorTerms() {
        return mSectorTerms;
    }
}
