package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.presentation.ui.VideoMovies.VideoDialogVIewModel;

public class ListMoviesViewModelFactory implements ViewModelProvider.Factory {
    private final GetListMovies getListMovies;

    public ListMoviesViewModelFactory(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VideoDialogVIewModel.class)){
            return (T) new VideoDialogVIewModel(getListMovies);
        }
        else if (modelClass.isAssignableFrom(ListMoviesViewModel.class)){
            return (T) new ListMoviesViewModel(getListMovies);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
