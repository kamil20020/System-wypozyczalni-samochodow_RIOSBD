package pwr.lab.lab7_projekt;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CarClientApi {
    private static final String url = "http://192.168.0.139:9200/cars-clients";

    public static void getAll(Consumer<CarClient[]> callback) {

        Request request = new Request.Builder()
                .url(url)
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                CarClient[] carsClients = MapperAndClient.getObjectMapper().readValue(response.body().string(), CarClient[].class);
                callback.accept(carsClients);
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void create(CarClientCreate carClient, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String carClientStr = MapperAndClient.getObjectMapper().writeValueAsString(carClient);
        RequestBody body = RequestBody.create(JSON, carClientStr);

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

    public static void update(Integer carClientId, CarClientUpdate carClient, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String carClientStr = MapperAndClient.getObjectMapper().writeValueAsString(carClient);
        RequestBody body = RequestBody.create(JSON, carClientStr);

        Request request = new Request.Builder()
                .url(url + "/" + carClientId)
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

    public static void delete(Integer carClientId, Runnable callback) {

        Request request = new Request.Builder()
                .url(url + "/" + carClientId)
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

    public static void getRentalsByClientCode(Integer clientCode, Consumer<ClientRental[]> callback) {

        Request request = new Request.Builder()
                .url(url + "/client-code/" + clientCode)
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                ClientRental[] clientsRentals = MapperAndClient.getObjectMapper().readValue(response.body().string(), ClientRental[].class);
                callback.accept(clientsRentals);
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
