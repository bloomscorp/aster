package com.bloomscorp.aster.fileupload.service;

import com.bloomscorp.aster.support.AsterUtility;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.hastar.CarrierException;
import com.bloomscorp.hastar.code.ActionCode;
import com.bloomscorp.pastebox.Pastebox;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AsterS3StorageManagerService extends StorageManagerService {

    private final S3Client s3Client;
    private final String s3Bucket;

    public AsterS3StorageManagerService(S3Client s3Client, String s3Bucket) {
        this.s3Client = s3Client;
        this.s3Bucket = s3Bucket;
    }

    @Override
    public String uploadImage(MultipartFile file, String basePath, String fileName) {
        try {

            if (file == null) {
                return "";
            }
            
            if (fileName == null || fileName.isEmpty()){
                fileName = AsterUtility
                    .buildImageFileName(
                        Objects.requireNonNull(file.getOriginalFilename())
                    );
            }
            
            if(!basePath.isBlank()){
                if (!basePath.endsWith("/")) {
                    basePath += "/";
                }
            }
            
            HashMap<String, String> fileMetaData = extractMetaData(file);

            PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Bucket)
                .key(basePath + fileName)
                .contentType(fileMetaData.get("Content-Type"))
                .acl(Constant.AWS_S3_ACL)
                .metadata(fileMetaData)
                .build();

            this.s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            GetUrlRequest getUrlRequest = GetUrlRequest
                .builder()
                .bucket(s3Bucket)
                .key(basePath + fileName)
                .build();

            return this.s3Client.utilities().getUrl(getUrlRequest).toExternalForm();

        } catch (IOException e) {
            throw new CarrierException(e, ActionCode.INSERT_FAILURE);
        }
    }

    @Override
    protected void deleteImage(String fileUrl) {
        try {

            String fileName = AsterUtility.extractFileNameFromUrl(fileUrl);

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(s3Bucket)
                .key(fileName)
                .build();

            s3Client.deleteObject(deleteObjectRequest);

        } catch (S3Exception e) {
            throw new CarrierException(e, ActionCode.DELETE_FAILURE);
        }
    }

    @Override
    public void initiateDeleteImageTask(String fileUrl) {

        if (Pastebox.isEmptyString(fileUrl)) return;

        new Thread(() -> {
            this.deleteImage(fileUrl);
        }).start();
    }

    public void initiateDeleteImageTask(@NotNull List<String> fileUrls) {
        fileUrls.forEach(this::initiateDeleteImageTask);
    }
}
