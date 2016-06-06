package testing;

import com.testing.dao.impl.QuestionDaoImpl;
import com.testing.dao.impl.TestDaoImpl;
import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.Question;
import com.testing.model.User;
import com.testing.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Study on 18.05.2016.
 */
public class TestServiceTest {

    private TestDaoImpl service = new TestDaoImpl();
    private QuestionDaoImpl questionService = new QuestionDaoImpl();
    private UserDaoImpl userService = new UserDaoImpl();
    @Test
    public void testSaveRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        com.testing.model.Test test = new com.testing.model.Test();
        test.setName("llaaa1");
        //Записали в БД
        com.testing.model.Test test1 = service.add(test);

        //Вывели записанную в БД запись
        System.out.println(test1);

        service.delete(test1.getId());
    }

    @Test
    public void addQuestion() throws Exception {
        com.testing.model.Test test = service.getTestByName("TEST 1");

        Question question = new Question("name", new ArrayList<String>(){{add("aaa");add("ddd");}}, 1);

        question = questionService.add(question);
        test.addQuestion(question);
        service.update(test);

        test = service.getTestByName("TEST 1");
        System.out.println(test.getQuestions().size());
    }

    @Test
    public void addTestToUser() throws Exception {
        com.testing.model.Test test = service.get(111);
        User user = userService.get("Panda");

        new UserService().addTest(user,test, new Date(), -1);

        user = userService.get(user.getId());

        System.out.println(user.getTests().toString());
    }

}
