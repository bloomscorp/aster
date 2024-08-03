package com.bloomscorp.aster.nverse.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {
    private long maxSize;
    private String[] contentTypes;

    @Override
    public void initialize(@NotNull ValidMultipartFile constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
        this.contentTypes = constraintAnnotation.contentTypes();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        if (file.getSize() > maxSize) {
            return false;
        }

        String fileType = file.getContentType();
        if (contentTypes.length > 0 && fileType != null) {
            for (String contentType : contentTypes) {
                if (fileType.equalsIgnoreCase(contentType)) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }
}

