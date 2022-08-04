package ro.mycode.librarymanagerapi.services;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.TestPropertySource;
import ro.mycode.librarymanagerapi.exceptions.NoStudentsFoundException;
import ro.mycode.librarymanagerapi.exceptions.WrongIDException;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;
import ro.mycode.librarymanagerapi.repo.BookRepo;
import ro.mycode.librarymanagerapi.repo.StudentRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@TestPropertySource(
        locations="classpath:application-it.properties"
)
class StudentServiceTest {

        @Mock
        private StudentRepo studentRepo;

        @Mock
        private BookRepo bookRepo;

        @InjectMocks
        private  StudentService studentService;

        @Captor
        private ArgumentCaptor<Book> bookArgumentCaptor;

    public StudentServiceTest() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void getAllStudendntsIfStudentsExists(){

        List<Student> students = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i<10; i++){
            students.add(new Student(faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.internet().password(),faker.number().numberBetween(1,65)));
        }

        when(studentRepo.findAll()).thenReturn(students);

        assertEquals(studentService.getAllStudents().size(),10);

//merge

    }

    @Test
    void getExceptionIfStudentsDontExist(){

        List<Student> students = new ArrayList<>();

        when(studentRepo.findAll()).thenReturn(students);

        assertThrows(NoStudentsFoundException.class,()->{

            studentService.getAllStudents();
        });

    }


    @Test
    void saveTheStudentOkStatus(){


        Student s = new Student("asdd","dwdqd","qwdd","qdwqdw",22);

        when(studentRepo.findStudentByName(s.getFirst_name(),s.getLast_name())).thenReturn(null);

       // boolean statement = studentService.addAstudent(s);

        assertEquals(studentService.addAstudent(s),true);


    }


    @Test
    void test_getStudentByID(){

      Student s = new Student(1L,"uduwduh","sdtwfdt","bwdbwddu","wbduwdudbu",23);

        when(studentRepo.findById(1L)).thenReturn(Optional.of(s));

        when(studentRepo.getThisStudent(1L)).thenReturn(s);

        assertEquals(studentService.getStudentByTheID(1l),s);


    }

    @Test
    void test_findBookById(){
        Book b = new Book(4L,"book-name", 1);
        Student s = new Student(1L,"uduwduh","sdtwfdt","bwdbwddu","wbduwdudbu",23);
        b.setStudent(s);
        when(bookRepo.findById(4L)).thenReturn(Optional.of(b));
        assertEquals(studentService.findBookById(1L,4L),b);
    }

    @Test
    void test_findBookByIdErrors(){


        Book b = new Book(4L,"book-name",1);
        Student s = new Student(1L,"uduwduh","sdtwfdt","bwdbwddu","wbduwdudbu",23);
        b.setStudent(s);

        when(bookRepo.findById(4L)).thenReturn(Optional.of(b));

        assertThrows(WrongIDException.class,()->{

            studentService.findBookById(2L,4L);

        });

    }


    //nu merge


    @Test
    void test_deleteBook(){

        Book b = new Book(4L,"book-name", 1);
        Student s = new Student(1L,"uduwduh","sdtwfdt","bwdbwddu","wbduwdudbu",23);

       List<Book> bookList = new ArrayList<>();

       bookList.add(b);
       b.setStudent(s);
        when(studentRepo.findById(1L)).thenReturn(Optional.of(s));
        when(bookRepo.findById(4L)).thenReturn(Optional.of(b));

        studentService.deleteBookFromStudentList(1L,4L);

        then(s).should().removeBook(4L);
        then(studentRepo).should().save(s);

        assertEquals(bookList.size(),0);

    }




    @Test
    void test_addBook(){

        Student s = new Student("uduwduh","sdtwfdt","bwdbwddu","wbduwdudbu",23);
        s.setId(1L);
        Book b = new Book("book-name", 1);
        b.setId(4L);

        given(studentRepo.findById(s.getId())).willReturn(Optional.of(s));
        given(bookRepo.findById(b.getId())).willReturn(Optional.of(b));



        //undertest(addBook)

    }








}