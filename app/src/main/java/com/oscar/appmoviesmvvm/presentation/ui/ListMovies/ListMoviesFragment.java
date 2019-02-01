package com.oscar.appmoviesmvvm.presentation.ui.ListMovies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.appmoviesmvvm.R;
import com.oscar.appmoviesmvvm.data.rest.MovieDbClient;
import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.managers.OnItemClickListener;
import com.oscar.appmoviesmvvm.managers.adapterPaging.MoviesListPagingAdapter;
import com.oscar.appmoviesmvvm.presentation.ui.VideoMovies.VideoDialogFragment;
import com.oscar.appmoviesmvvm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ListMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMoviesFragment extends Fragment implements OnItemClickListener {
    /**
     * The Recycler list movies.
     */
    @BindView(R.id.RecyclerListMovies)
    RecyclerView recyclerListMovies;
    /**
     * The Unbinder.
     */
    Unbinder unbinder;
    /**
     * The Movies list paging adapter.
     */
    public MoviesListPagingAdapter moviesListPagingAdapter;

    private Context context;
    private List<Results> resultsListAux = new ArrayList<>();
    private int typeFragment;


    private ListMoviesViewModel viewModel;
    private ListMoviesViewModelFactory viewModelFactory;

    /**
     * Instantiates a new List movies fragment.
     */
    public ListMoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param typeFragment the type fragment
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        unbinder = ButterKnife.bind(this, view);

        moviesListPagingAdapter = new MoviesListPagingAdapter(getContext(), this);
        recyclerListMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerListMovies.setAdapter(moviesListPagingAdapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        selectTypeFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void selectTypeFragment() {
        switch (typeFragment) {
            case Constants.TYPE_FRAGMENT.POPULAR:
                Log.e("TYPEMOVIE", "0");
                viewModel.getPagedListResults(Constants.TYPE_FRAGMENT.POPULAR).observe(this, moviesListPagingAdapter::submitList);
                break;
            case Constants.TYPE_FRAGMENT.TOP_RATED:
                Log.e("TYPEMOVIE", "1");
                viewModel.getPagedListResults(Constants.TYPE_FRAGMENT.TOP_RATED).observe(this, moviesListPagingAdapter::submitList);
                break;
            case Constants.TYPE_FRAGMENT.UPCOMING:
                viewModel.getPagedListResults(Constants.TYPE_FRAGMENT.UPCOMING).observe(this, moviesListPagingAdapter::submitList);
                Log.e("TYPEMOVIE", "2");
                break;
        }
    }

    private void proccessResponse(ResponseApi responseApi) {
        switch (responseApi.getStatus()) {
            case Constants.API_STATUS.LOADING:

                break;
            case Constants.API_STATUS.SUCCESS:
                Log.e("SUCCES API", "AQUI");
                break;
            case Constants.API_STATUS.ERROR:
                Log.e("Error", responseApi.error.getMessage());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(Results results) {
        VideoDialogFragment videoDialogFragment = VideoDialogFragment.newInstance(results.getId());
        videoDialogFragment.show(getFragmentManager(), "");
    }
}
