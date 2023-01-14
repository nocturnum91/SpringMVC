package org.nocturnum;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class UtilTests {

    @Test
    public void subStringTest() {
        String uploadFileName = "twitter_FmR97A1aMAIr9fD.jpg";
//            String extension =
        log.info(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
        log.info(uploadFileName.substring(uploadFileName.lastIndexOf(".")+1));
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

}
