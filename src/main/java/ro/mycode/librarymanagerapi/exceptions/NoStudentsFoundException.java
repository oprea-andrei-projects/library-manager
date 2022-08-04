package ro.mycode.librarymanagerapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoStudentsFoundException extends RuntimeException{

    public NoStudentsFoundException(String msg){

        super(msg);
    }
}
