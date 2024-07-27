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

      Car car1 = new Car("model1", 1);
      Car car2 = new Car("model2", 2);
      Car car3 = new Car("model3", 3);
      Car car5 = new Car("model5", 5);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user5 = new User("User5", "LastName5", "user5@mail.ru");

      user1.setUserCar(car1);
      user2.setUserCar(car2);
      user3.setUserCar(car3);
      user5.setUserCar(car5);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(user5);

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car = "+user.getUserCar());
//         System.out.println();
//      }

      List<User> foundUsers = userService.findByCar("model3", 3);
      foundUsers.forEach(System.out::println);

      context.close();
   }
}
