package pl.example.android.garbageapp.utilities;

import android.content.Context;

import pl.example.android.garbageapp.data.SectorTermRepository;
import pl.example.android.garbageapp.ui.DetailViewModelFactory;
import pl.example.android.garbageapp.data.database.SectorColor;

import static pl.example.android.garbageapp.utils.InjectorUtils.provideRepository;

public class InjectorUtils {
    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, SectorColor sectorColor) {
        SectorTermRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(repository, sectorColor);
    }
}
