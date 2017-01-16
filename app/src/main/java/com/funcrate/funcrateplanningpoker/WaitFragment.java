package com.funcrate.funcrateplanningpoker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitFragment extends Fragment {

    private Unbinder unbinder;

    public WaitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wait, container, false);

        unbinder = ButterKnife.bind(this, view);

        // TODO: 16-1-17 this is just for the simulation effect, remove this in production
        new Thread(() -> {
            try {
                Thread.sleep(2000);

                SocketActivity activity = (SocketActivity) getActivity();

                activity.setFragment(CardPickFragment.class, false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
