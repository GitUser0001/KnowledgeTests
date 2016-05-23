package testing;

import com.testing.dao.impl.TestDaoImpl;
import org.junit.Test;


/**
 * Created by Study on 18.05.2016.
 */
public class TestServiceTest {

    private TestDaoImpl service = new TestDaoImpl();

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

}
