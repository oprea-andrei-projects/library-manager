package ro.mycode.librarymanagerapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongBookException extends RuntimeException{

    public WrongBookException (String msg){

        super(msg);
    }
}
