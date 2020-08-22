package com.example.amaipfinder.home.page;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.example.amaipfinder.R;
import com.example.amaipfinder.databinding.FragmentDomainSearchBinding;


public class DomainSearch extends Fragment {


    FragmentDomainSearchBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_domain_search, container, false);


        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webView.loadUrl("https://www.ultratools.com/tools/ping");

        setSupportActionBar();
        return binding.getRoot();
    }

    private void setSupportActionBar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar2);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        binding.toolbar2.setNavigationOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_domainSearch_to_homePage)
        );
    }
}
