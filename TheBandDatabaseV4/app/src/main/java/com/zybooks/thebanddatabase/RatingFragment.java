package com.zybooks.thebanddatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingFragment extends Fragment {
    private RatingBar mRatingBar;

    public interface OnRatingSelectedListener{
        void onRatingSelected(float rating);
    }

    public static RatingFragment newInstance(float rating){
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putFloat("rating", rating);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        // TODO: stuff
        mRatingBar = view.findViewById(R.id.ratingBar);

        mRatingBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((DetailsActivity)getActivity()).onRatingSelected(mRatingBar.getRating());
                //mRatingBar.setVisibility(View.GONE);
                return false;
            }

        });

        return view;
    }

    public RatingBar getRatingBar(){
        return mRatingBar;
    }
}
