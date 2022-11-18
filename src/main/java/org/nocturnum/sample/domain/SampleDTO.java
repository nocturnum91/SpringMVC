package org.nocturnum.sample.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SampleDTO {

    private String id;
    private String name;
    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
