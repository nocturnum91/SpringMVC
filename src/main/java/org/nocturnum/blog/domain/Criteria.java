package org.nocturnum.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    private int skipCount;

    private String keyword;

    private Long preBno;

    private Long nextBno;

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skipCount = (pageNum - 1) * amount;
    }

    public int getSkipCount() {
        return (this.pageNum - 1) * this.amount;
    }

}
