package za.ac.cut.harvestseedpre_school;

import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Date;

import dmax.dialog.SpotsDialog;

public class AddLearner extends AppCompatActivity {

    LinearLayout linearAdd;
    EditText et_l_name, et_l_surname, et_l_address,
            et_l_id ,
            et_mother_name, et_father_name, et_mother_email, et_mother_contact,
            et_father_email, et_father_contact, et_doctor_name,
            et_doctor_contact, et_medical_plan, et_learner_allergies,
            et_learner_balance, et_medical_number;
    Spinner sp_class, sp_gender, sp_race, sp_type, sp_language, sp_medical_name;
    DatePickerView et_l_birth_date,et_l_enroll_date;
    Button btn_next;
    BackendlessUser backendlessUser;
    AlertDialog progressDialog;
    Learner learner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_learner);
        ReuseClass.setSupportActionBarView(this,"Create a Learner").setIcon(R.mipmap.ic_launcher);
        linearAdd = (LinearLayout) findViewById(R.id.linearAdd);
        backendlessUser = Backendless.UserService.CurrentUser();
        et_mother_name = (EditText) findViewById(R.id.et_mother_name);
        et_father_name = (EditText) findViewById(R.id.et_father_name);
        et_mother_email = (EditText) findViewById(R.id.et_mother_email);
        et_mother_contact = (EditText) findViewById(R.id.et_mother_contact);
        et_father_email = (EditText) findViewById(R.id.et_father_email);
        et_father_contact = (EditText) findViewById(R.id.et_father_contact);
        et_doctor_contact = (EditText) findViewById(R.id.et_doctor_contact);
        et_medical_number = (EditText) findViewById(R.id.et_medical_contact);
        et_doctor_name = (EditText) findViewById(R.id.et_doctor_name);
        et_medical_plan = (EditText) findViewById(R.id.et_medical_plan);
        et_learner_allergies = (EditText) findViewById(R.id.et_learner_allergies);
        et_learner_balance = (EditText) findViewById(R.id.et_learner_balance);
        sp_medical_name = (Spinner) findViewById(R.id.sp_medical_name);
        et_l_name = (EditText) findViewById(R.id.et_name);
        et_l_name = (EditText) findViewById(R.id.et_name);
        et_l_name = (EditText) findViewById(R.id.et_name);
        et_l_name = (EditText) findViewById(R.id.et_name);
        et_l_name = (EditText) findViewById(R.id.et_name);
        et_l_surname = (EditText) findViewById(R.id.et_surname);
        et_l_id = (EditText) findViewById(R.id.et_id_number);
        et_l_birth_date = (DatePickerView) findViewById(R.id.et_birth_date);
        et_l_enroll_date = (DatePickerView) findViewById(R.id.et_enroll_date);
        et_l_address = (EditText) findViewById(R.id.et_address);
        sp_class = (Spinner) findViewById(R.id.sp_class_name);
        sp_gender = (Spinner) findViewById(R.id.sp_gender);
        sp_race = (Spinner) findViewById(R.id.sp_race);
        sp_type = (Spinner) findViewById(R.id.sp_type);
        sp_language = (Spinner) findViewById(R.id.sp_language);
        learner = new Learner();
        btn_next = (Button) findViewById(R.id.btn_save_learner);
    }
    public void btnSaveLearner(View v) {
        if (ReuseClass.isConnectionAvailable(getApplicationContext())) {

            if (et_l_name.getText().toString().isEmpty() || et_l_surname.getText().toString().isEmpty()
                    || et_l_address.getText().toString().isEmpty() || et_l_id.getText().toString().isEmpty()
                    || et_l_birth_date.getText().toString().isEmpty() || et_l_enroll_date.getText().toString().isEmpty()
                    || sp_class.getSelectedItem().toString().isEmpty() || sp_gender.getSelectedItem().toString().isEmpty()
                    || sp_race.getSelectedItem().toString().isEmpty() || sp_type.getSelectedItem().toString().isEmpty() ||
                    sp_language.getSelectedItem().toString().isEmpty() || et_medical_number.getText().toString().isEmpty() ||
                    et_medical_plan.getText().toString().isEmpty() || et_doctor_contact.getText().toString().isEmpty() || et_mother_contact.getText().toString().isEmpty()
                    || et_father_contact.getText().toString().isEmpty()) {
                ReuseClass.setSupportSnackBar(getApplicationContext(), linearAdd,"Please enter all values!!!", Snackbar.LENGTH_LONG).show();
            } else {
                learner.setName(et_l_name.getText().toString());
                learner.setSurname(et_l_surname.getText().toString());
                learner.setIdNumber(et_l_id.getText().toString());
                learner.setBirthdate(et_l_birth_date.getText().toString());
                learner.setAddress(et_l_address.getText().toString());
                learner.setDateEnrolled(et_l_enroll_date.getText().toString());
                learner.setLanguageInstruction(sp_language.getSelectedItem().toString());
                learner.setRace(sp_race.getSelectedItem().toString());
                learner.setClassName(sp_class.getSelectedItem().toString());
                learner.setGender(sp_gender.getSelectedItem().toString());
                learner.setType_student(sp_type.getSelectedItem().toString());
                learner.setMotherName(et_mother_name.getText().toString());
                learner.setFatherName(et_father_name.getText().toString());
                learner.setMotherEmailAddress(et_mother_email.getText().toString());
                learner.setFatherEmailAddress(et_father_email.getText().toString());
                learner.setMotherContactNumber(et_mother_contact.getText().toString());
                learner.setFatherContactNumber(et_father_contact.getText().toString());
                learner.setDoctorName(et_doctor_name.getText().toString());
                learner.setDoctorContactNumber(et_doctor_contact.getText().toString());
                learner.setMedicalAidName(sp_medical_name.getSelectedItem().toString());
                learner.setMedicalAidPlan(et_medical_plan.getText().toString());
                learner.setMedicalAidNumber(et_medical_number.getText().toString());
                learner.setTuckBalance(et_learner_balance.getText().toString());
                learner.setAlergiesOfLearner(et_learner_allergies.getText().toString());
                learner.setUpdated(new Date());
                progressDialog = new SpotsDialog(AddLearner.this, R.style.SpotsDialogCustom);
                progressDialog.show();
                Backendless.Persistence.save(learner, new AsyncCallback<Learner>() {
                    @Override
                    public void handleResponse(Learner learner) {
                        progressDialog.dismiss();
                        ReuseClass.setSupportSnackBar(getApplicationContext(), linearAdd,"Learner Added", Snackbar.LENGTH_LONG).show();
                        AddLearner.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        ReuseClass.setSupportSnackBar(getApplicationContext(), linearAdd,"Error: " + backendlessFault.getMessage(), Snackbar.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }
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

    @Override
    public void onBackPressed() {
    }
}
