package org.sejonguniv.if_2020.ui.user.attendance;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceViewModel extends BaseViewModel {

    public void sendUserAttendance(Attendee attendee){

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Call<Void> request = service.insertAttendee(attendee);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() != null) {
                    Log.e("Success insert", "!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail insert", t.toString());
            }
        });

    }

}
