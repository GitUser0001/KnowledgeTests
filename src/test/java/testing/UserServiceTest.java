package testing;

import com.testing.dao.impl.GroupDaoImpl;
import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.Group;
import com.testing.model.User;
import com.testing.model.enums.UserRoleEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public class UserServiceTest {

    private UserDaoImpl service = new UserDaoImpl();


    @Test
    public void testUserGroup() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("testTest");
        user1.setPassword("13");

        Group studentGroup = new Group(UserRoleEnum.USER);
        GroupDaoImpl groupService = new GroupDaoImpl();
        studentGroup = groupService.add(studentGroup);



        //Записали в БД
        //User user = service.add(user1);

        List<Group> gr = new ArrayList<>();
        gr.add(studentGroup);

        user1.setGroups(gr);

        user1 = service.add(user1);

        //Вывели записанную в БД запись
        System.out.println(user1);


        service.delete(user1.getId());
        groupService.delete(studentGroup.getId());
    }


    @Test
    public void testSaveRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("testTest");
        user1.setPassword("123");

        //Записали в БД
        User user = service.add(user1);

        //Вывели записанную в БД запись
        System.out.println(user);


        service.delete(user.getId());
    }

    @Test
    public void testDeleteRecord() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
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
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Записываем в БД
        User user = service.add(user1);

        //Получние с БД Citroen‎
        User userFromDB = service.get(user.getId());
        System.out.println(userFromDB);

        service.delete(userFromDB.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        //Создаем автомобиль для записи в БД
        User user1 = new User();
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

        service.delete(user2.getId());
    }

    public void testGetAll(){
        //Создаем автомобиль для записи в БД
        User user1 = new User();
        user1.setFirstName("Ivan");
        user1.setLastName("Petrov");
        user1.setNick("test1");
        user1.setPassword("123");

        //Создаем автомобиль для записи в БД
        User user2 = new User();
        user2.setFirstName("Ivan");
        user2.setLastName("Petrov");
        user2.setNick("test1");
        user2.setPassword("123");

        //Создаем автомобиль для записи в БД
        User user3 = new User();
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
            service.delete(u.getId());
        }
    }
}
