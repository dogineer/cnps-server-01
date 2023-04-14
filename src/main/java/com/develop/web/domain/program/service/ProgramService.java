package com.develop.web.domain.program.service;


import com.develop.web.domain.program.programDto;

import java.util.List;

public interface ProgramService {

    void add(String name);

    List<programDto> list();

}
