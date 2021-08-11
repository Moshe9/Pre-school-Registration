package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class ICE extends AppCompatActivity implements View.OnClickListener {


    LinearLayout cv_police, cv_ambulance, cv_lifeline, cv_childline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice);
        ReuseClass.setSupportActionBarView(this,"In case of emergency Details").setIcon(R.mipmap.ic_launcher);
        cv_police = (LinearLayout)findViewById(R.id.iv_call_police);
        cv_ambulance = (LinearLayout)findViewById(R.id.iv_call_ambulance);
        cv_lifeline = (LinearLayout)findViewById(R.id.iv_call_traffic);
        cv_childline = (LinearLayout)findViewById(R.id.iv_call_child_services);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_call_ambulance: {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "10177"));
                startActivity(intent);
            }
            break;
            case R.id.iv_call_child_services: {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0800055555"));
                startActivity(intent);
            }
            break;
            case R.id.iv_call_traffic: {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0861322322"));
                startActivity(intent);
            }
            break;
            case R.id.iv_call_police: {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "10111"));
                startActivity(intent);
            }
            break;
        }
    }
    @Override
    public void onBackPressed() {

    }
}
