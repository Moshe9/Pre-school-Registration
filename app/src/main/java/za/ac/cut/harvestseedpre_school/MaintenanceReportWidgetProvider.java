package za.ac.cut.harvestseedpre_school;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceReportWidgetProvider extends AppWidgetProvider {

    ArrayList<ReportMaintenance> mList;
    ReportMaintenance lr;
    String issue, title, date;

    @Override
    public void onUpdate(final Context context,final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        for (final int appWidgetId : appWidgetIds) {
            mList = new ArrayList<>();
            Backendless.Persistence.of(ReportMaintenance.class).find(new AsyncCallback<List<ReportMaintenance>>() {
                @Override
                public void handleResponse(List<ReportMaintenance> reportMaintenances) {
                    for (int x = 0; x < reportMaintenances.size(); x++) {
                        lr = new ReportMaintenance(reportMaintenances.get(x).getReportTitle(),
                                reportMaintenances.get(x).getReportType(),
                                reportMaintenances.get(x).getReportInformation(),
                                reportMaintenances.get(x).getInserDate(), reportMaintenances.get(x).getStatus());

                        mList.add(lr);
//                        issue ="Issue Resolved: "+ mList.get(x).getStatus().toString();
                        title = "Report Title: " +mList.get(x).getReportTitle().toString();
//                        date = "Date Added: " +mList.get(x).getInserDate().toString();
                    }

                    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.maintenance_main_widget);
                    views.setTextViewText(R.id.m_report_issue, issue);
                    views.setTextViewText(R.id.m_report_title, title);
                    views.setTextViewText(R.id.m_report_title, date);

                    Intent intent = new Intent(context, MaintenanceReportWidgetProvider.class);
                    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
                    appWidgetManager.updateAppWidget(appWidgetId, views);
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                }
            });
        }

    }
}
