package org.nocturnum.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

    private int replyCnt;
    private List<ReplyVO> list;

}
