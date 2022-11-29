package org.nocturnum.blog.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private final int startPage;
    private int endPage;
    private final boolean prev;
    private final boolean next;

    private final int total;
    private final Criteria cri;

    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        //페이징의 끝 번호
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        //페이징의 시작 번호
        this.startPage = this.endPage - 9;

        //total을 이용한 endPage 재계산
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;

    }

}
