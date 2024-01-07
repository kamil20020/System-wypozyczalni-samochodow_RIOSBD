package pwr.lab.lab7_projekt;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CarsApi {

    private static final String url = "http://192.168.0.139:9200/cars";

    public static void getAll(Consumer<Car[]> callback) {

        Request request = new Request.Builder()
                .url(url)
                .build(); // defaults to GET

        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                Response response = MapperAndClient.getOkHttpClient().newCall(request).execute();
                Car[] cars = MapperAndClient.getObjectMapper().readValue(response.body().string(), Car[].class);
                callback.accept(cars);
                response.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void create(Car car, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String carStr = MapperAndClient.getObjectMapper().writeValueAsString(car);
        RequestBody body = RequestBody.create(JSON, carStr);

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

    public static void update(Integer carId, Car car, Runnable callback) throws JsonProcessingException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String carStr = MapperAndClient.getObjectMapper().writeValueAsString(car);
        RequestBody body = RequestBody.create(JSON, carStr);

        Request request = new Request.Builder()
                .url(url + "/" + carId)
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

    public static void delete(Integer carId, Runnable callback) {

        Request request = new Request.Builder()
                .url(url + "/" + carId)
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
