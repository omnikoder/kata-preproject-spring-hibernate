package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<User> userList = List.of(
              new User("Tom", "Cooper", "tom@pm.me", new Car("Ford", 1960)),
              new User("Ivan", "Ivanov", "ivan@mail.ru", new Car("Lada", 2000)),
              new User("Sam", "Smith", "sam@gmail.com", new Car("Kia", 2020)),
              new User("成", "龍", "chan@alibaba.cn")
      );
      userList.forEach(userService::add);
      userService.listUsers().forEach(System.out::println);
      System.out.println(userService.getUserByCarModelAndSeries("Lada", 2000));

      context.close();
   }
}
