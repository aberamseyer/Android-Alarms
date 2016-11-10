package ilstu.edu.project2alarms;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ilstu.edu.project2alarms.objects.Alarm;

/**
 * Created by Abe on 11/7/2016.
 */

public class AlarmListFragment extends ListFragment {
    // TODO needs to read from the alarms csv file to create a string array of .toString() alarm objects

    Alarm[] alarms = MainActivity.getAlarms();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_alarms,
                container, false);
        //List<?> items = new ArrayAdapter<?>();
        ArrayAdapter<Alarm> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, alarms);
        setListAdapter(adapter);

        return view;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }
}
