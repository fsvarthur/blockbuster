package com.example.blockbuster.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    public List<ErroDeReqDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeReqDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeReqDto erro = new ErroDeReqDto(e.getField(), message);
            dto.add(erro);
        });
        return dto;
    }
}
