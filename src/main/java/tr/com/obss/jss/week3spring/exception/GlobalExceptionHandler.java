package tr.com.obss.jss.week3spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGLER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleRundtimeException(HttpServletRequest request, Throwable t) {
        LOGGLER.error(t.getMessage(), t);
        Map<String, String> map = new HashMap<>();
        map.put("error", t.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> handleRundtimeException(HttpServletRequest request, ArithmeticException t) {
        LOGGLER.error(t.getMessage(), t);
        Map<String, String> map = new HashMap<>();
        map.put("error", "bilinmeyen bir hata oldu");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumantException(HttpServletRequest request, IllegalArgumentException t) {
        LOGGLER.error(t.getMessage(), t);
        Map<String, String> map = new HashMap<>();
        map.put("error", t.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
