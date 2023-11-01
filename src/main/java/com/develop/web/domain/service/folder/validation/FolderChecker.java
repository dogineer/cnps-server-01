package com.develop.web.domain.service.folder.validation;

import com.develop.web.domain.service.folder.mapper.FolderMapper;
import com.develop.web.global.exception.code.FolderErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FolderChecker {
    private final FolderMapper folderMapper;

    public void subfolderCheck(Integer folderId) throws CustomException {
        if(folderMapper.selectSubfolderCheck(folderId)){
            throw new CustomException(FolderErrorCode.EXISTENCE_FOLDER);
        }
    }
}
