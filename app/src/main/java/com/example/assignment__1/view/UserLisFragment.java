package com.example.assignment__1.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment__1.R;
import com.example.assignment__1.adapter.ListUsersAdapter;
import com.example.assignment__1.databinding.FragmentUserLisBinding;
import com.example.assignment__1.model.User;
import com.example.assignment__1.network.ApiFactory;
import com.example.assignment__1.network.Resource;
import com.example.assignment__1.network.UserApi;
import com.example.assignment__1.repository.UserRepository;
import com.example.assignment__1.viewmodel.MyViewModelFactory;
import com.example.assignment__1.viewmodel.UserViewModel;

import java.util.List;


public class UserLisFragment extends Fragment implements MyInterface{

    public static final String TAG = UserLisFragment.class.getSimpleName();
    UserViewModel userViewModel;
    FragmentUserLisBinding binding;
    NavController navController = null;

    public void observeResponse() {
        userViewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<Resource<List<User>>>() {
            @Override
            public void onChanged(Resource<List<User>> listResource) {
                if (listResource != null){
                    switch (listResource.status){
                        case LOADING:{
                            Log.d(TAG, "onChanged: UserLisFragment: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            //binding.setDataList(listResource.data);
                            initAdapter(listResource.data);
                            Log.d(TAG, "onChanged: UserLisFragment: got posts."+listResource.data.get(0).getFullName());
                            break;
                        }

                        case ERROR:{
                            Log.d(TAG, "onChanged: UserLisFragment: ERROR... " + listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_lis,container,false);
        UserApi api = ApiFactory.create();
        UserRepository repository = new UserRepository(api);
        MyViewModelFactory factory = new MyViewModelFactory(repository);
        userViewModel = new ViewModelProvider(getActivity(),factory).get(UserViewModel.class);
        Log.d(TAG, "onCreate:UserLisFragment "+userViewModel.userName);
        observeResponse();

        userViewModel.fetchUserRepo();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void initAdapter(List<User> data) {
        binding.listUser.setLayoutManager(new LinearLayoutManager(getContext()));
        ListUsersAdapter adapter = new ListUsersAdapter(this,data);
        binding.listUser.setAdapter(adapter);
        binding.listUser.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onSubmitClick() {

    }

    @Override
    public void userClicked(User user) {
        userViewModel.clickedUser = user;
        navController.navigate(R.id.action_userLisFragment_to_userDetailFragment);
        //Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
    }
}