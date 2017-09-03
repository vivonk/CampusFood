package vivonk.developer.inc.campusfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import vivonk.developer.inc.campusfood.R;

/**
 * Created by vivonk on 02-09-2017.
 */

public class CanteenLogin extends AppCompatActivity {
    private ProgressBar loginProgress;
    private ScrollView loginForm;
    private LinearLayout emailLoginForm;
    private AutoCompleteTextView email;
    private EditText password;
    private Button login;
    private Button signUp;
    private Button forgotPassword;
    private String strEmail,strPassword;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-09-02 16:52:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        loginProgress = (ProgressBar)findViewById( R.id.login_progress );
        loginForm = (ScrollView)findViewById( R.id.login_form );
        emailLoginForm = (LinearLayout)findViewById( R.id.email_login_form );
        email = (AutoCompleteTextView)findViewById( R.id.email );
        password = (EditText)findViewById( R.id.password );
        login = (Button)findViewById( R.id.login );
        signUp = (Button)findViewById( R.id.sign_up );
        forgotPassword = (Button)findViewById( R.id.forgot_password );

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-09-02 16:52:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_login);
        findViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = email.getText().toString();
                strPassword = password.getText().toString();
                if(strEmail.length()<=10&&strPassword.equals("")){
                    Toast.makeText(CanteenLogin.this, "Please enter correct details", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(getApplicationContext(),CanteenLogin.class));
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
