package ro.mycode.librarymanagerapi.dto;

import java.time.LocalDate;

public class BookDTO {
    private LocalDate created_at;


    public BookDTO( LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
