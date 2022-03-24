package team.foobar.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class Responser <T> {
    public static Integer GOOD = 200;
    public static Integer SERVER_ERROR = 500;
    public static Integer CLIENT_ERROR = 400;

    private Integer status = GOOD;
    private String message = "";
    private T data = null;
    private List<String> errors = null;

    public Responser<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Responser<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Responser<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Responser<T> setErrors(String error) {
        if(this.errors == null) {
            this.errors = new ArrayList<>();
        }

        errors.add(error);
        return this;
    }
}
