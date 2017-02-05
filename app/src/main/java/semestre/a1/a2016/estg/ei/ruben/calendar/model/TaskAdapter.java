package semestre.a1.a2016.estg.ei.ruben.calendar.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import semestre.a1.a2016.estg.ei.ruben.calendar.R;

/**
 * Created by Ruben on 05-02-2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> tasks){
        super(context, R.layout.task_item, R.id.textViewName, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view=super.getView(position, convertView,parent);

        TextView textViewName=(TextView) view.findViewById(R.id.textViewName);
        TextView textViewDate=(TextView) view.findViewById(R.id.textViewDate);

        Task task=getItem(position);

        textViewName.setText(task.getName());
        textViewDate.setText(task.getDate().toString());

        return view;
    }
}
