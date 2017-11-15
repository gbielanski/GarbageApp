package pl.example.android.garbageapp.utilities;

import android.content.Context;

import pl.example.android.garbageapp.ui.DetailViewModelFactory;
import pl.example.android.garbageapp.data.database.SectorType;

public class InjectorUtils {
    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, SectorType sectorType) {
        //SectorTermRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(/*repository,*/ sectorType);
    }
}
