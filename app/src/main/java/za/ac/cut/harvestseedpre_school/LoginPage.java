package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import dmax.dialog.SpotsDialog;
import za.ac.cut.harvestseedpre_school.MaterialDialogs.MaterialStyledDialog;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button btn_login, btn_resetPassword, button;
    EditText et_username, et_password;
    LinearLayout login_layout;
    SpotsDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ReuseClass.setSupportActionBarView(this, "Login").setIcon(R.mipmap.ic_launcher);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_resetPassword = (Button) findViewById(R.id.btn_resetPassword);
        button = (Button) findViewById(R.id.button2);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        login_layout = (LinearLayout) findViewById(R.id.login_layout);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (username.equals("") || password.equals("")) {

                    ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, "Please enter all fields!!", Snackbar.LENGTH_LONG).show();

                } else {

                    if (ReuseClass.isConnectionAvailable(getApplicationContext())) {

                        progressDialog = new SpotsDialog(LoginPage.this, R.style.SpotsDialogCustom);
                        progressDialog.show();

                        Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser backendlessUser) {

                                ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, backendlessUser.getEmail() + " successfully logged in!", Snackbar.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, HomePage.class);
                                startActivity(intent);
                                LoginPage.this.finish();
                                progressDialog.dismiss();

                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, backendlessFault.getMessage(), Snackbar.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, true);

                    } else {
                        ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, "Please check your internet connection", Snackbar.LENGTH_LONG).show();
                    }

                }
                break;
            case R.id.btn_resetPassword:

                if (ReuseClass.isConnectionAvailable(getApplicationContext())) {


                    Context context = this;
                    LayoutInflater inflater = getLayoutInflater();
                    View customView = inflater.inflate(R.layout.password_reset_dialog, null);
                    final EditText et_reset;
                    et_reset = (EditText) customView.findViewById(R.id.et_resetPassword);
                    final MaterialStyledDialog.Builder share_dialog = new MaterialStyledDialog.Builder(context)
                            .setIcon(R.mipmap.ic_launcher)
                            .withDialogAnimation(true)
                            .setDescription("Reset your password")
                            .setHeaderColor(R.color.colorPrimary)
                            .setCustomView(customView, 20, 20, 20, 0)
                            .setPositiveText("Reset")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    LoginPage.this.progressDialog = new SpotsDialog(LoginPage.this, R.style.SpotsDialogCustom);
                                    LoginPage.this.progressDialog.setTitle("Busy sending reset instructions...Please wait");
                                    LoginPage.this.progressDialog.show();

                                    Backendless.UserService.restorePassword(et_reset.getText().toString().trim(), new AsyncCallback<Void>() {
                                        @Override
                                        public void handleResponse(Void aVoid) {
                                            ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, "Reset Instructions has been sent to " + et_reset.getText().toString().trim(), Snackbar.LENGTH_LONG).show();
                                            LoginPage.this.progressDialog.dismiss();
                                        }

                                        @Override
                                        public void handleFault(BackendlessFault backendlessFault) {

                                            ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, backendlessFault.getMessage(), Snackbar.LENGTH_LONG).show();
                                            LoginPage.this.progressDialog.dismiss();

                                        }
                                    });
                                }
                            })
                            .setNegativeText("Cancel")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.cancel();
                                }
                            });
                    share_dialog.show();

                } else {
                    ReuseClass.setSupportSnackBar(getApplicationContext(), login_layout, "Please connect to the internet", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.button2:
                startActivity(new Intent(LoginPage.this, RegisterPage.class));
                this.finish();
                break;

        }
    }
}
