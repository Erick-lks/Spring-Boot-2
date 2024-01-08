package academy.devdojo.caetano.springbootacademy.exception;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;



@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}
