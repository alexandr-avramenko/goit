package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.models.Comments;
import org.example.models.Post;
import org.example.models.Task;
import org.example.models.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HttpRequestTest {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com";

    ObjectMapper objectMapper = new ObjectMapper();
    HttpClient client = HttpClient.newHttpClient();
    HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();

    public Optional<String> getUsers() throws IOException, InterruptedException {
        return getString(URI.create(BASE_URL + "/users"));
    }
    public Optional<String> getUser(int id) throws IOException, InterruptedException {
        return getString(URI.create(BASE_URL + "/users/" + id));
    }

    public Optional<String> getUser(String userName) throws IOException, InterruptedException {
        return getString(URI.create(BASE_URL + "/users?username=" + userName));
    }

    private Optional<String> getString(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);

        if (HttpURLConnection.HTTP_OK == response.statusCode()) {
            return Optional.ofNullable(response.body());
        }
        return Optional.empty();
    }

    public Optional<String> createUser(User userData) throws IOException, InterruptedException {

        String requestBody = objectMapper.writeValueAsString(userData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);

        String responseBody = response.body();
        if (HttpURLConnection.HTTP_CREATED == response.statusCode()) {
            return Optional.ofNullable(responseBody);
        }
        return Optional.empty();
    }

    public Optional<String> updateUser(User userData, int userId) throws IOException, InterruptedException {

        String requestBody = objectMapper.writeValueAsString(userData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);

        String responseBody = response.body();
        if (HttpURLConnection.HTTP_OK == response.statusCode()) {
            return Optional.ofNullable(responseBody);
        }

        return Optional.empty();
    }

    public int deleteUser(int userId) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);
        return response.statusCode();
    }

    public void getComments(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId + "/posts"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);

        TypeReference<List<Post>> valueTypeRef = new TypeReference<>() {};

        List<Post> posts = objectMapper.readValue(response.body(), valueTypeRef);
        Optional<Post> post = posts.stream().max(Comparator.comparingInt(Post::getId));
        Integer maxPost = post.map(Post::getId).orElse(null);

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + maxPost + "/comments"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response2 = client.send(request2, responseBodyHandler);

        Comments[] comments = objectMapper.readValue(response2.body(), Comments[].class);
        Path filePath = Paths.get("/Users/oavramenko/Documents/module_13/src", "user-" + userId + "-post-" + maxPost + "-comments.json");

        List<String> bodies = Stream.of(comments)
                .map(Comments::getBody)
                .collect(Collectors.toList());
        String jsonObj = objectMapper.writeValueAsString(bodies);
        Files.writeString(filePath, jsonObj, StandardOpenOption.CREATE);
    }

    public void getToDoTask(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + userId + "/todos"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, responseBodyHandler);

        Task[] tasks = objectMapper.readValue(response.body(), Task[].class);

        List<Task> notCompletedTask = Stream.of(tasks)
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
        System.out.println(notCompletedTask);
    }
}
