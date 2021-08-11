package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.w3c.dom.Text;

import dmax.dialog.SpotsDialog;
import za.ac.cut.harvestseedpre_school.MaterialDialogs.MaterialStyledDialog;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_tuck_shop, iv_reports, iv_classes, iv_admin_master;
    private Context context;
    BackendlessUser backendlessUser;
    TextView userLoggedIn;
    SpotsDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ReuseClass.setSupportActionBarView(this,"Welcome!!").setIcon(R.mipmap.ic_launcher);
        context = this;
        iv_tuck_shop = (ImageView) findViewById(R.id.btn_tuck_shop);
        iv_reports = (ImageView) findViewById(R.id.btn_reports);
        iv_classes = (ImageView) findViewById(R.id.btn_classes);
        iv_admin_master = (ImageView) findViewById(R.id.btn_admin_master);
        backendlessUser = Backendless.UserService.CurrentUser();
        userLoggedIn = (TextView) findViewById(R.id.userLoggedIn);

        if (backendlessUser != null) {
            userLoggedIn.setText("Logged in as: "+ backendlessUser.getProperty("name")
                    + " " + backendlessUser.getProperty("Surname"));
        }
        // Allocate permissions for user roles
        if (backendlessUser != null) {
            if (backendlessUser.getProperty("role").equals("Default") || backendlessUser.getProperty("role").equals("None")) {
                iv_admin_master.setVisibility(View.INVISIBLE);
            }else {
                iv_admin_master.setVisibility(View.VISIBLE);
            }
            if (backendlessUser.getProperty("role").equals("Master") || backendlessUser.getProperty("role").equals("Admin")) {
                iv_tuck_shop.setVisibility(View.INVISIBLE);
            } else {
                iv_tuck_shop.setVisibility(View.VISIBLE);
            }
            if (backendlessUser.getProperty("role").equals("None")) {
                final MaterialStyledDialog.Builder dialogHeader_Main1 = new MaterialStyledDialog.Builder(context)
                        .setIcon(R.mipmap.ic_launcher)
                        .withDialogAnimation(true)
                        .setTitle("Contact Admin to assign role for you")
                        .setDescription("To continue using this application, Please contact principle to assign role for you")
                        .setHeaderColor(R.color.colorPrimary)
                        .setCancelable(false)
                        .setPositiveText("Dismiss Application")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                progressDialog = new SpotsDialog(HomePage.this, R.style.SpotsDialogCustom);
                                progressDialog.show();
                                Backendless.UserService.logout(new AsyncCallback<Void>() {
                                    @Override
                                    public void handleResponse(Void aVoid) {
                                        Backendless.UserService.update(backendlessUser, new AsyncCallback<BackendlessUser>() {
                                            @Override
                                            public void handleResponse(BackendlessUser backendlessUser) {
                                            }

                                            @Override
                                            public void handleFault(BackendlessFault backendlessFault) {

                                            }
                                        });
                                        progressDialog.dismiss();
                                        HomePage.this.finish();
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault backendlessFault) {
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });

                dialogHeader_Main1.show();
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_logout:
                progressDialog = new SpotsDialog(HomePage.this, R.style.SpotsDialogCustom);
                progressDialog.show();
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void aVoid) {
                        Backendless.UserService.update(backendlessUser, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser backendlessUser) {
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {

                            }
                        });
                        progressDialog.dismiss();
                        HomePage.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.mnu_profile:
                startActivity(new Intent(HomePage.this, ProfileCheck.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tuck_shop:
                startActivity(new Intent(HomePage.this, Tuckshop.class));
                break;
            case R.id.btn_classes:
                startActivity(new Intent(HomePage.this, LearnerClasses.class));
                break;
            case R.id.btn_reports:
                startActivity(new Intent(HomePage.this, Reports.class));
                break;
            case R.id.btn_admin_master:
                startActivity(new Intent(HomePage.this, MasterAdmin.class));
                break;
        }
    }
    @Override
    public void onBackPressed() {
    }
}
