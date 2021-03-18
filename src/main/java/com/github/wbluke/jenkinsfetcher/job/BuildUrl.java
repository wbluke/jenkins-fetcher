package com.github.wbluke.jenkinsfetcher.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class BuildUrl {

    private String url;
    private long number;

}
