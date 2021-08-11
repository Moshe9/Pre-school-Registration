package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_gotoRegister, btn_gotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReuseClass.setSupportActionBarView(this, "Harvest Seed Pre-School").setIcon(R.mipmap.ic_launcher);
        btn_gotoLogin = (Button) findViewById(R.id.btn_gotoLogin);
        btn_gotoRegister = (Button) findViewById(R.id.btn_gotoRegister);

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gotoLogin:
                startActivity(new Intent(MainActivity.this, LoginPage.class));
                this.finish();
                break;
            case R.id.btn_gotoRegister:
                startActivity(new Intent(MainActivity.this, RegisterPage.class));
                this.finish();
                break;
            case R.id.btn_gotoIce: {
                startActivity(new Intent(MainActivity.this, ICE.class));
            }

        }
    }
}
