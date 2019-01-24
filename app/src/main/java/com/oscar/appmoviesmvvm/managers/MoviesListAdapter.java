package com.oscar.appmoviesmvvm.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.appmoviesmvvm.R;
import com.oscar.appmoviesmvvm.domain.model.Results;
import com.oscar.appmoviesmvvm.utils.Constants;

import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private Context context;
    private List<Results> resultsList;
    private OnItemClickListener onItemClickListener;

    /**
     * Instantiates a new Movies list adapter.
     *
     * @param resultsList         the results list
     * @param context             the context
     * @param onItemClickListener the on item click listener
     */
    public MoviesListAdapter(List<Results> resultsList, Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.resultsList = resultsList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_movies_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Results dataMovie = resultsList.get(position);
        holder.txtTitleMovie.setText(dataMovie.getOriginal_title());
        holder.txtOverviewMovie.setText(dataMovie.getOverview());
        holder.txtScore.setText(dataMovie.getVote_average());
        holder.txtRelease.setText(dataMovie.getRelease_date());
        String urlPhoto = Constants.URL.PHOTO + dataMovie.getPoster_path();
        Glide.with(context).load(urlPhoto).into(holder.imgPoster);
        holder.setClickListener(dataMovie, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Txt title movie.
         */
        @BindView(R.id.txt_title_movie)
        TextView txtTitleMovie;
        /**
         * The Img poster.
         */
        @BindView(R.id.img_poster)
        ImageView imgPoster;
        /**
         * The Txt overview movie.
         */
        @BindView(R.id.txt_overview_movie)
        TextView txtOverviewMovie;
        /**
         * The Txt release.
         */
        @BindView(R.id.txt_release)
        TextView txtRelease;
        /**
         * The Txt score.
         */
        @BindView(R.id.txt_score)
        TextView txtScore;

        /**
         * The View.
         */
        View view;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        /**
         * Set click listener.
         *
         * @param results the results
         * @param listener the listner
         */
        public void setClickListener(final Results results, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(results);
                }
            });
        }
    }
}
