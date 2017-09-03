package vivonk.developer.inc.campusfood;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;

import vivonk.developer.inc.campusfood.Adapter.CustomAdapter;
import vivonk.developer.inc.campusfood.Fragment.FoodFragment;

import static vivonk.developer.inc.campusfood.Adapter.CustomAdapter.myList;
import static vivonk.developer.inc.campusfood.Adapter.CustomAdapter.myPriceList;
import static vivonk.developer.inc.campusfood.Fragment.FoodFragment.adapter;

public class StudentDashboard extends AppCompatActivity {

    private TextView mTextMessage;
    private int sum=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Checkout.preload(getApplicationContext());
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);


                    Log.e("TAG", "onNavigationItemSelected: adapter is "+myList.get(0).getQuantity()+"  price is "+myPriceList.get(0).getPrice());

                    return true;
                case R.id.navigation_dashboard:
                    for(int i=0;i<adapter.getCount();i++)
                    {
                        int a = Integer.parseInt(myList.get(i).getQuantity());
                        int b = Integer.parseInt(myPriceList.get(i).getPrice());
                        sum+=a*b;
                    }
                    //mTextMessage.setText(R.string.title_dashboard);
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(StudentDashboard.this);
                    alertBuilder.setMessage("Your bill is of ₹"+Integer.toString(sum)+". Would you like to buy some beverage also.")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(StudentDashboard.this, "Ok", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    startActivity(new Intent(getApplicationContext(),MerchantActivity.class).putExtra("price",Integer.toString(sum)));
                                }
                            })
                            .create().show();

                    return true;
                /*case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FoodFragment foodFragment = new FoodFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,foodFragment).commit();
    }

}
