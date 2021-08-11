package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.net.MalformedURLException;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class ProfileCheck extends AppCompatActivity implements View.OnClickListener {


    EditText user_name, user_surname, user_email, user_phone;
    Button btn_save, btn_cancel;
    ImageView iv_profile;
    SpotsDialog progressDialog;
    private BackendlessUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_check);
        ReuseClass.setSupportActionBarView(this,"Update Profile").setIcon(R.mipmap.ic_launcher);
        user_name = (EditText) findViewById(R.id.etName);
        user_surname = (EditText) findViewById(R.id.etSurname);
        user_email = (EditText) findViewById(R.id.etEmail);
        user_phone = (EditText) findViewById(R.id.etCellPhone);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        user = Backendless.UserService.CurrentUser();
        iv_profile = (ImageView) findViewById(R.id.ivProfileImage);

        user_name.setEnabled(false);
        user_surname.setEnabled(false);
        user_email.setEnabled(false);
        user_phone.setEnabled(false);
        btn_save.setEnabled(false);
        displayProfilePicture();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_check_menu_items, menu);
        return super.onCreateOptionsMenu(menu);



    }

    public void displayProfilePicture() {

        if (user != null) {
            try {
                String pictureLocation = user.getProperty("email") + "_.png";
                URL url = new URL("https://api.backendless.com/865099D5-01B3-B407-FFC6-A42EE3799F00/A6A83E0F-F8A5-1BF1-FFA1-0AE892CA0000/files/ImageProfiles/" + pictureLocation);
                DownloadFiles pictureDownload = new DownloadFiles(iv_profile);
                pictureDownload.execute(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (user != null) {
            user_name.setText((String) user.getProperty("name"));
            user_surname.setText((String) user.getProperty("Surname"));
            user_email.setText(user.getEmail());
            user_phone.setText((String) user.getProperty("phone"));
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_u_cancel:
                this.finish();
                break;
            case R.id.mnu_update:
                user_name.setEnabled(true);
                user_surname.setEnabled(true);
                user_phone.setEnabled(true);
                user_email.setEnabled(true);
                btn_save.setEnabled(true);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (user != null) {
                    user.setProperty("name", user_name.getText().toString());
                    user.setProperty("surname", user_surname.getText().toString());
                    user.setProperty("phone", user_phone.getText().toString());
                    user.setProperty("email", user_email.getText().toString());
                    progressDialog = new SpotsDialog(ProfileCheck.this, R.style.SpotsDialogCustom);
                    progressDialog.show();
                    Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser backendlessUser) {
                            Intent intent = new Intent(ProfileCheck.this, HomePage.class);
                            startActivity(intent);
                            ProfileCheck.this.finish();
                            progressDialog.dismiss();
                            Toast.makeText(ProfileCheck.this, "Profile Successfully Updated", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            Toast.makeText(ProfileCheck.this, "Error" + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btn_cancel:
                this.finish();
                break;
        }
    }
}
