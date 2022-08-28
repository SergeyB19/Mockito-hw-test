package pro.sky.java.course2.mockitohwtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE, reason = "Переполнение массива")
public class EmployeeStorageIsFullException extends RuntimeException{
}