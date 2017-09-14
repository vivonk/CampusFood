package vivonk.developer.inc.campusfood;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Random;

public class MerchantActivity extends Activity implements PaymentResultListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_payment);
            Bundle bundle = getIntent().getExtras();
            startPayment(bundle.getString("price","50000"));
          }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
            /**
             * Add your logic here for a successfull payment response
             */
                    Toast.makeText(this, "Payment is successful, move to canteen after 20 minute and bring your order.", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onPaymentSuccess: success is "+razorpayPaymentID );

    }

    @Override
    public void onPaymentError(int code, String response) {
           /**
             * Add your logic here for a failed payment response
            *
            */
         Log.e(TAG, "onPaymentError: Response is "+response );
    }
    public void startPayment(String price) {
            /**
              * Instantiate Checkout
             */
                   Checkout checkout = new Checkout();

                    /**
                      * Set your logo here
                     */
                            checkout.setImage(R.drawable.campusfood_background);

                   /**
                     * Reference to current activity
                     */
                           final Activity activity = this;

                   /**
                     * Pass your payment options to the Razorpay Checkout as a JSONObject
                    */
                            try {
                JSONObject options = new JSONObject();

                         /**
                           * Merchant Name
                           * eg: Rentomojo || HasGeek etc.
                           */
                                 options.put("Nirmal Sarswat ", "Merchant Name");

                          /**
                            * Description can be anything
                          * eg: Order #123123
                           *     Invoice Payment
                           *     etc.
                           */
                                 Random rand = new Random();

                        int  n = rand.nextInt(9999999) + 1;
                  options.put("description", "Order #"+Integer.toString(n));

                         options.put("currency", "INR");


                                  options.put("amount",  Integer.toString(Integer.parseInt(price)*100));
                        checkout.open(activity, options);
              } catch(Exception e) {
                Log.e(TAG, "Error in starting Razorpay Checkout", e);
               }
         }

}