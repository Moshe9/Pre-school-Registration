package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
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

public class SelectLearnerEdit extends AppCompatActivity {

    ListView lst_edit;
    ArrayList<Learner> learnerList;
    int pos;
    AdapterLearnerUsers adapter;
    DilatingDotsProgressBar progressBarLearner;
    Learner learner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learner_edit);
        ReuseClass.setSupportActionBarView(this,"Select a Learner").setIcon(R.mipmap.ic_launcher);

        lst_edit = (ListView) findViewById(R.id.lst_learner_edit);
        progressBarLearner = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        learnerList = new ArrayList<>();
        adapter = new AdapterLearnerUsers(getApplicationContext(),learnerList);
        getAllLearners();
        lst_edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                Intent intent1 = new Intent(SelectLearnerEdit.this, EditLearner.class);
                intent1.putExtra("name", learnerList.get(position).getName());
                intent1.putExtra("surname", learnerList.get(position).getSurname());
                intent1.putExtra("className", learnerList.get(position).getClassName());
                intent1.putExtra("gender", learnerList.get(position).getGender());
                intent1.putExtra("race", learnerList.get(position).getRace());
                intent1.putExtra("birthdate", learnerList.get(position).getBirthdate());
                intent1.putExtra("languageInstruction", learnerList.get(position).getLanguageInstruction());
                intent1.putExtra("idNumber", learnerList.get(position).getIdNumber());
                intent1.putExtra("dateEnrolled", learnerList.get(position).getDateEnrolled());
                intent1.putExtra("address", learnerList.get(position).getAddress());
                intent1.putExtra("type_student", learnerList.get(position).getType_student());
                intent1.putExtra("motherName", learnerList.get(position).getMotherName());
                intent1.putExtra("fatherName", learnerList.get(position).getFatherName());
                intent1.putExtra("motherEmailAddress", learnerList.get(position).getMotherEmailAddress());
                intent1.putExtra("fatherEmailAddress", learnerList.get(position).getFatherEmailAddress());
                intent1.putExtra("motherContactNumber", learnerList.get(position).getMotherContactNumber());
                intent1.putExtra("fatherContactNumber", learnerList.get(position).getFatherContactNumber());
                intent1.putExtra("doctorName", learnerList.get(position).getDoctorName());
                intent1.putExtra("doctorContactNumber", learnerList.get(position).getDoctorContactNumber());
                intent1.putExtra("medicalAidName", learnerList.get(position).getMedicalAidName());
                intent1.putExtra("medicalAidPlan", learnerList.get(position).getMedicalAidPlan());
                intent1.putExtra("medicalAidNumber", learnerList.get(position).getMedicalAidNumber());
                intent1.putExtra("alergiesOfLearner", learnerList.get(position).getAlergiesOfLearner());
                intent1.putExtra("tuckBalance", learnerList.get(position).getTuckBalance());
                intent1.putExtra("objectId", learnerList.get(position).getObjectId());

                startActivity(intent1);
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
                        lst_edit.setAdapter(adapter);
                        progressBarLearner.hideNow();
                    }


                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(SelectLearnerEdit.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {

    }
}
