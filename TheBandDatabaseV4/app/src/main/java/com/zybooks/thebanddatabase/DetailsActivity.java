package com.zybooks.thebanddatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements RatingFragment.OnRatingSelectedListener {

    public static String EXTRA_BAND_ID = "bandId";

    private RatingFragment mRatingFragment;

    private DetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Terminate if two panes are displaying since ListActivity should be displaying both panes
        boolean isTwoPanes = getResources().getBoolean(R.bool.twoPanes);
        if (isTwoPanes) {
            finish();
            return;
        }

        setContentView(R.layout.activity_details);


        int bandId = getIntent().getIntExtra(EXTRA_BAND_ID, 1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        if (fragment == null) {
            // Use band ID from ListFragment to instantiate DetailsFragment
            mDetailsFragment = DetailsFragment.newInstance(bandId);
            fragmentManager.beginTransaction()
                    .add(R.id.details_fragment_container, mDetailsFragment)
                    .commit();
        }
        fragment = fragmentManager.findFragmentById(R.id.rating_fragment_container);
        if (fragment == null) {
            BandDatabase db = BandDatabase.get(this);
            mRatingFragment = RatingFragment.newInstance(db.getBand(bandId).getRating());
            fragmentManager.beginTransaction()
                    .add(R.id.rating_fragment_container, mRatingFragment)
                    .commit();
        }

        mRatingFragment.getRatingBar().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                RatingBar rb = (RatingBar) view;
                onRatingSelected(rb.getRating());
                return true;
            }
        });
    }

    @Override
    public void onRatingSelected(float rating) {
        BandDatabase db = BandDatabase.get(this);
        int bandId = getIntent().getIntExtra(EXTRA_BAND_ID, 1);;
        db.getBand(bandId).setRating(rating);
        displayRating(bandId);
    }

    private void displayRating(int bandId) {
        BandDatabase db = BandDatabase.get(this);
        TextView r = (TextView) mDetailsFragment.getView().findViewById(R.id.bandRating);
        r.setText("Rating: " + db.getBand(bandId).getRating());
    }
}
