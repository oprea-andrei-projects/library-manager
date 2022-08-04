package ro.mycode.librarymanagerapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongIDException extends RuntimeException{

    public WrongIDException(String str){

        super(str);
    }
}
