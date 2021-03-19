package com.wbluke.fetcher;

import com.wbluke.fetcher.job.JobItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
public class JenkinsJobFetcher {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private String api;
    private String token;
    private RestTemplate restTemplate = new RestTemplate();

    public JenkinsJobFetcher(String api, String token) {
        this.api = api;
        this.token = token;
    }

    public JobItem fetchJob(String jobName) {
        CompletableFuture<JobItem> completableFuture = CompletableFuture.supplyAsync(() -> {
            String requestUrl = templateUrl(jobName) + "/api/json";
            log.info("requestUrl = " + requestUrl);

            return restTemplate
                    .exchange(requestUrl, GET, new HttpEntity<>("", createAuthHeaders()), JobItem.class)
                    .getBody();
        }, executorService);

        return completableFuture.join();
    }

    private String templateUrl(String jobName) {
        return api + "/job/" + jobName;
    }

    private HttpHeaders createAuthHeaders() {
        String credentials = "wbluke" + ":" + token;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders() {
        };
        httpHeaders.set("Authorization", "Basic " + base64Credentials);
        return httpHeaders;
    }

}
