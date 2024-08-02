package com.bloomscorp.aster;

import com.bloomscorp.bsb.BmxGlobalExceptionHandler;
import com.bloomscorp.raintree.RainTreeResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

public class AsterGlobalExceptionHandler extends BmxGlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({BindException.class})
    public RainTreeResponse methodArgumentNotValidException(
            @NotNull BindException ex,
            WebRequest request
    ) {

        //TODO: set this in the response
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new RainTreeResponse(false, "Incorrect information.");
    }
}
