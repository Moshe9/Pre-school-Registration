package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.BackendlessUser;

import java.util.List;

public class AdapterUsers extends ArrayAdapter<BackendlessUser> {

    private final Context context;
    private final List<BackendlessUser> values;
    ImageView imageView;

    public AdapterUsers(Context context, List<BackendlessUser> list) {

        super(context, R.layout.activity_user_list, list);
        this.context = context;
        this.values = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_user_list, parent, false);

        TextView tvMateName = (TextView) rowView.findViewById(R.id.user_name);
        TextView tvMateRole = (TextView) rowView.findViewById(R.id.user_role);
        imageView = (ImageView) rowView.findViewById(R.id.item_profile_picture);

        tvMateName.setText(values.get(position).getProperty("name")
                + " " + values.get(position).getProperty("Surname"));
        tvMateRole.setText((String) values.get(position).getProperty("role"));


        return rowView;
    }
}
