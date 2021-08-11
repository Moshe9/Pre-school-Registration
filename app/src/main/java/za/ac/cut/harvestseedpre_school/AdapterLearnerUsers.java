package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AdapterLearnerUsers extends ArrayAdapter<Learner> {

    private final Context context;
    private final List<Learner> values;
    TextView tvUsername, className;

    public AdapterLearnerUsers(Context context, List<Learner> list) {
        super(context, R.layout.learner_list_item, list);
        this.context = context;
        this.values = list;
    }


    @Override
    public int getCount() {
        return values == null ? 0 : values.size();
    }

    @Nullable
    @Override
    public Learner getItem(int position) {
        return values.get(position);
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
            convertView = inflater.inflate(R.layout.learner_list_item, parent, false);
            tvUsername = (TextView) convertView.findViewById(R.id.tv_learner);
            className = (TextView) convertView.findViewById(R.id.tv_class_room);

        }
        tvUsername.setText("Learner Name: " + values.get(position).getName() + " " + values.get(position).getSurname());
        className.setText("Class Name:"+ values.get(position).getClassName());
        return convertView;
    }
}
