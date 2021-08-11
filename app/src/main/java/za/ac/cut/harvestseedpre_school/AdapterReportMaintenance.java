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

public class AdapterReportMaintenance extends ArrayAdapter<ReportMaintenance> {

    private final Context context;
    private final ArrayList<ReportMaintenance> reportMaintenances;
    TextView tv_title, tv_status, tv_problem, tv_insert_date, tv_info;

    public AdapterReportMaintenance(ArrayList<ReportMaintenance> reportMaintenances, Context context) {
        super(context, R.layout.maintenance_list_item, reportMaintenances);
        this.context = context;
        this.reportMaintenances = reportMaintenances;


    }
    @Override
    public int getCount() {
        return reportMaintenances == null ? 0 : reportMaintenances.size();
    }

    @Nullable
    @Override
    public ReportMaintenance getItem(int position) {
        return reportMaintenances.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.maintenance_list_item, parent, false);


            tv_title = (TextView) rowView.findViewById(R.id.tv_main_title);
            tv_status = (TextView) rowView.findViewById(R.id.tv_main_status);
            tv_info = (TextView) rowView.findViewById(R.id.tv_main_info);
            tv_problem = (TextView) rowView.findViewById(R.id.tv_main_issue) ;
            tv_insert_date = (TextView) rowView.findViewById(R.id.tv_main_date);
        }

        tv_status.setText("Issue Resolved: " +reportMaintenances.get(position).getStatus());
        tv_title.setText("Report Title: " +reportMaintenances.get(position).getReportTitle());
        tv_problem.setText("Issue: "+reportMaintenances.get(position).getReportType());
        tv_insert_date.setText("Insert Date: "  +reportMaintenances.get(position).getInserDate());
        tv_info.setText("Report: \n"  +reportMaintenances.get(position).getReportInformation());


        return rowView;
    }
}

