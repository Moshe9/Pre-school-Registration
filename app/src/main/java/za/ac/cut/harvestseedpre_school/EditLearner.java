package za.ac.cut.harvestseedpre_school;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Date;

import dmax.dialog.SpotsDialog;

public class EditLearner extends AppCompatActivity {
    EditText et_l_name, et_l_surname, et_l_address,
            et_l_id,
            et_mother_name, et_father_name, et_mother_email, et_mother_contact,
            et_father_email, et_father_contact, et_doctor_name,
            et_doctor_contact, et_medical_plan, et_learner_allergies,
            et_learner_balance, et_medical_number;
    String et_name, et_surname, et_address,
            et_id,
            et_mother_name_l, et_father_name_l, et_mother_email_l, et_mother_contact_l,
            et_father_email_l, et_father_contact_l, et_doctor_name_l,
            et_doctor_contact_l, et_medical_plan_l, et_learner_allergies_l,
            et_learner_balance_l, et_medical_number_l, sp_class_l, sp_race_l, sp_type_l, sp_language_l,
            sp_medical_name_l, sp_gender_l, et_l_birth_date_l, et_l_enroll_date_l;

    Spinner sp_class, sp_gender, sp_race, sp_type, sp_language, sp_medical_name;
    DatePickerView et_l_birth_date, et_l_enroll_date;
    AlertDialog progressDialog;
    String objectId;
    Learner learner;
    Button btn_save_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_learner);
        ReuseClass.setSupportActionBarView(this, "Update Learner Details").setIcon(R.mipmap.ic_launcher);
        btn_save_edit = (Button) findViewById(R.id.btn_save_edit);
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
        retrieveInfoBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void retrieveInfoBack() {

        objectId = getIntent().getExtras().getString("objectId");
        et_name = getIntent().getExtras().getString("name");
        et_surname = getIntent().getExtras().getString("surname");
        sp_class_l = getIntent().getExtras().getString("className");
        et_id = getIntent().getExtras().getString("idNumber");
        sp_gender_l = getIntent().getExtras().getString("gender");
        sp_race_l = getIntent().getExtras().getString("race");
        et_l_birth_date_l = getIntent().getExtras().getString("birthdate");
        sp_language_l = getIntent().getExtras().getString("languageInstruction");
        sp_type_l = getIntent().getExtras().getString("type_student");
        et_l_enroll_date_l = getIntent().getExtras().getString("dateEnrolled");
        et_address = getIntent().getExtras().getString("address");
        et_mother_name_l = getIntent().getExtras().getString("motherName");
        et_father_name_l = getIntent().getExtras().getString("fatherName");
        et_mother_email_l = getIntent().getExtras().getString("motherEmailAddress");
        et_father_email_l = getIntent().getExtras().getString("fatherEmailAddress");
        et_mother_contact_l = getIntent().getExtras().getString("motherContactNumber");
        et_father_contact_l = getIntent().getExtras().getString("fatherContactNumber");
        et_doctor_name_l = getIntent().getExtras().getString("doctorName");
        et_doctor_contact_l = getIntent().getExtras().getString("doctorContactNumber");
        sp_medical_name_l = getIntent().getExtras().getString("medicalAidName");
        et_medical_plan_l = getIntent().getExtras().getString("medicalAidPlan");
        et_medical_number_l = getIntent().getExtras().getString("medicalAidNumber");
        et_learner_allergies_l = getIntent().getExtras().getString("alergiesOfLearner");
        et_learner_balance_l = getIntent().getExtras().getString("tuckBalance");

        et_l_name.setText(et_name);
        et_l_surname.setText(et_surname);
//        sp_class.setText(sp_class_l);
//        sp_race.setText(sp_race_l);
//        sp_type.setText(sp_type_l);
//        sp_gender.setText(sp_gender_l);
        et_l_birth_date.setText(et_l_birth_date_l);
//        sp_language.setText(sp_language_l);
        et_l_id.setText(et_id);
        et_l_enroll_date.setText(et_l_enroll_date_l);
        et_l_address.setText(et_address);
        et_mother_name.setText(et_mother_name_l);
        et_father_name.setText(et_father_name_l);
        et_mother_email.setText(et_mother_email_l);
        et_father_email.setText(et_father_email_l);
        et_mother_contact.setText(et_mother_contact_l);
        et_father_contact.setText(et_father_contact_l);
        et_doctor_name.setText(et_doctor_name_l);
        et_doctor_contact.setText(et_doctor_contact_l);
//        sp_medical_name.setText(sp_medical_name_l);
        et_medical_plan.setText(et_medical_plan_l);
        et_medical_number.setText(et_medical_number_l);
        et_learner_allergies.setText(et_learner_allergies_l);
        et_learner_balance.setText(et_learner_balance_l);

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
    public void SaveEdit(View v) {
        if (ReuseClass.isConnectionAvailable(getApplicationContext())) {

            if (et_l_name.getText().toString().isEmpty() || et_l_surname.getText().toString().isEmpty()
                    || et_l_address.getText().toString().isEmpty() || et_l_id.getText().toString().isEmpty()
                    || et_l_birth_date.getText().toString().isEmpty() || et_l_enroll_date.getText().toString().isEmpty()
                    || sp_class.getSelectedItem().toString().isEmpty() || sp_gender.getSelectedItem().toString().isEmpty()
                    || sp_race.getSelectedItem().toString().isEmpty() || sp_type.getSelectedItem().toString().isEmpty() ||
                    sp_language.getSelectedItem().toString().isEmpty() || et_medical_number.getText().toString().isEmpty() ||
                    et_medical_plan.getText().toString().isEmpty() || et_doctor_contact.getText().toString().isEmpty() || et_mother_contact.getText().toString().isEmpty()
                    || et_father_contact.getText().toString().isEmpty()) {
                Toast.makeText(EditLearner.this, "Please enter all values!!!", Toast.LENGTH_SHORT).show();
            } else {
                learner = new Learner();
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
                learner.setObjectId(objectId);
                learner.setUpdated(new Date());
                progressDialog = new SpotsDialog(EditLearner.this, R.style.SpotsDialogCustom);
                progressDialog.show();
                Backendless.Persistence.of(Learner.class).findById(objectId, new AsyncCallback<Learner>() {
                    @Override
                    public void handleResponse(Learner learner) {

                        Backendless.Persistence.of(Learner.class).save(learner, new AsyncCallback<Learner>() {
                            @Override
                            public void handleResponse(Learner learner) {
                                Toast.makeText(EditLearner.this, "Learner Updated", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                EditLearner.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                progressDialog.dismiss();
                            }
                        });


                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        progressDialog.dismiss();

                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {

    }
}
