package vivonk.developer.inc.campusfood.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.encryptor4j.Encryptor;
import org.encryptor4j.factory.KeyFactory;
import org.json.JSONArray;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.Random;

import javax.crypto.SecretKey;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout mMAccountNumber,mMAadharNumber,mMIFSCCode,mMMobileNumber,mMEmailAddress,mMOtp,mMPassword,mMFirstName,mMLastName,mMAddress,mMDistrict;
    private CardView mCardView1,mCardView2,mCardView3;
    Button mNext1,mNext2,mNext3;
    EditText etOtp,etFirstName,etLastName,etAddress,etAadharNumber,etAccountNumber,etIFSCCode,etMobileNumber,etEmailAddress,etDistrict;
    CheckBox mCheckPassword;
    TextInputEditText mPassword;
    TextView mOtpTextView,mGenderTextView;
    Spinner spBankName,spBusinessType;
    RadioButton mRbMale,mRbFemale;
    boolean mLanguageHindi;
    int otp;
    String strFirst,strLast,strGender,strDistrict,strLocation,strPassword;
    String strAadhar,strAccount,strBank,strIFSCCode,strEmail,strMobile,strBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mMAadharNumber = (TextInputLayout) findViewById(R.id.merchant_aadhar_number_input_layout);
        mMAccountNumber = (TextInputLayout) findViewById(R.id.merchant_account_number_input_layout);
        mMIFSCCode = (TextInputLayout) findViewById(R.id.merchant_ifsc_code_input_layout);
        mMMobileNumber = (TextInputLayout) findViewById(R.id.merchant_mobile_number_input_layout);
        mMEmailAddress = (TextInputLayout) findViewById(R.id.merchant_email_address_input_layout);
        mMOtp = (TextInputLayout) findViewById(R.id.merchant_otp_input_layout);
        mMPassword = (TextInputLayout) findViewById(R.id.merchant_password_input_layout);
        mMFirstName = (TextInputLayout) findViewById(R.id.merchant_first_name_input_layout);
        mMLastName = (TextInputLayout) findViewById(R.id.merchant_last_name_input_layout);
        mMAddress = (TextInputLayout) findViewById(R.id.merchant_address_input_layout);
        mMDistrict = (TextInputLayout) findViewById(R.id.merchant_address_district_input_layout);

        etAadharNumber = (EditText) findViewById(R.id.merchant_aadhar_number);
        spBankName = (Spinner) findViewById(R.id.merchant_bank_name_spinner);
        etIFSCCode = (EditText) findViewById(R.id.merchant_ifsc_code);
        etMobileNumber = (EditText) findViewById(R.id.merchant_mobile_number);
        etEmailAddress = (EditText) findViewById(R.id.merchant_email_address);
        etOtp = (EditText) findViewById(R.id.merchant_otp);
        etFirstName = (EditText) findViewById(R.id.merchant_first_name);
        etLastName = (EditText) findViewById(R.id.merchant_last_name);
        etAddress = (EditText) findViewById(R.id.merchant_address);
        etAccountNumber = (EditText) findViewById(R.id.merchant_account_number);
        etDistrict = (EditText) findViewById(R.id.merchant_address_district);
        spBusinessType = (Spinner) findViewById(R.id.merchant_business_type);

        mRbMale = (RadioButton) findViewById(R.id.rb_male);
        mRbFemale = (RadioButton) findViewById(R.id.rb_female);

        mOtpTextView = (TextView) findViewById(R.id.otp_text_view);
        mGenderTextView = (TextView) findViewById(R.id.tv_gender);

        mCardView1 = (CardView) findViewById(R.id.card_view_1);
        mCardView2 = (CardView) findViewById(R.id.card_view_2);
        mCardView3 = (CardView) findViewById(R.id.card_view_3);

        mNext1 = (Button) findViewById(R.id.button_next_1);
        mNext2 = (Button) findViewById(R.id.button_next_2);
        mNext3 = (Button) findViewById(R.id.button_next_3);

        mPassword = (TextInputEditText) findViewById(R.id.merchant_password);

        mCheckPassword = (CheckBox) findViewById(R.id.checkbox_password);

        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            mMAccountNumber.setHint(getResources().getString(R.string.account_number_hindi));
            mMMobileNumber.setHint(getResources().getString(R.string.mobile_number_hindi));
            mMIFSCCode.setHint(getResources().getString(R.string.ifsc_code_hindi));
            mMEmailAddress.setHint(getResources().getString(R.string.email_hindi));
            mMAccountNumber.setHint(getResources().getString(R.string.account_number_hindi));
            mMOtp.setHint(getResources().getString(R.string.otp_hindi));
            mMAadharNumber.setHint(getResources().getString(R.string.aadhar_number_hindi));
            mMPassword.setHint(getResources().getString(R.string.password_hindi));
            mMFirstName.setHint(getResources().getString(R.string.first_name_hindi));
            mMLastName.setHint(getResources().getString(R.string.last_name_hindi));
            mMAddress.setHint(getResources().getString(R.string.address_hindi));

            mOtpTextView.setText(R.string.otp_text_view_hindi);
            mGenderTextView.setText(R.string.gender_hindi);

            mNext1.setText(R.string.next_hindi);
            mNext2.setText(R.string.next_hindi);
            mNext3.setText(R.string.submit_hindi);

            mCheckPassword.setText(R.string.show_password_hindi);

            mRbMale.setText(R.string.male_hindi);
            mRbFemale.setText(R.string.female_hindi);

            mLanguageHindi = true;
        }

        String simple = mMAadharNumber.getHint().toString();
        mMAadharNumber.setHint(setMandatory(simple));

        simple = mMIFSCCode.getHint().toString();
        mMIFSCCode.setHint(setMandatory(simple));

        simple = mMAccountNumber.getHint().toString();
        mMAccountNumber.setHint(setMandatory(simple));

        simple = mMMobileNumber.getHint().toString();
        mMMobileNumber.setHint(setMandatory(simple));

      /*  simple = mMEmailAddress.getHint().toString();
        mMEmailAddress.setHint(setMandatory(simple));
*/
        mNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidatorOne()) {
                    mCardView1.setVisibility(View.GONE);
                    mCardView2.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(SignUpActivity.this, "Please provide all details", Toast.LENGTH_SHORT).show();
                    //TODO Hindi
                }
            }
        });

        mNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidatorTwo()) {
                    mCardView2.setVisibility(View.GONE);
                    mCardView3.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(SignUpActivity.this, "Please enter correct OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidatorThree()) {
                    if(mRbFemale.isChecked()){
                        strGender = "female";
                    }
                    else{
                        strGender = "male";
                    }
                    mCardView3.setVisibility(View.VISIBLE);
                    new UploadData().execute();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Please enter correct details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCheckPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(!isChecked){
                    mPassword.setTransformationMethod(new PasswordTransformationMethod());
                    mPassword.setSelection(mPassword.getText().toString().length());
                }
                else {
                    mPassword.setTransformationMethod(null);
                    mPassword.setSelection(mPassword.getText().toString().length());

                }
            }
        });
    }
    String setMandatory(String simple){
        String colored = " *";
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.BLACK), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder.toString();
    }
    boolean ValidatorOne(){
        strAadhar = etAadharNumber.getText().toString();
        strBank = spBankName.getSelectedItem().toString();
        strAccount = etAccountNumber.getText().toString();
        strIFSCCode = etIFSCCode.getText().toString();
        strMobile = etMobileNumber.getText().toString();
        strEmail = etEmailAddress.getText().toString();
        Log.e("Strings are", "ValidatorOne: --------------  \n"+strAadhar.length()+"\n"+ strBank.equals("SELECT BANK NAME")+"\n"+strAccount.isEmpty()+"\n"+strMobile.length()+"\n"+strIFSCCode.isEmpty());
        if(strAadhar.length()==12&!strAccount.isEmpty()&!strBank.equals("SELECT BANK NAME")&!strIFSCCode.isEmpty()&strMobile.length()==10){

            Random rand = new Random();
            otp = rand.nextInt(99999)+1;
            Log.e(" OTP ", "ValidatorTwo: ---------------     "+ otp );
            new SendingSMS().execute(Integer.toString(otp),strMobile);
            return true;
        }
        return false;
    }
    boolean ValidatorTwo(){
        return Integer.toString(otp).equals(etOtp.getText().toString());
    }
    boolean ValidatorThree(){

        strFirst = etFirstName.getText().toString();
        strLast = etLastName.getText().toString();
        strDistrict = etDistrict.getText().toString();
        strLocation = etAddress.getText().toString();
        strPassword = mPassword.getText().toString();
        return !strFirst.isEmpty() & !strLast.isEmpty() & !strDistrict.isEmpty() & !strLocation.isEmpty() & strPassword.length() >= 8 & (mRbMale.isChecked() | mRbFemale.isChecked());
    }

    private class SendingSMS extends AsyncTask<String, Object, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            {
                //Your user name

                String username = "Rishi1";
                //Your authentication key
                String authkey = "d808a22243XX";
                //Multiple mobiles numbers separated by comma (max 200)
                String mobiles = strings[1];

                //Sender ID,While using route4 sender id should be 6 characters  long.
                String senderId = "MRWSHD";
                //Your message to send, Add URL encoding here.
                String message = "";
                //define route
                String accusage="1";


                message = "Please enter the OTP " +strings[0] + " to complete the verification of your number.";

                //Prepare Url
                URLConnection myURLConnection=null;
                URL myURL=null;
                BufferedReader reader=null;

                //encoding message
                String encoded_message= URLEncoder.encode(message);

                //Send SMS API
                String mainUrl="http://smspanel.marwadishaadi.com/submitsms.jsp?";

                //Prepare parameter string
                StringBuilder sbPostData= new StringBuilder(mainUrl);
                sbPostData.append("user="+username);
                sbPostData.append("&key="+authkey);
                sbPostData.append("&mobile="+mobiles);
                sbPostData.append("&message="+encoded_message);
                sbPostData.append("&accusage="+accusage);
                sbPostData.append("&senderid="+senderId);

                //final string
                mainUrl = sbPostData.toString();
                try
                {
                    //prepare connection

                    myURL = new URL(mainUrl);
                    myURLConnection = myURL.openConnection();
                    myURLConnection.connect();
                    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                    //reading response
                    String response;
                    while ((response = reader.readLine()) != null)
                        //print response

                        //finally close connection
                        reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    private class UploadData extends AsyncTask<String,String,String>{
        ProgressDialog pgd;
        @Override
        protected void onPreExecute() {
            pgd = new ProgressDialog(SignUpActivity.this);
            if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
                pgd.setMessage(getResources().getString(R.string.please_wait_hindi));
            }else {
                pgd.setMessage(getResources().getString(R.string.please_wait));
            }
            pgd.setCancelable(false);
            pgd.show();
            strBusiness = spBusinessType.getSelectedItem().toString();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            String encPassword = "12345678";
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                        strPassword.getBytes(StandardCharsets.UTF_8));
                encPassword= bytesToHex(encodedhash);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            /*strAadhar = Encrypt(strAadhar);
            strBank = Encrypt(strBank);
            strAccount = Encrypt(strAccount);
            strIFSCCode = Encrypt(strIFSCCode);
            strMobile = Encrypt(strMobile);
            strEmail = Encrypt(strEmail);
            strFirst = Encrypt(strFirst);
            strLast = Encrypt(strLast);

            strGender = Encrypt(strGender);
            strLocation = Encrypt(strLocation);
            strDistrict = Encrypt(strDistrict);*/
            Log.e("strings are ", "doInBackground: ********************** "+strDistrict+"\n"+strLocation+"\n"+strGender+"\n"+strLast+"\n"+strFirst+"\n"+strPassword+"\n"+strAccount+"\n"+strAadhar+"\n"+strBank+"\n"+strIFSCCode+"\n"+strMobile+"\n"+strEmail+"\n" );

            AndroidNetworking.post("http://ec2-34-204-44-57.compute-1.amazonaws.com:8081/signup")
                    .addBodyParameter("aadhar_number",strAadhar)
                    .addBodyParameter("bank_name",strBank)
                    .addBodyParameter("account_name",strAccount)
                    .addBodyParameter("ifsc_code",strIFSCCode)
                    .addBodyParameter("customer_id",strMobile)
                    .addBodyParameter("email",strEmail)
                    .addBodyParameter("first_name",strFirst)
                    .addBodyParameter("last_name",strLast)
                    .addBodyParameter("gender",strGender)
                    .addBodyParameter("password",encPassword)
                    .addBodyParameter("location",strLocation)
                    .addBodyParameter("district",strDistrict)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("TAG", "onResponse:99999999999999  "+response );
                            SharedPreferences sharedPreference = getSharedPreferences("user_info",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreference.edit();
                            editor.putString("aadhar",strAadhar)
                                    .putString("bank",strBank)
                                    .putString("account",strAccount)
                                    .putString("ifsc",strIFSCCode)
                                    .putString("mobile",strMobile)
                                    .putString("email",strEmail)
                                    .putString("type",strBusiness)
                                    .apply();

                            SignUpActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), DashBoard.class));
                                }
                            });
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.e("TAG", "onResponse:99999999999999  "+anError );

                        }
                    });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pgd.dismiss();
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    private  String Encrypt(String string){
        SecretKey secretKey = (SecretKey) KeyFactory.AES.randomKey();
        String key= secretKey.toString();
        Log.e("Key ", "onClick: secret key is "+ key );
        Encryptor encryptor = new Encryptor(secretKey, "AES/GCM/NoPadding", 16, 128);
        byte[] encrypted = new byte[0];
        try {
            encrypted = encryptor.encrypt(string.getBytes());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        byte[] decrypted = new byte[0];
        try {
            decrypted = encryptor.decrypt(encrypted);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        Log.e("decrypted key + enc ", "onClick: ------------   "+new String(decrypted)+" enc is ***********0   "+new String(encrypted));
//
/*
        Log.e("Credential", "onClick: customer id is "+ encCustomerId+ " decrypt is "+" ------------------------ password is    "+encPassword );*/
        return new String(encrypted);
    }
    private static String Decrypt(){
        return null;
    }
}
