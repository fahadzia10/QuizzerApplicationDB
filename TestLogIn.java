package fahadzia.quizzer;

import org.junit.Test;

import fahadzia.quizzer.activity.DatabaseHelper;
import fahadzia.quizzer.activity.LoginActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestLogIn {
    @Test
    public void addition_isCorrect() throws Exception {
        LoginActivity l=new LoginActivity();
        DatabaseHelper h=new DatabaseHelper(l);
        assertTrue(h.checkForDuplicateTeacher("fahadzia")); //Should give true as it already exists
        assertFalse(h.validateTeacher("notindatabase","000")); //Should give false as it isnt in database
    }
}