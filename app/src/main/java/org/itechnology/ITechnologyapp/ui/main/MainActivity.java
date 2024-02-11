package org.itechnology.ITechnologyapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import org.itechnology.ITechnologyapp.R;
import org.itechnology.ITechnologyapp.databinding.ActivityMainBinding;
import org.itechnology.ITechnologyapp.models.FUtilsValidation;
import org.itechnology.ITechnologyapp.models.NetworkAvailable;
import org.itechnology.ITechnologyapp.models.UserResponseModel;
import org.itechnology.ITechnologyapp.networks.API;
import org.itechnology.ITechnologyapp.ui.categories.CategoriesActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private NetworkAvailable networkAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActivity();
    }

    private void setupActivity() {
        networkAvailable = new NetworkAvailable(this);
        binding.btnLogin.setOnClickListener(this);
    }

    private void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("credentials", MODE_PRIVATE);
        int id = preferences.getInt("id", 0);
        if (id != 0) {
            startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();
        }
    }

    private void login() {
        if (networkAvailable.isNetworkAvailable()) {
            if (!FUtilsValidation.isEmpty(binding.edtEmail, getString(R.string.required))
                    && FUtilsValidation.isEmail(binding.edtEmail, getString(R.string.set_valied_email))
                    && !FUtilsValidation.isEmpty(binding.edtPass, getString(R.string.required))) {
                binding.bar.setVisibility(View.VISIBLE);
                Call<UserResponseModel> call = API.getInstance().login(binding.edtEmail.getText().toString().trim()
                        , binding.edtPass.getText().toString());
                call.enqueue(new Callback<UserResponseModel>() {
                    @Override
                    public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                        binding.bar.setVisibility(View.INVISIBLE);
                        if (response.body() != null) {
                            UserResponseModel result = response.body();
                            if (result.isStatus()) {
                                SharedPreferences preferences = getSharedPreferences("credentials", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("id", result.getOutput().getId());
                                editor.putString("name", result.getOutput().getNameAR());
                                editor.putString("email", result.getOutput().getEmail());
                                editor.putString("password", result.getOutput().getPassword());
                                editor.apply();
                                editor.commit();
                                startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
                                finish();
                            } else {
                                Snackbar.make(binding.getRoot(), getString(R.string.error_login), Snackbar.LENGTH_LONG).show();
                            }
                        } else {
                            Snackbar.make(binding.getRoot(), getString(R.string.error_login), Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponseModel> call, Throwable t) {
                        binding.bar.setVisibility(View.INVISIBLE);
                        Snackbar.make(binding.getRoot(), getString(R.string.error_login), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        } else {
            Snackbar.make(binding.getRoot(), getString(R.string.error_connection), Snackbar.LENGTH_LONG).show();
        }
    }
}