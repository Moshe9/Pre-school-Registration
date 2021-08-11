package za.ac.cut.harvestseedpre_school;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceReportView extends AppCompatActivity {

    ListView lvUsers;
    ArrayList<ReportMaintenance> mList;
    AdapterReportMaintenance adapter;
    DilatingDotsProgressBar circularBar;
    ReportMaintenance lr;
    BackendlessUser user;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_report_view);
        ReuseClass.setSupportActionBarView(this, "Maintenance Reports").setIcon(R.mipmap.ic_launcher);
        lvUsers = (ListView) findViewById(R.id.lst_rep_main);
        circularBar = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        mList = new ArrayList<>();
        adapter = new AdapterReportMaintenance(mList, getApplicationContext());
        user = Backendless.UserService.CurrentUser();
        loadData();
        lvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(parent);
                pos = position;
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();


        if (user != null) {
            if (user.getProperty("role").equals("Master")) {
                inflater.inflate(R.menu.delete_context_menu, menu);
            }
        }
    }

    public void loadData() {
        circularBar.showNow();
        if (mList != null) {
            mList.clear();
        }
        Backendless.Persistence.of(ReportMaintenance.class).find(new AsyncCallback<List<ReportMaintenance>>() {
            @Override
            public void handleResponse(List<ReportMaintenance> reportMaintenances) {
                for (int x = 0; x < reportMaintenances.size(); x++) {
                    lr = new ReportMaintenance(reportMaintenances.get(x).getReportTitle(),
                            reportMaintenances.get(x).getReportType(),
                            reportMaintenances.get(x).getReportInformation(),
                            reportMaintenances.get(x).getInserDate(), reportMaintenances.get(x).getStatus());

                    mList.add(lr);
                    if (lr != null) {
                        lvUsers.setAdapter(adapter);
                        circularBar.hideNow();
                    }
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                circularBar.hideNow();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_cancel:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
