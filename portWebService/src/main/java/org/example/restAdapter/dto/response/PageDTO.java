package org.example.restAdapter.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    private Integer totalPages;
    private Long totalElements;
    private Long pageIndex;
    private Long pageSize;
    private List<T> content;

    public PageDTO(List<T> content, Long totalElements, Long pageIndex, Long pageSize) {
        this.totalElements = totalElements;
        this.totalPages = Math.round(totalElements/pageSize);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.content = content;
    }

}
