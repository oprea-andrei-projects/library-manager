package ro.mycode.librarymanagerapi;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.mycode.librarymanagerapi.dto.StudentDTO;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;
import ro.mycode.librarymanagerapi.repo.BookRepo;
import ro.mycode.librarymanagerapi.repo.StudentRepo;
import ro.mycode.librarymanagerapi.services.StudentService;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApiApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo){

        return args -> {

//            Faker faker = new Faker();
//
//            for (int i = 0; i < 10; i++) {
//
//
//                Student student=new Student(faker.name().firstName(), faker.name().lastName(),faker.internet().safeEmailAddress(),faker.internet().password(),faker.number().numberBetween(1,100));
//
//                for (int j = 0; j < 5; j++) {
//                    student.addBook( new Book(faker.book().title(), i));
//                }
//
//
//                studentRepo.save(student);
//            }
//
////            StudentDTO sdto1 = new StudentDTO("georgie.homenick@example.com","zx9jaak3r2");
////
////            StudentService studentService = new StudentService(studentRepo);
////
////            System.out.println(studentService.login(sdto1));


        };
    }
}
