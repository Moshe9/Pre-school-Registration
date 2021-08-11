package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

public class Splash extends AppCompatActivity {
    DilatingDotsProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        progressBar.showNow();
        final Thread thread = new Thread() {
            public void run() {

                try {
                    sleep(2000);

                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    Splash.this.finish();
                }

            }
        };
        thread.start();
    }
}
