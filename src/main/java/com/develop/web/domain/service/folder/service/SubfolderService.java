package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.FolderDetailsDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubfolderService {
    private final FolderMapper folderMapper;

    public List<FolderDetailsDto> findSubfolder(Integer num){
        return folderMapper.selectSubfolderList(num);
    }
}
