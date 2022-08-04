package ro.mycode.librarymanagerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MailOrPassException extends RuntimeException{

    public MailOrPassException(String str){

        super(str);
    }


}
