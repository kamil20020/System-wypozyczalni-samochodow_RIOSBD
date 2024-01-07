package pwr.lab.lab7_projekt;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClientsApi {

    private static final String url = "http://192.168.0.139:9200/clients";

    public static void getAll(Consumer<Client[]> callback) {

        Request request = new Request.Builder()
                .url(url)
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                Client[] clients = MapperAndClient.getObjectMapper().readValue(response.body().string(), Client[].class);
                callback.accept(clients);
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void create(Client client, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String clientStr = MapperAndClient.getObjectMapper().writeValueAsString(client);
        RequestBody body = RequestBody.create(JSON, clientStr);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                System.out.println(response.body().string());
                callback.run();
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void update(Integer clientId, Client client, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String clientStr = MapperAndClient.getObjectMapper().writeValueAsString(client);
        RequestBody body = RequestBody.create(JSON, clientStr);

        Request request = new Request.Builder()
                .url(url + "/" + clientId)
                .put(body)
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                callback.run();
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void delete(Integer clientId, Runnable callback) {

        Request request = new Request.Builder()
                .url(url + "/" + clientId)
                .delete()
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                callback.run();
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
