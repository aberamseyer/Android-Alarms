package ilstu.edu.project2alarms;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Abe on 11/7/2016.
 */

public class AlarmListFragment extends ListFragment {
    // TODO needs to read from the alarms csv file to create a string array of .toString() alarm objects

    String[] alarms = new String[] { "Alarms1","Alarm2","Alarm3","Alarm4","Alarm5","Alarm6","Alarm7","Alarm8","Alarm9" };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_alarms,
                container, false);
        //List<?> items = new ArrayAdapter<?>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, alarms);
        setListAdapter(adapter);

        return view;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Access the other fragment directly and call the "change" method  of that fragment
//        TextFragment txt = (TextFragment)getFragmentManager().
//                findFragmentById(R.id.fragment2);
//        txt.change(AndroidOS[position],"Version : "+Version[position]);
//        getListView().setSelector(android.R.color.holo_blue_dark);
//    }
    }
}
