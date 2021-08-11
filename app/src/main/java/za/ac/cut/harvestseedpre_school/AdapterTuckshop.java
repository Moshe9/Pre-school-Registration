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

public class AdapterTuckshop extends ArrayAdapter<Learner> {

    private final Context context;
    private final ArrayList<Learner> learnerList;
    TextView tv_learner, tv_balance,tv_class;

    public AdapterTuckshop(ArrayList<Learner> learnerList, Context context) {
        super(context, R.layout.tuckshop_list_item, learnerList);
        this.context = context;
        this.learnerList = learnerList;
    }

    @Override
    public int getCount() {
        return  learnerList == null ? 0 : learnerList.size();
    }

    @Nullable
    @Override
    public Learner getItem(int position) {
        return learnerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.tuckshop_list_item, parent, false);


            tv_learner = (TextView) rowView.findViewById(R.id.tuck_learner_name);
            tv_balance = (TextView) rowView.findViewById(R.id.tuck_balance);
            tv_class = (TextView) rowView.findViewById(R.id.tuck_class);
        }

        tv_learner.setText(learnerList.get(position).getName() + " " + learnerList.get(position).getSurname());
        tv_balance.setText("Balance: \n" +"R" +String.valueOf(learnerList.get(position).getTuckBalance()) );
        tv_class.setText(learnerList.get(position).getClassName());
        return rowView;
    }
}
