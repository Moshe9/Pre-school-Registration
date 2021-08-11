package za.ac.cut.harvestseedpre_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class LearnerInformation extends AppCompatActivity {


    TextView et_l_name,
            et_l_id, et_l_birth_date,tv_class_type, tv_enroll_date,tv_type, tv_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_information);
        ReuseClass.setSupportActionBarView(this,"Learner Information").setIcon(R.mipmap.ic_launcher);
        et_l_name = (TextView) findViewById(R.id.tv_l_name_info);
        et_l_id = (TextView) findViewById(R.id.tv_id_info);
        et_l_birth_date = (TextView) findViewById(R.id.tv_birth_date_info);
        tv_class_type = (TextView) findViewById(R.id.tv_class_info);
        tv_enroll_date = (TextView) findViewById(R.id.tv_date_added_info);
        tv_language = (TextView) findViewById(R.id.tv_language_info);
        tv_type = (TextView) findViewById(R.id.tv_type_info);

        et_l_name.setText(getIntent().getStringExtra("name"));
        tv_class_type.setText("Class Name: " + getIntent().getStringExtra("className"));
        tv_language.setText("Language: " + getIntent().getStringExtra("languageInstruction"));
        tv_type.setText("Student Type: " + getIntent().getStringExtra("type_student"));
        et_l_birth_date.setText("Birth Date: " + getIntent().getStringExtra("birthdate"));
        tv_enroll_date.setText("Date Enrolled: " + getIntent().getStringExtra("dateEnrolled"));
        et_l_id.setText("Id Number: " + getIntent().getStringExtra("idNumber"));
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
}
