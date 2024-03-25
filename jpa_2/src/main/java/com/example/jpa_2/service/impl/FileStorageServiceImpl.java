package com.example.jpa_2.service.impl;

import com.example.jpa_2.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//được quản lý bởi container Spring và
// có thể được inject vào các thành phần khác của ứng dụng.
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private ServletContext context;
    // ServletContext là một interface trong Servlet API được sử dụng để tương tác
    // với môi trường thực thi của ứng dụng web
    // (ví dụ: thư mục lưu trữ tạm thời, thông tin về ứng dụng, v.v.).
    public FileStorageServiceImpl(ServletContext context) {
        this.context = context;
    }

    @Override
    public String upload(MultipartFile file) {
        String uploadFolder = "/uploads/";      // khai báo đường dẫn thư mục lưu trữ tệp
        String path = context.getRealPath("/WEB-INF/") + uploadFolder;
        File folder = new File(path);
        if (!folder.exists()) folder.mkdir();        // mkdir() khởi tạo 1 thư mục
        if (!file.isEmpty()) {
            Path fileUpload = Paths.get(path + file.getOriginalFilename());
            // System.out.println(file.getOriginalFilename());
            // z4786726587759_07b31aad7c6f1cf2fa351cdaaa223978.jpg
            try {
                Files.write(fileUpload, file.getBytes());
                return uploadFolder + file.getOriginalFilename();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}

