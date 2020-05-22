package com.odds.springtestsdemo.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RepositoryService {

    @Value("${github.base_api.url}")
    String gitHubApiUrl;

    @Autowired
    RestTemplate restTemplate;

    public RepositoryModel getRepository(String user) throws JSONException {
        RepositoryModel repository = new RepositoryModel();

        String URL = gitHubApiUrl + "/users/" + user + "/repos";

        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        JSONArray responseFromGitHub = new JSONArray(response.getBody());
        if (responseFromGitHub.length() == 0) {
            return repository;
        }

        JSONObject repo = new JSONObject(responseFromGitHub.get(0).toString());
        repository.setId(repo.getInt("id"));
        repository.setName(repo.getString("name"));
        repository.setUrl(repo.getString("url"));

        return repository;
    }
}
