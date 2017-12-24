package fahadzia.quizzer.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fahadzia.quizzer.entity.*;

public class DatabaseHelper extends SQLiteOpenHelper implements Serializable
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Quizzer.db";

    private static final String TEACHER_TABLE_NAME="Teacher";
    private static final String STUDENT_TABLE_NAME="Student";
    private static final String QUIZ_TABLE_NAME="Quiz";
    private static final String QUESTION_TABLE_NAME="Question";

    private static final String COLUMN_QUIZ_NAME="Quiz_Name";

    private static final String COLUMN_QUESTION_PROMPT="Prompt";
    private static final String COLUMN_QUESTION_OPTION1="Option1";
    private static final String COLUMN_QUESTION_OPTION2="Option2";
    private static final String COLUMN_QUESTION_OPTION3="Option3";
    private static final String COLUMN_QUESTION_OPTION4="Option4";
    private static final String COLUMN_QUESTION_CHOSENANSWER="Chosen_Answer";
    private static final String COLUMN_QUESTION_CORRECTANSWER="Correct_Answer";

    private static final String COLUMN_TEACHER_USERNAME="Teacher_Username";
    private static final String COLUMN_TEACHER_PASSWORD="Teacher_Password";

    private static final String COLUMN_STUDENT_USERNAME="Student_Username";
    private static final String COLUMN_STUDENT_PASSWORD="Student_Password";

    private static final String QUIZ_TABLE_CREATE="create table "+QUIZ_TABLE_NAME+" ("+COLUMN_QUIZ_NAME+" text primary key not null)";
    private static final String QUESTION_TABLE_CREATE="create table "+QUESTION_TABLE_NAME+" ("+COLUMN_QUESTION_PROMPT+" text primary key not null, "
            + ""+COLUMN_QUESTION_OPTION1+" text not null, "+COLUMN_QUESTION_OPTION2+" text not null, "+COLUMN_QUESTION_OPTION3+" text not null, "+COLUMN_QUESTION_OPTION4+" text not null, "
            +""+COLUMN_QUESTION_CORRECTANSWER+" text not null, "+COLUMN_QUESTION_CHOSENANSWER+" text not null, "+COLUMN_QUIZ_NAME+" text FOREIGN KEY REFERENCES "+QUIZ_TABLE_NAME+"("+COLUMN_QUIZ_NAME+"))";

    private static final String TEACHER_TABLE_CREATE="create table "+TEACHER_TABLE_NAME+" ("+COLUMN_TEACHER_USERNAME+" text primary key not null, "+COLUMN_TEACHER_PASSWORD+" text password)";
    private static final String STUDENT_TABLE_CREATE="create table "+STUDENT_TABLE_NAME+" ("+COLUMN_STUDENT_USERNAME+" text primary key not null, "+COLUMN_STUDENT_PASSWORD+" text password)";

    SQLiteDatabase db;
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(STUDENT_TABLE_CREATE);
        db.execSQL(QUIZ_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);
        this.db=db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String dropTeacher="DROP TABLE IF EXISTS "+TEACHER_TABLE_NAME;
        String dropStudent="DROP TABLE IF EXISTS "+STUDENT_TABLE_NAME;
        String dropQuiz="DROP TABLE IF EXISTS "+QUIZ_TABLE_NAME;
        String dropQuestion="DROP TABLE IF EXISTS "+QUESTION_TABLE_NAME;
        db.execSQL(dropTeacher);
        db.execSQL(dropStudent);
        db.execSQL(dropQuestion);
        db.execSQL(dropQuiz);
        this.onCreate(db);
    }
    public Quiz getQuiz(String quizName)
    {
        db=this.getReadableDatabase();

        String query="Select * from "+QUESTION_TABLE_NAME+" where "+COLUMN_QUIZ_NAME+" = \""+quizName+"\"";
        Cursor cursor=db.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            List<Question> questions=new ArrayList();
            do
            {
                Question q=new Question();
                q.setPrompt(cursor.getString(0));
                q.setOption1(cursor.getString(1));
                q.setOption2(cursor.getString(2));
                q.setOption3(cursor.getString(3));
                q.setOption4(cursor.getString(4));
                q.setCorrect_answer(cursor.getString(5));
                questions.add(q);
            }
            while(cursor.moveToNext());
            Quiz quiz=new Quiz(quizName,questions);
            db.close();
            cursor.close();
            return quiz;
        }
        db.close();
        cursor.close();
        return null;
    }

/*    public boolean checkForProductExist(String productName)
    {
        db=this.getReadableDatabase();
        String query="Select Name from "+productTableName;
        Cursor cursor=db.rawQuery(query,null);
        String getProductName;
        if (cursor.moveToFirst())
        {
            do
            {
                getProductName=cursor.getString(0);
                if (getProductName.equals(productName))
                {
                    db.close();
                    cursor.close();
                    return true;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return false;
    }
    public List<Product> returnAllProducts()
    {
        List<Product> products=new ArrayList<Product>();
        db=this.getReadableDatabase();
        String query="Select * from "+productTableName;
        System.out.println(productTableName);
        Cursor cursor=db.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do
            {
                System.out.println("HELLO");
                String name=cursor.getString(0);
                System.out.println("HELLO1");
                String availability=cursor.getString(1);
                System.out.println("HELLO2");
                int price=cursor.getInt(2);
                System.out.println("HELLO3");
                Product product=new Product(name,price,availability);
                products.add(product);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return products;
    }
    public void editProductDatabase(String originalName, String editedName, String editedPrice, String editedAvailability)
    {
        db=this.getWritableDatabase();
        *//*ContentValues values=new ContentValues();
        values.put(columnProductName,editedName);
        values.put(columnProductPrice,Integer.parseInt(editedPrice));
        values.put(columnProductAvailability,editedAvailability);
        db.update(productTableName,values,"Name="+originalName,null);
        db.close();*//*
        int price= Integer.parseInt(editedPrice);
        String query="UPDATE Product SET Price="+price+",Availability='"+editedAvailability+"',Name='"+editedName+"' WHERE Name='"+originalName+"'";
        db.execSQL(query);
    }
    */
    public boolean checkForDuplicateTeacher(String userName)
    {
        db=this.getReadableDatabase();
        String query="Select "+COLUMN_TEACHER_USERNAME+" from "+TEACHER_TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String getUsername;
        if (cursor.moveToFirst())
        {
            do
            {
                getUsername=cursor.getString(0);
                if (getUsername.equals(userName))
                {
                    db.close();
                    return false;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return true;
    }

    public boolean checkForDuplicateStudent(String userName)
    {
        db=this.getReadableDatabase();
        String query="Select "+COLUMN_STUDENT_USERNAME+" from "+STUDENT_TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String getUsername;
        if (cursor.moveToFirst())
        {
            do
            {
                getUsername=cursor.getString(0);
                if (getUsername.equals(userName))
                {
                    db.close();
                    return false;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return true;
    }
   /* public boolean checkForDuplicateProduct(String productName)
    {
        db=this.getReadableDatabase();
        String query="Select "+columnProductName+" from "+productTableName;
        Cursor cursor;
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch(Exception e)
        {
            return true;
        }
        String getProductName;
        if (cursor.moveToFirst())
        {
            do
            {
                getProductName=cursor.getString(0);
                if (getProductName.equals(productName))
                {
                    db.close();
                    return false;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return true;
    }*/
    public boolean validateTeacher(String username, String password)
    {
        db=this.getReadableDatabase();
        String query="Select "+COLUMN_TEACHER_USERNAME+","+COLUMN_TEACHER_PASSWORD+" from "+TEACHER_TABLE_NAME;
        Cursor cursor ;
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch(Exception e)
        {
            return true;
        }
        String getUsername,getPassword;
        if (cursor.moveToFirst())
        {
            do
            {
                getUsername=cursor.getString(0);
                if (getUsername.equals(username))
                {
                    getPassword=cursor.getString(1);
                    if (getPassword.equals(password))
                    {
                        db.close();
                        return true;
                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return false;
    }

    public boolean validateStudent(String username, String password)
    {
        db=this.getReadableDatabase();
        String query="Select "+COLUMN_STUDENT_USERNAME+","+COLUMN_STUDENT_PASSWORD+" from "+STUDENT_TABLE_NAME;
        Cursor cursor ;
        try
        {
            cursor=db.rawQuery(query,null);
        }
        catch(Exception e)
        {
            return true;
        }
        String getUsername,getPassword;
        if (cursor.moveToFirst())
        {
            do
            {
                getUsername=cursor.getString(0);
                if (getUsername.equals(username))
                {
                    getPassword=cursor.getString(1);
                    if (getPassword.equals(password))
                    {
                        db.close();
                        return true;
                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return false;
    }

    public void insertStudent(Student student)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_STUDENT_USERNAME,student.getUsername());
        values.put(COLUMN_STUDENT_PASSWORD,student.getPassword());
        db.insert(STUDENT_TABLE_NAME,null,values);
        db.close();
    }

    public void insertTeacher(Teacher teacher)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_TEACHER_USERNAME,teacher.getUsername());
        values.put(COLUMN_TEACHER_PASSWORD,teacher.getPassword());
        db.insert(TEACHER_TABLE_NAME,null,values);
        db.close();
    }

    public void insertQuestion(Question question)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_QUESTION_PROMPT,question.getPrompt());
        values.put(COLUMN_QUESTION_OPTION1,question.getOption1());
        values.put(COLUMN_QUESTION_OPTION2,question.getOption2());
        values.put(COLUMN_QUESTION_OPTION3,question.getOption3());
        values.put(COLUMN_QUESTION_OPTION4,question.getOption4());
        values.put(COLUMN_QUESTION_CORRECTANSWER,question.getCorrect_answer());
        values.put(COLUMN_QUESTION_CHOSENANSWER,question.getChosen_answer());
        values.put(COLUMN_QUIZ_NAME,question.getQuiz().getQzName());
        db.insert(QUESTION_TABLE_NAME,null,values);
        db.close();
    }

    public void insertQuiz(Quiz quiz)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_QUIZ_NAME,quiz.getQzName());
        db.insert(QUIZ_TABLE_NAME,null,values);
        db.close();
    }
}
