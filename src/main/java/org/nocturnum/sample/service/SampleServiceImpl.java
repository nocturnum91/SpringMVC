package org.nocturnum.sample.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.sample.mapper.SampleMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j
public class SampleServiceImpl implements SampleService {

    private SampleMapper sampleMapper;

    @Override
    public String getTime() {
        return sampleMapper.getTime();
    }

}
