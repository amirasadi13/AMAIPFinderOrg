package com.example.amaipfinder.home.page;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amaipfinder.R;
import com.example.amaipfinder.databinding.FragmentHomePageBinding;
import com.example.amaipfinder.home.page.ip.server.services.IpApiService;
import com.example.amaipfinder.home.page.ip.server.services.IpInfoPojo;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends Fragment {

    ActionBarDrawerToggle drawerToggle;
    IpInfoPojo ipInfo;
    FragmentHomePageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);


        final IpApiService ipApiService = RetrofitClient.getClient().create(IpApiService.class);


        binding.btnSearch.setOnClickListener(v -> {
                    callGetIp(ipApiService);
                    hideKeyBoard();
                }
        );
        setSupportActionBar();
        navigationOnItemSelected();
        setFocusOnBtnSearch();
        return binding.getRoot();
    }

    private void setFocusOnBtnSearch() {

        binding.btnSearch.requestFocus();

    }

    private void hideKeyBoard() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }


    private void callGetIp(final IpApiService ipApiService) {

        Call<IpInfoPojo> ipCall = ipApiService.getIp("90b3ff0bec4b40cdb57cc1078e2fb8d8",
                binding.etSearchIp.getText().toString());
        ipCall.enqueue(new Callback<IpInfoPojo>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<IpInfoPojo> call, Response<IpInfoPojo> response) {

                try {
                    if (response.code() == 200) {
                        ipInfo = response.body();
                        if (ipInfo != null) {
                            changeTagsState();
                        }
                        setTags();
                    } else {
//                        Toast.makeText(getContext(), "enter valid ip", Toast.LENGTH_SHORT).show();
                        showErrorDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<IpInfoPojo> call, Throwable t) {
            }
        });
    }

    private void showErrorDialog() {
        ViewDialog viewDialog = new ViewDialog();
        viewDialog.showDialog(getActivity(), "Enter Valid Ip");
    }

    private void setTags() {
        binding.tvIp.setText("Ip Address :" + " " + ipInfo.getIp());
        binding.tvCountery.setText("Country :" + " " + ipInfo.getCountryName());
        binding.tvCity.setText("City :" + " " + ipInfo.getCity());
        binding.tvCountryCapital.setText("Country Capital :" + ipInfo.getCountry_capital());
        binding.tvIsp.setText("Isp :\n" + ipInfo.getIsp());
        binding.tvOrganization.setText("Organization :\n" + ipInfo.getOrganization());
        binding.tvAsNumber.setText("As Number :" + ipInfo.getAsNumber());
        binding.tvLanguages.setText("Country Languages :\n" + ipInfo.getLanguages());
        Glide.with(getView()).load(ipInfo.getImageFlag()).into(binding.imgFlag);
        binding.tvTimeZoneName.setText("Time Zone :\n" + ipInfo.getTimeZonePojo().getName());
        binding.tvCurrentTime.setText("Current Time :\n" + ipInfo.getTimeZonePojo().getCurrentTime());
    }

    private void changeTagsState() {
        binding.progressBar.setVisibility(View.GONE);
        binding.tvProcessBar.setVisibility(View.GONE);
        binding.loadingBackground.setVisibility(View.GONE);
        binding.tvIp.setVisibility(View.VISIBLE);
        binding.tvCountery.setVisibility(View.VISIBLE);
        binding.tvCity.setVisibility(View.VISIBLE);
        binding.tvCountryCapital.setVisibility(View.VISIBLE);
        binding.tvIsp.setVisibility(View.VISIBLE);
        binding.tvAsNumber.setVisibility(View.VISIBLE);
        binding.tvOrganization.setVisibility(View.VISIBLE);
        binding.tvLanguages.setVisibility(View.VISIBLE);
        binding.tvTimeZoneName.setVisibility(View.VISIBLE);
        binding.tvCurrentTime.setVisibility(View.VISIBLE);
        binding.imgFlag.setVisibility(View.VISIBLE);
    }

    private void navigationOnItemSelected() {

        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.go_to_ip_ranges_page:
                    Navigation.findNavController(getView()).navigate(R.id.action_homePage_to_mapFrag);
                    break;
                case R.id.go_to_domain_search_page:
                    Navigation.findNavController(getView()).navigate(R.id.action_homePage_to_domainSearch);
                    break;
                case R.id.go_to_as_information_page:
                    Navigation.findNavController(getView()).navigate(R.id.action_homePage_to_ASInfoFragment);
                    break;
                case R.id.go_to_contactus_page:
                    showContactDialog();
                    break;
            }
            return true;
        });
    }

    private void showContactDialog() {
        ContactDialog contactDialog = new ContactDialog();
        contactDialog.showDialogContact(getActivity());
    }


    private void setSupportActionBar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.homePageToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        drawerToggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, binding.homePageToolbar, 0, 0);
        drawerToggle.setDrawerIndicatorEnabled(true);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_page_overflow_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final IpApiService ipApiService = RetrofitClient.getClient().create(IpApiService.class);
        switch (item.getItemId()) {
            case R.id.my_ip: {
                callGetIp(ipApiService);
            }
        }
        return super.onOptionsItemSelected(item);

    }
}
