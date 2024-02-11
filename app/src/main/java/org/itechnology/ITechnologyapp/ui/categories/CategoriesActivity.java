package org.itechnology.ITechnologyapp.ui.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Network;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import org.itechnology.ITechnologyapp.R;
import org.itechnology.ITechnologyapp.adapters.CategoryAdapter;
import org.itechnology.ITechnologyapp.databinding.ActivityCategoriesBinding;
import org.itechnology.ITechnologyapp.models.CategoriesResponseModel;
import org.itechnology.ITechnologyapp.models.CategoryModel;
import org.itechnology.ITechnologyapp.models.NetworkAvailable;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoryAdapter.OnClickListener {
    private ActivityCategoriesBinding binding;
    private NetworkAvailable networkAvailable;
    private ArrayList<CategoryModel> arrayList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private CategoriesViewModel categoriesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActivity();
    }

    private void setupActivity() {
        networkAvailable = new NetworkAvailable(this);
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        buildRecycler();
    }

    private void buildRecycler() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(CategoriesActivity.this, RecyclerView.VERTICAL, false));
        categoryAdapter = new CategoryAdapter(this, arrayList, this);
        binding.recycler.setAdapter(categoryAdapter);
        if (networkAvailable.isNetworkAvailable()) {
            binding.bar.setVisibility(View.VISIBLE);
            categoriesViewModel.selectCategories();
            categoriesViewModel.mutableLiveData.observe(this, new Observer<Object>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onChanged(Object o) {
                    binding.bar.setVisibility(View.INVISIBLE);
                    if (o instanceof Throwable) {
                        Snackbar.make(binding.getRoot(), getString(R.string.error_getting_data), Snackbar.LENGTH_LONG).show();
                    } else {
                        CategoriesResponseModel result = (CategoriesResponseModel) o;
                        arrayList = new ArrayList<>();
                        arrayList.addAll(result.getOutput());
                        categoryAdapter.setList(arrayList);
                        categoryAdapter.notifyDataSetChanged();
                    }
                }
            });
        } else {
            Snackbar.make(binding.getRoot(), getString(R.string.error_connection), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickApproval(CategoryModel model, int position) {
        Snackbar.make(binding.getRoot(), getString(R.string.approval_test_msg), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClickDecline(CategoryModel model, int position) {
        arrayList.remove(position);
        categoryAdapter.setList(arrayList);
        categoryAdapter.notifyDataSetChanged();
    }
}