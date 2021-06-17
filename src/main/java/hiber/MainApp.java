package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User bob = new User("Bob", "Bobov", "bob@mail.com");
      User vova = new User("Vova", "Vovkin", "vova@mail.com");
      User rob = new User("Rob", "Robov", "Rob@mail.com");
      User stive = new User("Stive", "Sigal", "Stive@mail.com");

      Car priora = new Car("priora", 666);
      Car vesta = new Car("vesta", 110);
      Car zaz = new Car("zaz0", 321);
      Car uaz = new Car("uaz", 777);

      bob.setCar(priora);
      userService.add(bob);

      vova.setCar(vesta);
      userService.add(vova);

      rob.setCar(zaz);
      userService.add(rob);

      stive.setCar(uaz);
      userService.add(stive);


      for (User user : userService.listUsers()) {
         System.out.println(user+ "My test++++++++++");
      }


      System.out.println(userService.getUserByCar("zaz0", 321));





      context.close();
   }
}

