package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dmax.dialog.SpotsDialog;
import za.ac.cut.harvestseedpre_school.MaterialDialogs.MaterialStyledDialog;

public class RoleUsers extends AppCompatActivity {


    ListView lst_users;
    Context context = this;
    ArrayList<BackendlessUser> userList;
    AdapterUsers adapter;
    int pos;
    BackendlessUser user;
    SpotsDialog progressDialog;
    DilatingDotsProgressBar progressBar;
    Spinner userRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_users);
        ReuseClass.setSupportActionBarView(this,"User roles").setIcon(R.mipmap.ic_launcher);
        lst_users = (ListView) findViewById(R.id.lst_users);
        progressBar = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        userList = new ArrayList<>();
        user = new BackendlessUser();

        getAllUsers();
        listViewOnClicks();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void getAllUsers() {
        progressBar.showNow();
        Backendless.Data.of(BackendlessUser.class).find(new AsyncCallback<List<BackendlessUser>>() {
            @Override
            public void handleResponse(List<BackendlessUser> backendlessUsers) {
                Iterator<BackendlessUser> backendlessUserIterator = backendlessUsers.iterator();

                while (backendlessUserIterator.hasNext()) {
                    BackendlessUser user;
                    user = backendlessUserIterator.next();
                    userList.add(user);
                    if (userList != null) {
                        adapter = new AdapterUsers(getApplicationContext(), userList);
                        lst_users.setAdapter(adapter);
                        progressBar.hideNow();
                    }

                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                progressBar.hideNow();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    public void listViewOnClicks() {
        lst_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                pos = position;

                if (ReuseClass.isConnectionAvailable(getApplicationContext())) {



                    LayoutInflater inflater = getLayoutInflater();
                    final View viewRow = inflater.inflate(R.layout.role_change, null);
                    userRoles = (Spinner) viewRow.findViewById(R.id.sp_role_change);
                    final MaterialStyledDialog.Builder share_dialog = new MaterialStyledDialog.Builder(RoleUsers.this)
                            .setIcon(R.mipmap.ic_launcher)
                            .withDialogAnimation(true)
                            .setDescription("Change user role")
                            .setHeaderColor(R.color.colorPrimary)
                            .setCustomView(viewRow, 20, 20, 20, 0)
                            .setPositiveText("Change")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    RoleUsers.this.progressDialog = new SpotsDialog(RoleUsers.this, R.style.SpotsDialogCustom);
                                    RoleUsers.this.progressDialog.setTitle("Busy sending reset instructions...Please wait");
                                    RoleUsers.this.progressDialog.show();
                                    user.setProperty("role",userRoles.getSelectedItem().toString());
                                    user.setProperty("objectId",userList.get(pos).getObjectId());
                                    Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                                        @Override
                                        public void handleResponse(BackendlessUser backendlessUser) {
                                            Toast.makeText(RoleUsers.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                            adapter.notifyDataSetChanged();
                                            progressDialog.dismiss();
                                            RoleUsers.this.finish();


                                        }

                                        @Override
                                        public void handleFault(BackendlessFault backendlessFault) {

                                            Toast.makeText(RoleUsers.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
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
                    Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
                }


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
