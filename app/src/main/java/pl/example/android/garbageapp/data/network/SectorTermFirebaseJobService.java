package pl.example.android.garbageapp.data.network;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import pl.example.android.garbageapp.utils.InjectorUtils;

/**
 * Created by miltomasz on 11/11/17.
 */

public class SectorTermFirebaseJobService extends JobService {
    private static final String LOG_TAG = SectorTermFirebaseJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(LOG_TAG, "Job service started");

        SectorTermsNetworkDataSource networkDataSource =
                InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchSectorTerms();

        jobFinished(jobParameters, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
