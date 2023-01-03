package org.nocturnum.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String getListLink(Criteria cri) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.getAmount())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }

}
