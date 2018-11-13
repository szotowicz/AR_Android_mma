package tomik.pg.com.mamproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tomik.pg.com.mamproject.R;

public class TrackingAlgorithmFragment extends BaseFragment {

    public TrackingAlgorithmFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tracking_algorithm, container, false);
    }
}