package fahadzia.quizzer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fahadzia.quizzer.R;
import fahadzia.quizzer.entity.Student;
import fahadzia.quizzer.entity.Teacher;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonTeacherSignUp;
    Button buttonStudentSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextUsername=(EditText) findViewById(R.id.editTextUsername);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonTeacherSignUp=(Button) findViewById(R.id.buttonTeacherSignUp);
        buttonStudentSignUp=(Button) findViewById(R.id.buttonStudentSignUp);
        Bundle bundle=getIntent().getExtras();
        //helper= (DatabaseHelper) getIntent().getSerializableExtra("DBObject");
    }

    public void onTeacherSignUpClicked(View view)
    {
        boolean check=true;
        if (editTextPassword.getText().toString().equals("") || editTextUsername.getText().toString().equals(""))
        {
            Toast notEnoughData=Toast.makeText(SignUpActivity.this,"Empty fields!", Toast.LENGTH_SHORT);
            notEnoughData.show();
            check=false;
        }
        if (check==true)
        {
            check=helper.checkForDuplicateTeacher(editTextUsername.getText().toString());
            if (check==true)
            {
                Teacher teacher=new Teacher();
                teacher.setUsername(editTextUsername.getText().toString());
                teacher.setPassword(editTextPassword.getText().toString());
                helper.insertTeacher(teacher);
                Toast inserted=Toast.makeText(SignUpActivity.this,"Signed Up!", Toast.LENGTH_SHORT);
                inserted.show();
            }
            else
            {
                Toast exist=Toast.makeText(SignUpActivity.this,"Username already exists", Toast.LENGTH_SHORT);
                exist.show();
            }
        }
    }

    public void onStudentSignUpClicked(View view)
    {
        boolean check=true;
        if (editTextPassword.getText().toString().equals("") || editTextUsername.getText().toString().equals(""))
        {
            Toast notEnoughData=Toast.makeText(SignUpActivity.this,"Cannot leave empty!", Toast.LENGTH_SHORT);
            notEnoughData.show();
            check=false;
        }
        if (check==true)
        {
            check=helper.checkForDuplicateTeacher(editTextUsername.getText().toString());
            if (check==true)
            {
                Student student=new Student();
                student.setUsername(editTextUsername.getText().toString());
                student.setPassword(editTextPassword.getText().toString());
                helper.insertStudent(student);
                Toast inserted=Toast.makeText(SignUpActivity.this,"Signed Up!", Toast.LENGTH_SHORT);
                inserted.show();
            }
            else
            {
                Toast exist=Toast.makeText(SignUpActivity.this,"Username already exists", Toast.LENGTH_SHORT);
                exist.show();
            }
        }
    }

}