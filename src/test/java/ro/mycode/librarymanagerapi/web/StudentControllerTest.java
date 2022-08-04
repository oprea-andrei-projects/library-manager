package ro.mycode.librarymanagerapi.web;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.mycode.librarymanagerapi.LibraryManagerApiApplication;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;
import ro.mycode.librarymanagerapi.repo.BookRepo;
import ro.mycode.librarymanagerapi.repo.StudentRepo;
import ro.mycode.librarymanagerapi.services.StudentService;


import java.util.*;






@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagerApiApplication.class)
@AutoConfigureMockMvc
class StudentControllerTest {

    @MockBean
    private StudentRepo mockStudentRepo;


    @MockBean
    private BookRepo mockBookRepo;

    @MockBean
    private StudentService mockStudentServices;



   @Autowired
    private MockMvc mock;



    @Test
    void test_getStudentBooks() throws Exception {

        Student s = new Student(1L,"Andrei","Oprea","mail@Mail.com","aaa",22);
        Book b = new Book("bookName", 2000);
        List<Book> bookList =new ArrayList<>();
        bookList.add(b);

        s.setBookList(bookList);

        when(mockStudentRepo.findById(1L)).thenReturn(Optional.of(s));

        ObjectMapper mapper = new ObjectMapper();

        mock.perform(MockMvcRequestBuilders.get("/api/v1/getThisStudentBooks/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string(mapper.writeValueAsString(mapper.writeValueAsString(bookList))));

    }

//
//    @Test
//    void test_addStudent() throws Exception {
//
//        Student s = new Student("Andrei","Oprea","mail@Mail.com","aaa",22);
//        s.setId(1L);
//        when(mockStudentRepo.findStudentByName(s.getFirst_name(),s.getLast_name())).thenReturn(null);
//        mockStudentRepo.save(s);
//        when(mockStudentRepo.findStudentByName(s.getFirst_name(),s.getLast_name())).thenReturn(s);
//        when(mockStudentServices.addAstudent(s)).thenReturn(true);
//        mock.perform(MockMvcRequestBuilders.post("/api/v1/addStudent")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(String.valueOf(asJsonString(s))))
//                .andExpect(status().isCreated());
//    }
//
//
    public static String asJsonString(final Object obj) throws JsonProcessingException {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



}