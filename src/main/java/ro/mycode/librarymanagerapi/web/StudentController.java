package ro.mycode.librarymanagerapi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.librarymanagerapi.dto.BookDTO;
import ro.mycode.librarymanagerapi.dto.StudentDTO;
import ro.mycode.librarymanagerapi.exceptions.MailOrPassException;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;
import ro.mycode.librarymanagerapi.services.StudentService;

import java.util.List;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }




//
//    @PostMapping("/addABook/{id}")
//    public ResponseEntity<Book> addDasBook(@PathVariable Long id, @RequestBody Book book){
//
//        this.studentService.addBookToStudent(id,book);
//
//        return new ResponseEntity<>(book, HttpStatus.CREATED);
//
//    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student s){

        this.studentService.addAstudent(s);

        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }

    @GetMapping("/getStudentByID/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable Long id){

        Student s = this.studentService.getStudentByTheID(id);

        return  new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/deleteBook")
    public ResponseEntity<Long> deleteTheBook(@RequestParam Long sId,  @RequestParam Long bId ){

      this.studentService.deleteBookFromStudentList(sId,bId);

        return new ResponseEntity<>(bId,HttpStatus.ACCEPTED);

    }




   @PostMapping("/login")
    public ResponseEntity<Student> loginData(@RequestBody StudentDTO studentDTO){

        Student s = this.studentService.login(studentDTO);

       return new ResponseEntity<>(s, HttpStatus.ACCEPTED);

   }

   @GetMapping("/getThisStudentBooks/{id}")
    public ResponseEntity<List<Book>> getStudentBooks (@PathVariable Long id){

        return new ResponseEntity<>(this.studentService.getStudentBooks(id), HttpStatus.ACCEPTED);

   }

   @PostMapping("/addBookToStudent/{studentID}")
    public ResponseEntity<Book> addBook(@PathVariable Long studentID, @RequestBody Book b){
        this.studentService.addTheBook(studentID,b);
        return new ResponseEntity<>(b, HttpStatus.CREATED);
   }

   @GetMapping("/getBookByid/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable Long id){

        Book b = this.studentService.getTheBookByID(id);

        return new ResponseEntity<>(b, HttpStatus.ACCEPTED);

   }

   @PutMapping("/updateBook/{stdID}")
    public ResponseEntity<Book> updateBook(@PathVariable Long stdID, @RequestBody Book book1){

       Book b =  this.studentService.updateBook(stdID,book1);

        return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
   }






}
