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
import android.widget.Button;
import android.widget.TextView;

import com.oscar.appmoviesmvvm.R;
import com.oscar.appmoviesmvvm.data.rest.MovieDbClient;
import com.oscar.appmoviesmvvm.domain.model.ResponseApi;
import com.oscar.appmoviesmvvm.domain.model.ResponseMovies;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.domain.usecase.ListMovies.GetListMovies;
import com.oscar.appmoviesmvvm.managers.MoviesListAdapter;
import com.oscar.appmoviesmvvm.managers.OnItemClickListener;
import com.oscar.appmoviesmvvm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ListMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMoviesFragment extends Fragment implements OnItemClickListener {
    @BindView(R.id.RecyclerListMovies)
    RecyclerView recyclerListMovies;
    @BindView(R.id.btnback)
    Button btnback;
    @BindView(R.id.page)
    TextView page;
    @BindView(R.id.btnnext)
    Button btnnext;
    Unbinder unbinder;

    public MoviesListAdapter moviesListAdapter;

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
     *
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

        moviesListAdapter = new MoviesListAdapter(resultsListAux, getContext(), this);
        recyclerListMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerListMovies.setAdapter(moviesListAdapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        selectTypeFragment(Integer.toString(pageCont));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void selectTypeFragment(String page) {
        switch (typeFragment) {
            case 0:
                Log.e("TYPEMOVIE", "0");
                viewModel.getResponsePopular().observe(this, responseApi -> proccessResponse(responseApi));
                viewModel.getMoviesPopular(page);
                break;
            case 1:
                Log.e("TYPEMOVIE", "1");
                viewModel.getResponseTopRated().observe(this, responseApi -> proccessResponse(responseApi));
                viewModel.getMoviesTopRating(page);
                break;
            case 2:
                Log.e("TYPEMOVIE", "2");
                viewModel.getResponseUpcoming().observe(this, responseApi -> proccessResponse(responseApi));
                viewModel.getMoviesUpcoming(page);
                break;
        }
    }

    private void proccessResponse(ResponseApi responseApi) {
        switch (responseApi.getStatus()) {
            case Constants.API_STATUS.LOADING:

                break;
            case Constants.API_STATUS.SUCCESS:
                setDataResults(responseApi.responseMovies);
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

    @OnClick({R.id.btnback, R.id.btnnext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnback:
                if (pageCont != 1){
                    pageCont--;
                    selectTypeFragment(Integer.toString(pageCont));
                }
                break;
            case R.id.btnnext:
                if (pageCont < pageTotal){
                    pageCont++;
                    selectTypeFragment(Integer.toString(pageCont));
                }
                break;
        }
    }

    @Override
    public void onItemClick(Results results) {

    }

    public void setDataResults(ResponseMovies responseMovies) {
        resultsListAux.clear();
        List<Results> resultsList = responseMovies.getResults();
        for (Results item : resultsList) {
            resultsListAux.add(item);
        }
        moviesListAdapter.notifyDataSetChanged();
        pageTotal = Integer.parseInt(responseMovies.getTotal_pages());
        String pager = Integer.toString(pageCont) + " de " + responseMovies.getTotal_pages();
        page.setText(pager);
    }
}
