package org.nocturnum.sample.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample1Mapper {

    @Insert("insert into tb_sample1 (col1) values (#{date})")
    int insertCol1(String data);

}
