package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepository.save(new User(10l, "test_email"));
        usersRepository.update(new User(10l, "test_email_test_update2"));
//        usersRepository.delete(1L);
        System.out.println(usersRepository.findByEmail("test_email"));
        System.out.println(usersRepository.findById(1L));
        System.out.println(usersRepository.findAll());
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(1L));
        System.out.println(usersRepository.findByEmail("test_email"));
        usersRepository.save(new User(142L, "template2"));
        usersRepository.save(new User(141L, "template1"));
        usersRepository.save(new User(143L, "template3"));
        usersRepository.update(new User(142L, "qwqwqwqwqwq"));
        usersRepository.delete(141L);
        usersRepository.delete(140L);
        System.out.println(usersRepository.findAll());
    }
}
