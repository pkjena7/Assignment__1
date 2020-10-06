package com.example.assignment__1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment__1.model.User;
import com.example.assignment__1.network.Resource;
import com.example.assignment__1.repository.UserRepository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    public String userName;
    private UserRepository repository;
    protected final MutableLiveData<Resource<List<User>>> mUsers = new MutableLiveData<>();


    public MutableLiveData<Resource<List<User>>> getResponse() {
        return mUsers;
    }

    public UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public User clickedUser;

    public void fetchUserRepo() {
        repository.fetchUserRepo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(s -> mUsers.postValue(Resource.loading((List<User>)null)))
                //.doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<User> listResource) {
                        mUsers.postValue(Resource.success(listResource));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mUsers.postValue(Resource.error(e.getMessage(),null));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
