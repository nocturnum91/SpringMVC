package org.nocturnum.sample.domain;

import lombok.Data;

@Data
public class AttachFileDTO {

    private String fileName;
    private String extension;
    private String uploadPath;
    private String uuid;
    private boolean image;

}
