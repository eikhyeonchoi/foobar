package team.foobar.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileManager {
    @Value("${file.dir}")
    private String fileDir;

    public String storeFile(MultipartFile file) throws JsonProcessingException {
        long size = file.getSize();
        String contentType = file.getContentType();
        String originName = file.getOriginalFilename();
        String ext = this.extractExt(file.getOriginalFilename());
        String newName = UUID.randomUUID().toString() + "." + ext;

        System.out.println("newName = " + newName);

        try {
            String fullPath = fileDir + newName;
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new ObjectMapper().writeValueAsString(new FileContext(originName, newName, ext, fileDir, contentType, size));
    }

    private String extractExt(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    @Data
    static class FileContext {
        private String originName;
        private String newName;
        private String ext;
        private String storePath;
        private String contentType;
        private Long size;



        public FileContext(String originName, String newName, String ext, String storePath, String contentType, Long size) {
            this.originName = originName;
            this.newName = newName;
            this.ext = ext;
            this.storePath = storePath;
            this.contentType = contentType;
            this.size = size;
        }
    }
}
