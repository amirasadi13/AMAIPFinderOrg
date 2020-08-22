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
import com.example.amaipfinder.databinding.FragmentAsinfoBinding;


public class ASInfoFragment extends Fragment {


    FragmentAsinfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_asinfo, container, false);

        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webView.loadUrl("https://www.ultratools.com/tools/asnInfo");

        setSupportActionBar();
        return binding.getRoot();
    }

    private void setSupportActionBar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar3);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        binding.toolbar3.setNavigationOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_ASInfoFragment_to_homePage)
        );
    }

}
