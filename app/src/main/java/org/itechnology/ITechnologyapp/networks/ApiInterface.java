package org.itechnology.ITechnologyapp.networks;

import org.itechnology.ITechnologyapp.models.CategoriesResponseModel;
import org.itechnology.ITechnologyapp.models.UserModel;
import org.itechnology.ITechnologyapp.models.UserResponseModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //Login
    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponseModel> login(@Field("email") String email
            , @Field("password") String password);

    //select the categories
    @GET("select_categories.php")
    Observable<CategoriesResponseModel> getCategory();

}

