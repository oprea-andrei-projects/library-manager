package ro.mycode.librarymanagerapi.services;


import org.springframework.stereotype.Service;
import ro.mycode.librarymanagerapi.dto.StudentDTO;
import ro.mycode.librarymanagerapi.exceptions.*;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;
import ro.mycode.librarymanagerapi.repo.BookRepo;
import ro.mycode.librarymanagerapi.repo.StudentRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    StudentRepo studentRepo;
    BookRepo bookRepo;



    public StudentService (StudentRepo studentRepo, BookRepo bookRepo){

        this.studentRepo = studentRepo;

        this.bookRepo = bookRepo;
    }

    public List<Student> getAllStudents(){

        List<Student> students= this.studentRepo.findAll();
        if(students.size()==0){
            throw new NoStudentsFoundException("Nu sunt studenti de gasit !!!");
        }
        return students;

    }

    public boolean addAstudent(Student s){

       Student myStudent = this.studentRepo.findStudentByName(s.getFirst_name(),s.getLast_name());

        if (myStudent == null){

            this.studentRepo.save(s);

            return true;

        }else {

            throw new StudentExistsException("Student already in list !!!");


        }


    }

    public Student getStudentByTheID(Long id){

        Optional<Student> check = this.studentRepo.findById(id);

        if(check.isEmpty()){

            throw new WrongIDException("ID-ul introdus nu exista !");

        }

        return this.studentRepo.getThisStudent(id);
    }

    public Book findBookById(Long stdID,Long id){

        boolean present = bookRepo.findById(id).isPresent();
        if(present){
            Book book = bookRepo.findById(id).get();
            if(book.getStudent().getId()==stdID){
                return book;

             }else{
                throw new WrongIDException("student id not correct !!!");
            }
        }else{
            throw new WrongIDException("book id not correct !!!");
        }
    }

    public void deleteBookFromStudentList(Long student_Id,Long idBook){

       Optional<Student> s = this.studentRepo.findById(student_Id);

        if(s.isPresent()){
            s.get().removeBook(idBook);
            studentRepo.save(s.get());

        }else{

            throw new WrongIDException("ID-ul nu este corect !");

        }

    }

    public Student login(StudentDTO studentDTO){

       Student s = this.studentRepo.getStudentByCredentials(studentDTO.getEmail(), studentDTO.getPassword());

        if(s==null){

            throw new MailOrPassException("Mail or Pass not correct !");
        }else{

            return s;
        }

    }

    public List<Book> getStudentBooks(Long id){

        return this.studentRepo.findById(id).get().getBookList();
    }

    public boolean addTheBook (Long student_Id, Book b){

        Optional<Student> studentCheck = Optional.of(getStudentByTheID(student_Id));

        if(studentCheck.isEmpty()) {

            throw new WrongIDException("ID-ul studentului nu este corect !!!");

        }else if(Collections.frequency(studentCheck.get().getBookList(),b)!=0){

            throw new WrongBookException("Cartea se afla deja in posesia studentului !!!");

        }
        else{
            studentCheck.get().addBook(b);
            this.studentRepo.save(studentCheck.get());

            return true;
        }


    }

    public Book getTheBookByID(Long bookID){

        return bookRepo.findById(bookID).get();


    }

    public Book updateBook(Long stdID,Book book){

        Book book1 = findBookById(stdID, book.getId());

        book1.setBook_name(book.getBook_name());
        book1.setCreated_at(book.getCreated_at());

        this.bookRepo.save(book1);

        return book1;

    }
















}
