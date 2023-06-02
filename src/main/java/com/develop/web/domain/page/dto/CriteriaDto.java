package com.develop.web.domain.page.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
@ToString
public class CriteriaDto {
    private final int page;
    private final int limit;
    private final int offset;

    public CriteriaDto() {
        this(1, 10);
    }

    public CriteriaDto(int page, int limit){
        this.page = page;
        this.limit = limit;
        this.offset = (getPage() - 1) * limit;
    }

    public String getListLink(int page){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create("/user/ingest"))
            .queryParam("page", page)
            .queryParam("limit", limit);

        return builder.toUriString();
    }
}
