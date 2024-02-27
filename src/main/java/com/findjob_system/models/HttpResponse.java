package com.findjob_system.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    protected String timesStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message ;
    protected String DeveloperMessage;
    protected String path;
    private int totalPages;
    private long totalElements;
    protected String requestMethod;
    protected Map<?,?> data;

}
