package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import za.ac.cut.harvestseedpre_school.MaterialDialogs.MaterialStyledDialog;

public class MasterAdmin extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_tuck, iv_maint, iv_learner, iv_roles;
    BackendlessUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_admin);
        ReuseClass.setSupportActionBarView(this, "Admin/Master").setIcon(R.mipmap.ic_launcher);
        iv_tuck = (ImageView) findViewById(R.id.iv_tuckshop_master);
        iv_maint = (ImageView) findViewById(R.id.iv_maintenance_master);
        iv_learner = (ImageView) findViewById(R.id.iv_learner_master);
        iv_roles = (ImageView) findViewById(R.id.iv_set_roles);
        user = Backendless.UserService.CurrentUser();
        roles();
    }

    private void roles() {
        if (user != null) {
            if (user.getProperty("role").equals("Admin") ) {
                iv_roles.setVisibility(View.INVISIBLE);
                iv_learner.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_master_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_learner_select:
                CallInflater();
                break;
            case R.id.mn_cancel_admin:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CallInflater() {
        Context context = this;
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.inflate_learner_selection, null);
        ImageView addLearner = (ImageView) customView.findViewById(R.id.add_learner);
        ImageView editLearner = (ImageView) customView.findViewById(R.id.edit_learner);
        ImageView deleteLearner = (ImageView) customView.findViewById(R.id.delete_learner);
        final MaterialStyledDialog share_dialog = new MaterialStyledDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .withDialogAnimation(true)
                .setDescription("Learner Admin (Add, Edit, Delete)")
                .setHeaderColor(R.color.colorPrimary)
                .setCustomView(customView, 20, 20, 20, 0)
                .setPositiveText("Dismiss")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                }).build();
        share_dialog.show();
        addLearner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasterAdmin.this, AddLearner.class));
                share_dialog.dismiss();
            }
        });
        editLearner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasterAdmin.this, SelectLearnerEdit.class));
                share_dialog.dismiss();
            }
        });
        deleteLearner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasterAdmin.this, DeleteLearner.class));
                share_dialog.dismiss();
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_set_roles:
                startActivity(new Intent(MasterAdmin.this, RoleUsers.class));
                break;
            case R.id.iv_tuckshop_master:
                startActivity(new Intent(MasterAdmin.this, Tuckshop.class));
                break;
        }
    }
}
