package za.ac.cut.harvestseedpre_school;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterLearnerReports extends ArrayAdapter<LearnerReport> {

    private final Context context;
    private final ArrayList<LearnerReport> learnerList;
    TextView tv_learnerName, tv_title;

    public AdapterLearnerReports(ArrayList<LearnerReport> learnerList, Context context) {
        super(context, R.layout.learner_report_list_item, learnerList);
        this.context = context;
        this.learnerList = learnerList;
    }


    @Override
    public int getCount() {
        return learnerList == null ? 0 : learnerList.size();
    }

    @Nullable
    @Override
    public LearnerReport getItem(int position) {
        return learnerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.learner_report_list_item, parent, false);


        tv_learnerName = (TextView) convertView.findViewById(R.id.tv_learner_in_report);
        tv_title = (TextView) convertView.findViewById(R.id.tv_report_title);

        tv_learnerName.setText("Learner Name: " + learnerList.get(position).getLearnerName());
        tv_title.setText("Report Type: " + learnerList.get(position).getReportTitle());
        return convertView;
    }
}
