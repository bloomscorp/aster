package com.bloomscorp.aster.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public abstract class StorageManagerService {

    protected final HashMap<String, String> extractMetaData(MultipartFile file) {

        HashMap<String, String> metadata = new HashMap<>();

        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        return metadata;
    }

    public abstract String uploadImage(MultipartFile file);

    protected abstract void deleteImage(String fileUrl);

    public abstract void initiateDeleteImageTask(String fileUrl);
}
