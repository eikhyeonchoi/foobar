package team.foobar.config.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.foobar.dto.Responser;
import team.foobar.exception.AuthFailException;
import team.foobar.exception.DuplicateObjectException;
import team.foobar.exception.ObjectNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ObjectNotFoundException.class,
            AuthFailException.class,
            DuplicateObjectException.class,
            MissingServletRequestParameterException.class,
    })
    public Responser<Object> clientException(Exception e) {
        return new Responser<Object>().setStatus(Responser.CLIENT_ERROR).setMessage(e.getMessage());
    }

    /**
     * 마지막 처리 exception
     */
    @ExceptionHandler
    public Responser<Object> defaultHandle(Exception e) {
        return new Responser<Object>().setStatus(Responser.SERVER_ERROR).setMessage(e.getMessage());
    }
}
