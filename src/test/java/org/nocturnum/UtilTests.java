package org.nocturnum;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class UtilTests {

    @Test
    public void subStringTest() {
        String uploadFileName = "여우_81d694da-1faf-4572-ba80-82a4c198cace";
        log.info(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
        log.info(uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1));
    }

    @Test
    public void checkImgTypeTest() {
        File file = new File("/Users/nocturnum/Dev/upload/tmp/2023/01/14/", "twitter_FmR97A1aMAIr9fD_6371eb07-c3ad-4ead-a332-9a20667a8a87.jpg");
        log.info(file);
        log.info(file.toPath());
        MimetypesFileTypeMap mimeTypeMap = new MimetypesFileTypeMap();
        String contentType = mimeTypeMap.getContentType(file);
        log.info(contentType.startsWith("image"));
    }

    @Test
    public void getOriginalName() {
        String fileName = "(작성예시)_지역보험료·소득월액보험료_소득_정산부과_동의서_5ecd10ca-4b8b-4362-9e9c-ae0b735a23e8.docx";
        String resourceOriginalName = fileName.substring(0, fileName.indexOf(".")).substring(0, fileName.lastIndexOf("_"));
        log.info(resourceOriginalName);

    }

}
