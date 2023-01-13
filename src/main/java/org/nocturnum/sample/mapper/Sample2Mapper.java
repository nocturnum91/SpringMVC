package org.nocturnum.sample.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {

    @Insert("insert into tb_sample2 (col2) values (#{date})")
    int insertCol2(String data);

}
