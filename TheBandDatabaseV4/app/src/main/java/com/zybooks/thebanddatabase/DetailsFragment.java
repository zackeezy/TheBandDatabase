package com.zybooks.thebanddatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private Band mBand;

    private TextView mName;

    private TextView mDescription;

    private TextView mGenre;

    private TextView mRating;

    public static DetailsFragment newInstance(int bandId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("bandId", bandId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the band ID from the intent that started DetailsActivity
        int bandId = 1;
        if (getArguments() != null) {
            bandId = getArguments().getInt("bandId");
        }

        mBand = BandDatabase.get(getContext()).getBand(bandId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        mName = (TextView) view.findViewById(R.id.bandName);
        mName.setText(mBand.getName());

        mGenre = (TextView) view.findViewById(R.id.bandGenre);
        mGenre.setText(mBand.getGenre());

        mDescription = (TextView) view.findViewById(R.id.bandDescription);
        mDescription.setText(mBand.getDescription());

        mRating = (TextView) view.findViewById(R.id.bandRating);
        if (mBand.getRating() >= 0)
            mRating.setText("Rating: " + mBand.getRating());
        else
            mRating.setText("");


        return view;
    }

    public void setName(String name){
        mName.setText(name);
    }

    public void setGenre(String genre){
        mGenre.setText(genre);
    }

    public void setDescription(String desc){
        mDescription.setText(desc);
    }

    public void setRating(String rating){
        mRating.setText(rating);
    }
}
