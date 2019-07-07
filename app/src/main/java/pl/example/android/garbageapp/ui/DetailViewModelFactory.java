package pl.example.android.garbageapp.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.data.database.SectorColor;

public class DetailViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final SectorTermRepository mRepository;
    private final SectorColor mSectorColor;

    public DetailViewModelFactory(SectorTermRepository repository, SectorColor sectorColor) {
        this.mRepository = repository;
        this.mSectorColor = sectorColor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailActivityViewModel(mRepository, mSectorColor);
    }
}
