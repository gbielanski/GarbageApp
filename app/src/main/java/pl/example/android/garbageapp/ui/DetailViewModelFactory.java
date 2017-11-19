package pl.example.android.garbageapp.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorType;

public class DetailViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final SectorTermRepository mRepository;
    private final SectorType mSectorType;

    public DetailViewModelFactory(SectorTermRepository repository, SectorType sectorType) {
        this.mRepository = repository;
        this.mSectorType = sectorType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailActivityViewModel( mRepository, mSectorType);
    }
}
