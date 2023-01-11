package org.nocturnum.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BlogVO {

    private Long bno;
    private String title;
    private String content;
    private Date regDate;
    private Date updateDate;

    private int replyCount;

}
