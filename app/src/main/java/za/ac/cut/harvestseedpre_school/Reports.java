package za.ac.cut.harvestseedpre_school;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import dmax.dialog.SpotsDialog;
import za.ac.cut.harvestseedpre_school.R;

public class Reports extends AppCompatActivity {

    ImageView iv_learner_report, iv_maintenance_report;
    SpotsDialog progressDialog;
    LearnerReport learnerReport;
    ReportMaintenance reportMaintenance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        ReuseClass.setSupportActionBarView(this,"Reports").setIcon(R.mipmap.ic_launcher);
        iv_learner_report = (ImageView) findViewById(R.id.iv_learner_reports);
        iv_maintenance_report =(ImageView) findViewById(R.id.iv_maintenance_reports);
        reportMaintenance = new ReportMaintenance();
        learnerReport = new LearnerReport();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reports_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void AddMaintenanceReport(View v) {
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_maintenance_report_create, null);
        final EditText et_title_maint = (EditText) view.findViewById(R.id.et_report_maintenance_title);
        final String status = "Unresolved";
        final EditText et_Info_m = (EditText) view.findViewById(R.id.et_maintenance_add_info);
        final Spinner sp_rec_m = (Spinner) view.findViewById(R.id.sp_report_type);
        final DatePickerView et_insert_date_m = (DatePickerView) view.findViewById(R.id.dpv_insert_date_m);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(view);

        dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (ReuseClass.isConnectionAvailable(getApplicationContext())) {
                    progressDialog = new SpotsDialog(Reports.this, R.style.SpotsDialogCustom);
                    progressDialog.show();

                    if (et_title_maint.getText().toString().isEmpty() || et_Info_m.getText().toString().isEmpty() ||
                            et_insert_date_m.getText().toString().isEmpty()) {
                        Toast.makeText(Reports.this, "Please enter all fields!!", Toast.LENGTH_SHORT).show();
                    } else {

                        reportMaintenance.setReportTitle(et_title_maint.getText().toString().trim());
                        reportMaintenance.setReportInformation(et_Info_m.getText().toString().trim());
                        reportMaintenance.setReportType(sp_rec_m.getSelectedItem().toString().trim());
                        reportMaintenance.setInserDate(et_insert_date_m.getText().toString().trim());
                        reportMaintenance.setStatus(status);
                        Backendless.Persistence.save(reportMaintenance, new AsyncCallback<ReportMaintenance>() {
                            @Override
                            public void handleResponse(ReportMaintenance reportMaintenance) {
                                progressDialog.dismiss();
                                Toast.makeText(Reports.this, "Maintenance Report Created!!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                progressDialog.dismiss();
                            }
                        });

                    }
                } else {
                    Toast.makeText(Reports.this, "No internet connection available", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
    public void AddLearnerReport(View v) {
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_learner_report_create, null);
        final EditText etLearner = (EditText) view.findViewById(R.id.et_report_learner_name);
        final EditText etTitle = (EditText) view.findViewById(R.id.et_learner_report_title);
        final EditText et_Info = (EditText) view.findViewById(R.id.et_learner_report_info);
        final Spinner sp_rec = (Spinner) view.findViewById(R.id.sp_l_type);
        final DatePickerView et_insert_date = (DatePickerView) view.findViewById(R.id.e_learner_report_insert);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(view);
        dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (ReuseClass.isConnectionAvailable(getApplicationContext())) {
                    progressDialog = new SpotsDialog(Reports.this, R.style.SpotsDialogCustom);
                    progressDialog.show();

                    if (etLearner.getText().toString().isEmpty() || etTitle.getText().toString().isEmpty() ||
                            et_Info.getText().toString().isEmpty()) {
                        Toast.makeText(Reports.this, "Please enter all fields!!", Toast.LENGTH_SHORT).show();
                    } else {

                        learnerReport.setLearnerName(etLearner.getText().toString().trim());
                        learnerReport.setReportTitle(etTitle.getText().toString().trim());
                        learnerReport.setLearnerReportRecommendation(sp_rec.getSelectedItem().toString().trim());
                        learnerReport.setReportInformation(et_Info.getText().toString().trim());
                        learnerReport.setInsertDate(et_insert_date.getText().toString().trim());
                        Backendless.Persistence.save(learnerReport, new AsyncCallback<LearnerReport>() {
                            @Override
                            public void handleResponse(LearnerReport learnerReport) {
                                progressDialog.dismiss();
                                Toast.makeText(Reports.this, "Learner Report Created!!", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                Toast.makeText(Reports.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });

                    }
                } else {
                    Toast.makeText(Reports.this, "No internet connection available", Toast.LENGTH_SHORT).show();

                }


            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_reports_cancel:
                this.finish();
                break;
            case R.id.mnu_lreps:
                startActivity(new Intent(Reports.this, LearnerReportView.class));
                break;
            case R.id.mnu_m_reps:
               startActivity(new Intent(Reports.this, MaintenanceReportView.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }
}
