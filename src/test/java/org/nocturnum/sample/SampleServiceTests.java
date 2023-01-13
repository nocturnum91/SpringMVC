package org.nocturnum.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class SampleServiceTests {

    @Setter(onMethod_ = @Autowired)
    private SampleService sampleService;

    @Test
    public void testGetTime() {
        log.info(sampleService.getTime());
    }

    @Test
    public void testClass() {
        log.info(sampleService);
        log.info(sampleService.getClass().getName());
        // INFO - [SpringMVC.SampleServiceTests.testClass(27)] | org.nocturnum.sample.service.SampleServiceImpl@20a8a64e
        // INFO - [SpringMVC.SampleServiceTests.testClass(28)] | com.sun.proxy.$Proxy31
    }

    @Test
    public void testAdd() throws Exception {
        log.info(sampleService.doAdd("123", "456"));
    }

    @Test
    public void testAddError() throws Exception {
        log.info(sampleService.doAdd("123", "ABC"));
    }


}
