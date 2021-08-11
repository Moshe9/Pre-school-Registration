package za.ac.cut.harvestseedpre_school;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class HarvestBackendless extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.initApp(getApplicationContext(),"865099D5-01B3-B407-FFC6-A42EE3799F00","82C45152-96DE-14F4-FFED-6490DC230D00");
        Backendless.Messaging.registerDevice("758153085062", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void aVoid) {

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
    }
}
