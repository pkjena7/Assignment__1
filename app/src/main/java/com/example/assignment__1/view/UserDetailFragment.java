package com.example.assignment__1.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment__1.R;
import com.example.assignment__1.databinding.FragmentUserDetailBinding;
import com.example.assignment__1.network.ApiFactory;
import com.example.assignment__1.network.UserApi;
import com.example.assignment__1.repository.UserRepository;
import com.example.assignment__1.viewmodel.MyViewModelFactory;
import com.example.assignment__1.viewmodel.UserViewModel;


public class UserDetailFragment extends Fragment {

    FragmentUserDetailBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_detail,container,false);
        UserApi api = ApiFactory.create();
        UserRepository repository = new UserRepository(api);
        MyViewModelFactory factory = new MyViewModelFactory(repository);
        UserViewModel userViewModel = new ViewModelProvider(getActivity(),factory).get(UserViewModel.class);
        binding.setUser(userViewModel.clickedUser);
        return binding.getRoot();
    }
}