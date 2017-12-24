package fahadzia.quizzer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fahadzia.quizzer.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonTeacherLogin;
    Button buttonStudentLogin;
    TextView signUpLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonTeacherLogin = (Button) findViewById(R.id.buttonTeacherLogin);
        buttonStudentLogin = (Button) findViewById(R.id.buttonStudentLogin);
        signUpLink = (TextView) findViewById(R.id.textViewSignup);

    }
    @Override
    public void onBackPressed()
    {
        this.finish();
    }
    public void onTeacherLoginClick(View v)
    {
        boolean check=true;
        if (editTextUsername.getText().toString().equals("") || editTextPassword.getText().toString().equals(""))
        {
            Toast notEnoughData=Toast.makeText(LoginActivity.this,"Empty fields!", Toast.LENGTH_SHORT);
            notEnoughData.show();
            check=false;
        }
        else if (check==true)
        {
            check=helper.validateTeacher(editTextUsername.getText().toString(),editTextPassword.getText().toString());

            if (check==false)
            {
                Toast invalid=Toast.makeText(LoginActivity.this,"Invalid username or password!", Toast.LENGTH_SHORT);
                invalid.show();
            }
            else if (check==true)
            {
                Toast invalid=Toast.makeText(LoginActivity.this,"Validated!", Toast.LENGTH_SHORT);
                invalid.show();
/*            Intent managerAddProduct = new Intent(LoginActivity.this, ManagerAddProductActivity.class);
            //managerAddProduct.putExtra("DBObject", (Serializable) helper);
            LoginActivity.this.startActivity(managerAddProduct);*/
            }
        }

    }

    public void onStudentLoginClick(View v)
    {
        boolean check=true;
        if (editTextUsername.getText().toString().equals("") || editTextPassword.getText().toString().equals(""))
        {
            Toast notEnoughData=Toast.makeText(LoginActivity.this,"Empty fields!", Toast.LENGTH_SHORT);
            notEnoughData.show();
            check=false;
        }
        else if (check==true)
        {
            check=helper.validateStudent(editTextUsername.getText().toString(),editTextPassword.getText().toString());
            if (check==false)
            {
                Toast invalid=Toast.makeText(LoginActivity.this,"Invalid username or password!", Toast.LENGTH_SHORT);
                invalid.show();
            }
            else if (check==true)
            {
                Toast invalid=Toast.makeText(LoginActivity.this,"Validated!", Toast.LENGTH_SHORT);
                invalid.show();
/*            Intent managerAddProduct = new Intent(LoginActivity.this, ManagerAddProductActivity.class);
            //managerAddProduct.putExtra("DBObject", (Serializable) helper);
            LoginActivity.this.startActivity(managerAddProduct);*/
            }
        }

    }

    public void onSignUpClick(View v)
    {
        Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        LoginActivity.this.startActivity(signUpIntent);
    }

    public EditText getEditTextUsername() {
        return editTextUsername;
    }

    public EditText getEditTextPassword() {
        return editTextPassword;
    }

    public void setEditTextUsername(EditText editTextUsername) {
        this.editTextUsername = editTextUsername;
    }

    public void setEditTextPassword(EditText editTextPassword) {
        this.editTextPassword = editTextPassword;
    }
}

