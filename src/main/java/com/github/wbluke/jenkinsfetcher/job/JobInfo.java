package com.github.wbluke.jenkinsfetcher.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class JobInfo {

    private String description;
    private String displayName;
    private String name;
    private boolean buildable;
    private String url;

    private List<BuildUrl> builds;

    private BuildUrl firstBuild;
    private BuildUrl lastBuild;
    private BuildUrl lastCompletedBuild;
    private BuildUrl lastFailedBuild;
    private BuildUrl lastStableBuild;
    private BuildUrl lastSuccessfulBuild;
    private BuildUrl lastUnstableBuild;
    private BuildUrl lastUnsuccessfulBuild;

}
