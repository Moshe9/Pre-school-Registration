package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

public class ClassB extends AppCompatActivity {

    ListView lvUsers;
    List<Learner> usersList;
    AdapterLearnerUsers adapterLearnerUsers;
    DilatingDotsProgressBar circularBar;
    Learner learner;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_b);
        ReuseClass.setSupportActionBarView(this, "View Class B").setIcon(R.mipmap.ic_launcher);
        lvUsers = (ListView) findViewById(R.id.lst_classB);
        circularBar = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        usersList = new ArrayList<>();
        adapterLearnerUsers = new AdapterLearnerUsers(getApplicationContext(), usersList);
        loadData();
        OnClicks();
    }
    private void OnClicks() {
        lvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(parent);
                pos = position;
                return false;
            }
        });

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ClassB.this, LearnerInformation.class);
                intent1.putExtra("name", usersList.get(position).getName());
                intent1.putExtra("surname", usersList.get(position).getSurname());
                intent1.putExtra("className", usersList.get(position).getClassName());
                intent1.putExtra("languageInstruction", usersList.get(position).getLanguageInstruction());
                intent1.putExtra("type_student", usersList.get(position).getType_student());
                intent1.putExtra("birthdate", usersList.get(position).getBirthdate());
                intent1.putExtra("alergiesOfLearner", usersList.get(position).getAlergiesOfLearner());
                intent1.putExtra("dateEnrolled", usersList.get(position).getDateEnrolled());
                intent1.putExtra("doctorContactNumber", usersList.get(position).getDoctorContactNumber());
                intent1.putExtra("medicalAidName", usersList.get(position).getMedicalAidName());
                intent1.putExtra("medicalAidNumber", usersList.get(position).getMedicalAidNumber());
                intent1.putExtra("medicalAidPlan", usersList.get(position).getMedicalAidPlan());
                intent1.putExtra("doctorName", usersList.get(position).getDoctorName());
                intent1.putExtra("idNumber", usersList.get(position).getIdNumber());
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

    public void loadData() {
        circularBar.showNow();

        if (usersList != null) {
            usersList.clear();
        }
        String whereClause = "className = 'Class B'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("name");
        Backendless.Persistence.of(Learner.class).find(queryBuilder, new AsyncCallback<List<Learner>>() {
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


                    usersList.add(learner);
                    if (learner != null) {
                        lvUsers.setAdapter(adapterLearnerUsers);

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
