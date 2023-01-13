package org.nocturnum.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.sample.service.SampleTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class SampleTxServiceTests {

    @Setter(onMethod_ = @Autowired)
    private SampleTxService service;

    @Test
    public void testLong() {
        String str = "AAAAAAAAAA\r\n" +
                "BBBBBBBBBB\r\n" +
                "CCCCCCCCCC\r\n" +
                "DDDDDDDDDD\r\n" +
                "EEEEEEEEEE\r\n" +
                "FFFFFFFFFF\r\n";

        log.info(str.getBytes().length);

        service.addData(str);
    }
}
