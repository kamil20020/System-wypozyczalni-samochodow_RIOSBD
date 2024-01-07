package pwr.lab.lab7_projekt;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public void testRequest() throws IOException {



        Client client = new Client(
                "Tomasz", "Nowak", "tomasz.nowak@wp.pl", 605123852, "1935-11-12T23:28:45.462449776Z"
        );

        Car car = new Car(
                "xzxzx", "wewaa", "aaaz", "cscs", "DW23232t", 114, null
        );

//        CarClientCreate carClient = new CarClientCreate(
//                "2023-07-01T21:27:45.857Z", "2023-08-01T21:27:45.857Z", 4, 6
//        );

        CarClientApi.getRentalsByClientCode(136354433, (rentals) ->{
            System.out.println(Arrays.asList(rentals));
            //System.out.println(rentals);
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Test:" , Toast.LENGTH_SHORT).show();

        try {
            testRequest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
