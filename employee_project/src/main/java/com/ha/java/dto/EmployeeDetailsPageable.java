package com.ha.java.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailsPageable {

    private List<Employee> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public EmployeeDetailsPageable() {
    }

    public EmployeeDetailsPageable(List<Employee> content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}
