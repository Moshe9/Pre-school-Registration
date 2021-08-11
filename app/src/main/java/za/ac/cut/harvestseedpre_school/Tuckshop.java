package za.ac.cut.harvestseedpre_school;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Tuckshop extends AppCompatActivity {

    ListView lst_tuckshop;
    ArrayList<Learner> learnerList;
    AdapterTuckshop adapter;
    EditText inputSearch;
    DilatingDotsProgressBar progress;
    Learner learner;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuckshop);
        ReuseClass.setSupportActionBarView(this,"HS Tuck shop").setIcon(R.mipmap.ic_launcher);

        lst_tuckshop = (ListView) findViewById(R.id.lst_tuckshop);
        inputSearch = (EditText) findViewById(R.id.inputSearchTuckShop);
        progress = (DilatingDotsProgressBar) findViewById(R.id.progressBar);
        learnerList = new ArrayList<>();
        adapter = new AdapterTuckshop(learnerList, getApplicationContext());
        getAllLearners();
        searchLearner();
        OnClick();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Tuckshop.this.finish();
        return true;
    }
    private void OnClick() {
        lst_tuckshop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(parent);
                pos = position;
                return false;
            }
        });
        lst_tuckshop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Tuckshop.this, LearnerPurchase.class));
                Intent intent1 = new Intent(Tuckshop.this, LearnerPurchase.class);
                intent1.putExtra("name", learnerList.get(position).getName());
                intent1.putExtra("surname", learnerList.get(position).getSurname());
                intent1.putExtra("className", learnerList.get(position).getClassName());
                intent1.putExtra("tuckBalance", String.valueOf(learnerList.get(position).getTuckBalance()));
                intent1.putExtra("objectId", learnerList.get(position).getObjectId());
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tuck_menu_items, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.mnu_pdf_options:
//                Intent intent = new Intent(Tuckshop.this, CreateViewPDF.class);
//                intent.putExtra("name", learnerList.get(pos).getName());
//                intent.putExtra("surname", learnerList.get(pos).getSurname());
//                intent.putExtra("className", learnerList.get(pos).getClassName());
//                intent.putExtra("tuckBalance", String.valueOf(learnerList.get(pos).getTuckBalance()));
//                intent.putExtra("objectId", learnerList.get(pos).getObjectId());
//                startActivity(intent);
//                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Tuckshop.this.finish();
    }

    public void searchLearner() {
        lst_tuckshop.setTextFilterEnabled(true);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                int textlength = cs.length();
                ArrayList<Learner> tempArrayList = new ArrayList<Learner>();
                for(Learner c: learnerList){
                    if (textlength <= c.getName().length()) {
                        if (c.getName().toLowerCase().contains(cs.toString().toLowerCase())) {
                            tempArrayList.add(c);
                        }
                    }
                }
                adapter = new AdapterTuckshop(tempArrayList, getApplicationContext());
                lst_tuckshop.setAdapter(adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    public void getAllLearners() {
        progress.showNow();
        if (learnerList != null) {
            learnerList.clear();
        }

        Backendless.Persistence.of(Learner.class).find(new AsyncCallback<List<Learner>>() {
            @Override
            public void handleResponse(List<Learner> learners) {
                for (int x = 0; x < learners.size(); x++) {
                    learner = new Learner(learners.get(x).getName(), learners.get(x).getSurname(),
                            learners.get(x).getClassName(), learners.get(x).getGender(),
                            learners.get(x).getRace(), learners.get(x).getBirthdate(),
                            learners.get(x).getLanguageInstruction(), learners.get(x).getIdNumber(),
                            learners.get(x).getDateEnrolled(), learners.get(x).getAddress(),
                            learners.get(x).getType_student(), learners.get(x).getMotherName(),
                            learners.get(x).getFatherName(), learners.get(x).getMotherEmailAddress(),
                            learners.get(x).getFatherEmailAddress(), learners.get(x).getMotherContactNumber(),
                            learners.get(x).getFatherContactNumber(), learners.get(x).getDoctorName(),
                            learners.get(x).getDoctorContactNumber(), learners.get(x).getMedicalAidName(),
                            learners.get(x).getMedicalAidPlan(), learners.get(x).getMedicalAidNumber(),
                            learners.get(x).getAlergiesOfLearner(), learners.get(x).getTuckBalance(),
                            learners.get(x).getObjectId(), learners.get(x).getCreated(), learners.get(x).getUpdated());


                    learnerList.add(learner);
                    if (learner != null) {
                        lst_tuckshop.setAdapter(adapter);
                        progress.hideNow();
                    }


                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(Tuckshop.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                progress.hideNow();
            }
        });
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
}
