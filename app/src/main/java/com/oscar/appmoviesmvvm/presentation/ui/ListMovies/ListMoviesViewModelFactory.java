package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;

public class ListMoviesViewModelFactory implements ViewModelProvider.Factory {
    private final GetListMovies getListMovies;

    public ListMoviesViewModelFactory(GetListMovies getListMovies) {
        this.getListMovies = getListMovies;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListMoviesViewModel.class)){
            return (T) new ListMoviesViewModel(getListMovies);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
