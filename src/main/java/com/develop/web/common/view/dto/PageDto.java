package com.develop.web.common.view.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
@Getter
public class PageDto {
    private int total;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;
    private CriteriaDto criteriaDto;

    public PageDto() {

    }

//    public PageDto(int total, int pageCount) {
//        this.total = total;
//        this.pageCount = pageCount;
//    }

    public PageDto(int total, int pageCount, CriteriaDto criteriaDto) {
        this.total = total;
        this.criteriaDto = criteriaDto;
        this.pageCount = pageCount;

        this.endPage = (int) (Math.ceil(criteriaDto.getPage() * 1.0 / pageCount)) * pageCount;
        this.startPage = endPage - (pageCount - 1);

        realEnd = (int) (Math.ceil(total * 1.0 / criteriaDto.getLimit()));

        if (endPage > realEnd) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }

        prev = startPage > 1;
        next = endPage < realEnd;
    }
}
