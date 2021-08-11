package za.ac.cut.harvestseedpre_school;

import com.backendless.push.BackendlessBroadcastReceiver;
import com.backendless.push.BackendlessPushService;

public class Reciever extends BackendlessBroadcastReceiver {

    @Override
    public Class<? extends BackendlessPushService> getServiceClass() {
        return BackendlessNotifications.class;
    }
}
