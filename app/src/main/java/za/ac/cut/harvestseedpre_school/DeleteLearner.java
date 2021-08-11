package za.ac.cut.harvestseedpre_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class DeleteLearner extends AppCompatActivity {


    ListView lst_deleteLearner;
    ArrayList<Learner> learnerList;
    int pos;
    AdapterLearnerUsers adapter;
    SpotsDialog progressDialog;
    DilatingDotsProgressBar progressBarLearner;
    Learner learner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_learner);
        ReuseClass.setSupportActionBarView(this,"Remove a Learner").setIcon(R.mipmap.ic_launcher);

        lst_deleteLearner = (ListView) findViewById(R.id.lst_delete_l);
        progressBarLearner = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        learnerList = new ArrayList<>();
        adapter = new AdapterLearnerUsers(getApplicationContext(),learnerList);
        getAllLearners();
        lst_deleteLearner.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
    public void getAllLearners() {
        progressBarLearner.showNow();
        if (learnerList != null) {
            learnerList.clear();
        }

        Backendless.Persistence.of(Learner.class).find(new AsyncCallback<List<Learner>>() {
            @Override
            public void handleResponse(List<Learner> learners) {
                for (int x = 0; x < learners.size(); x++) {
                    learner = new Learner(learners.get(x).getName(), learners.get(x).getSurname(),
                            learners.get(x).getClassName(), learners.get(x).getGender(),
                            learners.get(x).getRace(), learners.get(x).getBirthdate(),
                            learners.get(x).getLanguageInstruction(), learners.get(x).getIdNumber(),
                            learners.get(x).getDateEnrolled(), learners.get(x).getAddress(),
                            learners.get(x).getType_student(), learners.get(x).getMotherName(),
                            learners.get(x).getFatherName(), learners.get(x).getMotherEmailAddress(),
                            learners.get(x).getFatherEmailAddress(), learners.get(x).getMotherContactNumber(),
                            learners.get(x).getFatherContactNumber(), learners.get(x).getDoctorName(),
                            learners.get(x).getDoctorContactNumber(), learners.get(x).getMedicalAidName(),
                            learners.get(x).getMedicalAidPlan(), learners.get(x).getMedicalAidNumber(),
                            learners.get(x).getAlergiesOfLearner(), learners.get(x).getTuckBalance(),
                            learners.get(x).getObjectId(), learners.get(x).getCreated(), learners.get(x).getUpdated());


                    learnerList.add(learner);
                    if (learner != null) {
                        lst_deleteLearner.setAdapter(adapter);
                        progressBarLearner.hideNow();
                    }


                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(DeleteLearner.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                progressBarLearner.setVisibility(View.GONE);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.mnu_learner_delete:
                progressDialog = new SpotsDialog(DeleteLearner.this, R.style.SpotsDialogCustom);
                progressDialog.show();

                Backendless.Persistence.of(Learner.class).findById(learnerList.get(pos).getObjectId(), new AsyncCallback<Learner>() {
                    @Override
                    public void handleResponse(Learner learner) {
                        Backendless.Persistence.of(Learner.class).remove(learner, new AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long aLong) {
                                Toast.makeText(DeleteLearner.this, "Student Deleted!!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                Toast.makeText(DeleteLearner.this, "Error:" + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {

                    }
                });
                break;


        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}
