package vivonk.developer.inc.campusfood.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vivonk.developer.inc.campusfood.Model.Food;
import vivonk.developer.inc.campusfood.R;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends ArrayAdapter<Food> implements View.OnClickListener {

    private ArrayList<Food> dataSet;
    Context mContext;
    public static ArrayList<Food> myList;
    public static ArrayList<Food> myPriceList;
    String url = "http://i.imgur.com/DvpvklR.png";
    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView amount;
        EditText quantity;
        ImageView info;
        Button add,minus;
    }

    public CustomAdapter(ArrayList<Food> data, Context context) {
        super(context, R.layout.tabitem, data);
        myList = new ArrayList<>();
        myPriceList = new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            Food food = new Food();
            food.setQuantity(data.get(i).getQuantity());
            food.setPrice(data.get(i).getPrice());
            myList.add(food);
            myPriceList.add(food);
        }
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Food dataModel = (Food) object;
        Log.e(TAG, "onClick: position is "+position );

        switch (v.getId()) {
            case R.id.food_image:
                Snackbar.make(v, "Food is " + dataModel.getName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
            case R.id.add:
                int now  = Integer.parseInt(dataModel.getQuantity());
                now++;
                dataModel.setQuantity(Integer.toString(now));
                break;
            case R.id.minus:
                now = Integer.parseInt(dataModel.getQuantity());
                Log.e(TAG, "onClick: Button called to decrease");
                if(now>0) {
                    now--;
                }
                dataModel.setQuantity(Integer.toString(now));
                Log.e(TAG, "onClick: dataModel "+dataModel.getQuantity());
                break;

        }
    }

    private int lastPosition = -1;
    private ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Food dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
         // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.tabitem, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.food);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.food_price);
            viewHolder.quantity = (EditText) convertView.findViewById(R.id.food_quantity);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.food_image);
            viewHolder.add =(Button)convertView.findViewById(R.id.add);
            viewHolder.minus = (Button) convertView.findViewById(R.id.minus);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        lastPosition = position;

        viewHolder.name.setText(dataModel.getName());
        viewHolder.amount.setText(dataModel.getPrice());
//        viewHolder.quantity.setText(dataModel.getQuantity());
//        viewHolder.quantity.setText(dataModel.get());
        viewHolder.info.setOnClickListener(this);
        viewHolder.add.setOnClickListener(this);
        viewHolder.minus.setOnClickListener(this);
        viewHolder.info.setTag(position);
        viewHolder.add.setTag(position);
        viewHolder.minus.setTag(position);
        Log.e(TAG, "getView: "+dataModel.getImageURL()+" is loaded or not, I don't know");
        Picasso.with(mContext).cancelRequest(viewHolder.info);
        Picasso.with(mContext).load(dataModel.getImageURL()).into(viewHolder.info, new Callback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: success" );
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError: error in loading is" );
            }
        });
        viewHolder.quantity.setText(myList.get(position).getQuantity());
        viewHolder.quantity.setTag(position);
        viewHolder.quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int position = (Integer)v.getTag();
                    final EditText Caption = (EditText) v;
                    myList.get(position).setQuantity(Caption.getText().toString());
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
