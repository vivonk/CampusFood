package vivonk.developer.inc.campusfood.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vivonk.developer.inc.campusfood.Adapter.CustomAdapter;
import vivonk.developer.inc.campusfood.Model.Food;
import vivonk.developer.inc.campusfood.R;

import static android.content.ContentValues.TAG;

/**
 * Created by vivonk on 03-09-2017.
 */

public class FoodFragment extends Fragment {
    private ListView listView;
    private View view;

    public CustomAdapter getAdapter() {
        return adapter;
    }

    public static CustomAdapter adapter;
    private ArrayList<Food> model ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_food,container,false);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: is called" );
        listView = (ListView) view.findViewById(R.id.list);
        model = new ArrayList<>();
        model.add(new Food("Parantha","20","http://luckydhabba.com/wp-content/uploads/2016/06/Menu-071.jpg","0"));
        model.add(new Food("Meggi","20","http://www.dfordelhi.in/wp-content/uploads/2016/06/MAGGI-NOODLES-facebook.jpg","0"));
        model.add(new Food("Pasta","25","https://i0.wp.com/hiddenponies.com/wp-content/uploads/2013/05/pork-pasta.jpg","0"));
        model.add(new Food("Samosa","10","http://www.ndtv.com/cooks/images/moong-dal-samosa-new.jpg","0"));
        model.add(new Food("French Fries","20","http://az616578.vo.msecnd.net/files/2016/09/17/636096940768710804952549616_french-fries-1200.jpg","0"));
       /* model.add(new Food("Parantha","20","http://luckydhabba.com/wp-content/uploads/2016/06/Menu-071.jpg","0"));
        model.add(new Food("Parantha","20","http://luckydhabba.com/wp-content/uploads/2016/06/Menu-071.jpg","0"));*/


        adapter = new CustomAdapter(model,getContext());
        listView.setAdapter(adapter);

    }
}
