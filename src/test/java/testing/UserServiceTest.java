package testing;

import JPA.Crud.TestService;
import JPA.Crud.UserService;
import JPA.Entity.User;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public class UserServiceTest {
    UserService service = new UserService();

    @Test
    public void testSaveRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setAdmin(false);
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test");
        user1.setPassword("123");

        //Записали в БД
        User user = service.add(user1);

        //Вывели записанную в БД запись
        System.out.println(user);
    }

    @Test
    public void testDeleteRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setAdmin(false);
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Записуем его в БД
        User user = service.add(user1);

        //Удвлем его с БД
        service.delete(user.getId());
    }

    @Test
    public void testSelect() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setAdmin(false);
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Записываем в БД
        User user = service.add(user1);

        //Получние с БД Citroen‎
        User userFromDB = service.get(user.getId());
        System.out.println(userFromDB);
    }

    @Test
    public void testUpdate() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setAdmin(false);
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Записываем в БД
        user1 = service.add(user1);

        user1.setLastName("lalala");

        //Обновляем
        service.update(user1);

        //Получаем обновленую запись
        User user2 = service.get(user1.getId());
        System.out.println(user2);
    }

    public void testGetAll(){
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setAdmin(false);
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Создаем автомобиль для записи в БД
        User user2 = new User();
        user2.setAdmin(false);
        user2.setFirstName("Ivan");
        user2.setLastName("Petrov");
        user2.setNick("test1");
        user2.setPassword("123");

        //Создаем автомобиль для записи в БД
        User user3 = new User();
        user3.setAdmin(false);
        user3.setFirstName("Ivan");
        user3.setLastName("Petrov");
        user3.setNick("test1");
        user3.setPassword("123");

        //Сохраняем все авто
        service.add(user1);
        service.add(user2);
        service.add(user3);

        //Получаем все авто с БД
        List<User> users = service.getAll();

        //Выводим полученый список авто
        for(User u : users){
            System.out.println(u);
        }
    }
}
