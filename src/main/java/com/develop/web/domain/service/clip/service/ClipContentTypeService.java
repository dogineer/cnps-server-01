package com.develop.web.domain.service.clip.service;

import org.springframework.stereotype.Service;

@Service
public class ClipContentTypeService {
    public String findMediaFileContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        switch (extension) {
            case "mp4":
                return "video/mp4";
            case "mov":
                return "video/quicktime";
            default:
                return "application/octet-stream";
        }
    }
}
