package ro.mycode.librarymanagerapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.librarymanagerapi.model.Book;
import ro.mycode.librarymanagerapi.model.Student;

import java.util.Map;


@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.id = ?1")
    public Student getThisStudent(Long id);

    @Query("select s from Student s where s.email =?1 and s.password = ?2")
    public Student getStudentByCredentials(String mail, String password);

    @Query("select s from Student s where s.first_name = ?1 and s.last_name = ?2")
    Student findStudentByName(String first_name, String last_name);










}
