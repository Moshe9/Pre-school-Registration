package za.ac.cut.harvestseedpre_school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import dmax.dialog.SpotsDialog;

public class LearnerPurchase extends AppCompatActivity implements View.OnClickListener {

    TextView currentTransaction,afterTransaction,currentBalance;
    EditText et_total;
    String name_learner,surname_learner,tuck_balance,className,objectId;
    Button btn1,btn2,btn3,btn4,btn5,btn6 ;
    double total,currentTr;
    String currentT,afterT;
    SpotsDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_purchase);

        ReuseClass.setSupportActionBarView(this,"Learner Purchase ").setIcon(R.mipmap.ic_launcher);
        btn1 = (Button) findViewById(R.id.tv_tencent);
        btn2 = (Button) findViewById(R.id.tv_twentycent);
        btn3 = (Button) findViewById(R.id.tv_fiftycent);
        btn4 = (Button) findViewById(R.id.tv_onerand);
        btn5 = (Button) findViewById(R.id.tv_tworand);
        btn6 = (Button) findViewById(R.id.tv_fiferand);
        et_total = (EditText) findViewById(R.id.et_total);
        currentBalance= (TextView) findViewById(R.id.current_balance);
        currentTransaction=(TextView) findViewById(R.id.current_transaction);
        afterTransaction=(TextView) findViewById(R.id.after_transaction);
        getData();

    }
    public void getData() {
        name_learner = getIntent().getExtras().getString("name");
        surname_learner = getIntent().getExtras().getString("surname");
        tuck_balance = getIntent().getExtras().getString("tuckBalance");
        className = getIntent().getExtras().getString("className");
        objectId= getIntent().getExtras().getString("objectId");
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
    public void performPurchase(double amount) {
        total+=amount;
        currentTr+=amount;
        double d= Double.parseDouble(tuck_balance);
        double totalafter = d-=currentTr;
        String totAmount= String.format("%.2f", total);
        currentT = String.format("%.2f", currentTr);
        afterT = String.format("%.2f", totalafter);
        et_total.setText("R"+totAmount);
        currentBalance.setText("Current Balance: "+ "R"+tuck_balance);
        currentTransaction.setText("Current Transaction: "+ "R"+currentT);
        afterTransaction.setText("After Transaction: "+ "R"+afterT);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_tencent:
                performPurchase(0.10);
                break;
            case R.id.tv_twentycent:
                performPurchase(0.20);
                break;
            case R.id.tv_fiftycent:
                performPurchase(0.50);
                break;
            case R.id.tv_onerand:
                performPurchase(1.00);
                break;
            case R.id.tv_tworand:
                performPurchase(2.00);
                break;
            case R.id.tv_fiferand:
                performPurchase(5.00);
                break;
            case R.id.btn_submit_transaction:
                Learner learner = new Learner();

                learner.setTuckBalance((currentT));
                progressDialog = new SpotsDialog(LearnerPurchase.this, R.style.SpotsDialogCustom);
                progressDialog.show();
                Backendless.Persistence.of(Learner.class).findById(objectId, new AsyncCallback<Learner>() {
                    @Override
                    public void handleResponse(Learner learner) {

                        Backendless.Persistence.of(Learner.class).save(learner, new AsyncCallback<Learner>() {
                            @Override
                            public void handleResponse(Learner learner) {
                                Toast.makeText(LearnerPurchase.this, "Learner Balance Updated", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                progressDialog.dismiss();
                            }
                        });


                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        progressDialog.dismiss();

                    }
                });
                break;
            case R.id.btn_reset_transaction:
                String cleartext = "R0.00";
                et_total.setText(cleartext);
                currentTransaction.setText("");
                afterTransaction.setText("");
                break;

        }

    }
}
