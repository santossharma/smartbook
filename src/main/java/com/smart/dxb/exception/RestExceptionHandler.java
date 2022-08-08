package com.smart.dxb.exception;

import com.smart.dxb.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(ApplicationException ex) {
        return ResponseEntity.status(ex.getStatus() )
                .body(ErrorDTO.builder()
                        .message(ex.getMessage() )
                        .build());
    }
}
