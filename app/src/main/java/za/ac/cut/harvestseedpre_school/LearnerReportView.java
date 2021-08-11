package za.ac.cut.harvestseedpre_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

public class LearnerReportView extends AppCompatActivity {


    ListView lvUsers;
    ArrayList<LearnerReport> mList;
    AdapterLearnerReports adapter;
    DilatingDotsProgressBar circularBar;
    LearnerReport lr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_report_view);
        ReuseClass.setSupportActionBarView(this, "Learner Reports").setIcon(R.mipmap.ic_launcher);
        lvUsers = (ListView) findViewById(R.id.lst_report_learners);
        circularBar = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        mList = new ArrayList<>();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
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

    public void loadData() {
        circularBar.showNow();
        if (mList != null) {
            mList.clear();
        }
        Backendless.Persistence.of(LearnerReport.class).find(new AsyncCallback<List<LearnerReport>>() {
            @Override
            public void handleResponse(List<LearnerReport> learnerReports) {

                    mList.addAll(learnerReports);
                    if (mList != null) {
                        adapter = new AdapterLearnerReports(mList,getApplicationContext());
                        lvUsers.setAdapter(adapter);
                        circularBar.hideNow();
                    }

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(LearnerReportView.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                circularBar.hideNow();
            }
        });
    }
}
