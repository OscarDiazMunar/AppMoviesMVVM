package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.appmoviesmvvm.R;
import com.oscar.appmoviesmvvm.data.rest.MovieDbClient;
import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ListMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMoviesFragment extends Fragment{
    private Context context;
    private List<Results> resultsListAux = new ArrayList<>();
    private int typeFragment;
    private int pageCont = 1;
    private int pageTotal;


    private ListMoviesViewModel viewModel;
    private ListMoviesViewModelFactory viewModelFactory;

    public ListMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ListMoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListMoviesFragment newInstance(int typeFragment) {
        ListMoviesFragment fragment = new ListMoviesFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARGS.TYPE_FRAGMENT, typeFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            typeFragment = getArguments().getInt(Constants.ARGS.TYPE_FRAGMENT);
        }
        viewModelFactory = new ListMoviesViewModelFactory(new GetListMovies(MovieDbClient.getInstance()));
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(ListMoviesViewModel.class);
        viewModel.getResponse().observe(this, responseApi -> proccessResponse(responseApi));

    }


    private void proccessResponse(ResponseApi responseApi){
        switch (responseApi.getStatus()){
            case Constants.API_STATUS.LOADING:

                break;
            case Constants.API_STATUS.SUCCESS:
                Log.e("HOLA", responseApi.responseMovies.toString());
                break;
            case Constants.API_STATUS.ERROR:
                Log.e("Error", responseApi.error.getMessage());
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movies, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        viewModel.getMovies("1");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
