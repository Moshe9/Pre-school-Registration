package za.ac.cut.harvestseedpre_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class LearnerClasses extends AppCompatActivity implements View.OnClickListener {

    ImageView a,b,c,d,e,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_classes);
        ReuseClass.setSupportActionBarView(this,"Classes").setIcon(R.mipmap.ic_launcher);
        a = (ImageView) findViewById(R.id.classa);
        b = (ImageView) findViewById(R.id.classb);
        c = (ImageView) findViewById(R.id.classc);
        d = (ImageView) findViewById(R.id.classd);
        e = (ImageView) findViewById(R.id.classe);
        f = (ImageView) findViewById(R.id.classf);


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
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classa:
                startActivity(new Intent(LearnerClasses.this, ClassA.class));
                break;
            case R.id.classb:
                startActivity(new Intent(LearnerClasses.this, ClassB.class));
                break;
            case R.id.classc:
                startActivity(new Intent(LearnerClasses.this, ClassC.class));
                break;
            case R.id.classd:
                startActivity(new Intent(LearnerClasses.this, ClassD.class));
                break;
            case R.id.classe:
                startActivity(new Intent(LearnerClasses.this, ClassE.class));
                break;
            case R.id.classf:
                startActivity(new Intent(LearnerClasses.this, ClassF.class));
                break;
        }
    }
}
