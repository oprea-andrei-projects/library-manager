package ro.mycode.librarymanagerapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="Book")
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {


    @Id
    @SequenceGenerator(

            name = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
   Long id;

    @Column(

            name = "book_name",
            nullable = false,
            columnDefinition = "TEXT"

    )

    @NotBlank(message = "Name is required")

   String book_name;

    @Column(

            name = "created_at",
            nullable = false
    )

    //LocalDate created_at;
    int created_at;

//    public Book(String book_name, LocalDate created_at){
//        this.book_name = book_name;
//        this.created_at = created_at;
//    }

    public Book(String book_name, int created_at){
        this.book_name = book_name;
        this.created_at = created_at;
    }

//    public Book(Long id,String book_name, LocalDate created_at){
//
//        this.id = id;
//        this.book_name = book_name;
//        this.created_at = created_at;
//    }

    public Book(Long id,String book_name, int created_at){

        this.id = id;
        this.book_name = book_name;
        this.created_at = created_at;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name="student_id",
            referencedColumnName="id",
            foreignKey = @ForeignKey(
                    name="student_id_fk"
            )
    )
    private Student student;

//    public Student getStudent() {
//        return student;
//    }

    @Override
    public boolean equals(Object o){

        Book b = (Book) o;

        return this.id==b.id;
    }

    public Book(Long id) {
        this.id = id;
    }
}
