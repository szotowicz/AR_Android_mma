package tomik.pg.com.mamproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import tomik.pg.com.mamproject.MainActivity;

public abstract class BaseFragment extends Fragment {

    protected MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    public MainActivity getMyActivity() {
        return mainActivity;
    }
}