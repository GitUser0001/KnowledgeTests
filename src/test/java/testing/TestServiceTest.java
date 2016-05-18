package testing;

import JPA.Crud.TestService;
import org.junit.Test;


/**
 * Created by Study on 18.05.2016.
 */
public class TestServiceTest {
    TestService service = new TestService();

    @Test
    public void testSaveRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        JPA.Entity.Test test = new JPA.Entity.Test();
        test.setName("llaaa1");
        //Записали в БД
        JPA.Entity.Test test1 = service.add(test);

        //Вывели записанную в БД запись
        System.out.println(test1);

        service.delete(test1.getId());
    }

}
