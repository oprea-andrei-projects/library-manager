package ro.mycode.librarymanagerapi.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.mycode.librarymanagerapi.exceptions.WrongIDException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Student")
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {


    @Id
    @SequenceGenerator(

            name = "studet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;



    @Column(

            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String first_name;


    @Column(

            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )

    String last_name;


    @Column(

            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )

    String email;

    @Column(


            name = "password",
            nullable = false,
            columnDefinition = "TEXT"

    )
    String password;


    @Column(

            name = "age",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    int age;

    public Student(String first_name, String last_name, String email, String password,int age){

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Student(Long id,String first_name, String last_name, String email, String password,int age){

        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            orphanRemoval = true

    )
    private List<Book> bookList= new ArrayList<>();

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book){
         this.bookList.add(book);
         book.setStudent(this);
    }

    public void removeBook(Long id){
       if(this.bookList.remove(new Book(id))){

       }else{

            throw new WrongIDException("ID-ul nu este cotect !");

       }




    }




    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +

                '}';
    }

    @Override
    public boolean equals(Object o){

        Student s = (Student) o;

        return this.first_name.equals(s.first_name) && this.last_name.equals(s.last_name);
    }




}
