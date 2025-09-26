package com.exam.service.Impl;

import com.exam.dto.GeneralResponseDto;
import com.exam.service.DmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class DmsServiceImpl implements DmsService {

    private static final String UPLOAD_DIR = "C:/Hritik/Uploaded-Docs";

    private final com.example.document_management_service.DmsServiceGrpc.DmsServiceBlockingStub dmsServiceStub;

    public DmsServiceImpl(com.example.document_management_service.DmsServiceGrpc.DmsServiceBlockingStub dmsServiceStub) {
        this.dmsServiceStub = dmsServiceStub;
    }

    @Override
    public ResponseEntity<GeneralResponseDto> uploadDocument(MultipartFile file) {

        GeneralResponseDto response = new GeneralResponseDto();
        try {
            // Convert file to Base64
            String base64Content = Base64.getEncoder().encodeToString(file.getBytes());

            // gRPC request
            com.example.document_management_service.DmsProto.DocumentUploadRequest grpcRequest = com.example.document_management_service.DmsProto.DocumentUploadRequest.newBuilder()
                    .setOriginalDocumentName(file.getOriginalFilename())
                    .setDocumentPath(UPLOAD_DIR)
                    .setDocumentSize(String.valueOf(file.getSize()))
                    .setDocumentType(file.getContentType())
                    .setDocumentBase64(base64Content)
                    .build();

            // Call gRPC service
            com.example.document_management_service.DmsProto.DocumentUploadResponse grpcResponse = dmsServiceStub.uploadDocument(grpcRequest);
            // Response
            Map<String,Object> docUuid = new HashMap<>();
            docUuid.put("docUuid",grpcResponse.getDocUuid());
            response.setMessage(grpcResponse.getMessage());
            response.setStatusCode(grpcResponse.getStatusCode());
            response.setData(docUuid);
            response.setStatus(true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("File upload failed");
            response.setStatusCode(500);
            response.setStatus(false);
            return ResponseEntity.ok(response);
        }
    }
}
