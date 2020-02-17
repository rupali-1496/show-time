package com.example.showtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.showtime.retrofit.APIInterface;
import com.example.showtime.retrofit.ApiClient;
import com.example.showtime.retrofit.ContentNodes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.txt1);
        apiInterface = ApiClient.getClient().create(APIInterface.class);


        /**
         GET List Resources
         **/
        Call<ContentNodes> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ContentNodes>() {
            @Override
            public void onResponse(Call<ContentNodes> call, Response<ContentNodes> response) {
                Log.d("Main2Activity", response.code() + "");

                Toast.makeText(getApplicationContext(), "hiii: "+response.code(), Toast.LENGTH_SHORT).show();

                String displayResponse = "";

                ContentNodes resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<ContentNodes.Datum> datumList = resource.results;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (ContentNodes.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.list_type + " " + datum.description + "\n";
                }

                responseText.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<ContentNodes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "hiii: ", Toast.LENGTH_SHORT).show();
                call.cancel();
            }


        });
    }
}