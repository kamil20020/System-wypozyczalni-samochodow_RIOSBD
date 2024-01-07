package pwr.lab.lab7_projekt;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

public class MapperAndClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
}
