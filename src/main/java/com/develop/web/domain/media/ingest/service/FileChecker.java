package com.develop.web.domain.media.ingest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileChecker {
    public void fileNull(Resource data){

        if (data == null){
            throw new NullPointerException("파일 없음");
        }
    }
}
