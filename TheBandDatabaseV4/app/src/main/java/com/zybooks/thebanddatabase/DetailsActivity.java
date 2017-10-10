package com.zybooks.thebanddatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements RatingFragment.OnRatingSelectedListener {

    public static String EXTRA_BAND_ID = "bandId";

    private int mBandId;

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


        mBandId = getIntent().getIntExtra(EXTRA_BAND_ID, 1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        if (fragment == null) {
            // Use band ID from ListFragment to instantiate DetailsFragment
            mDetailsFragment = DetailsFragment.newInstance(mBandId);
            fragmentManager.beginTransaction()
                    .add(R.id.details_fragment_container, mDetailsFragment)
                    .commit();
        }
        fragment = fragmentManager.findFragmentById(R.id.rating_fragment_container);
        BandDatabase db = BandDatabase.get(this);
        if (fragment == null && db.getBand(mBandId).getRating() < 0) {
            mRatingFragment = RatingFragment.newInstance(db.getBand(mBandId).getRating());
            fragmentManager.beginTransaction()
                    .add(R.id.rating_fragment_container, mRatingFragment)
                    .commit();
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onRatingSelected(float rating) {
        BandDatabase db = BandDatabase.get(this);
        db.getBand(mBandId).setRating(rating);
        displayRating(rating);
    }

    private void displayRating(float rating) {
        TextView r = (TextView) mDetailsFragment.getView().findViewById(R.id.bandRating);
        r.setText("Rating: " + rating);
    }
}
