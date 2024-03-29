package com.funcrate.funcrateplanningpoker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadyFragment extends Fragment {

    @BindView(R.id.btnUnready)
    Button btnUnready;
    @BindView(R.id.btnReady)
    Button btnReady;

    private Unbinder unbinder;

    public ReadyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }


    @OnClick(R.id.btnReady)
    public void onButtonReadyClick() {
        btnReady.setVisibility(GONE);
        btnUnready.setVisibility(View.VISIBLE);

        SocketActivity socketActivity = (SocketActivity) getActivity();

        socketActivity.sendMessageThroughSocket("ready;true");
    }

    @OnClick(R.id.btnUnready)
    public void onButtonUnreadyClick() {
        btnUnready.setVisibility(GONE);
        btnReady.setVisibility(View.VISIBLE);

        SocketActivity socketActivity = (SocketActivity) getActivity();

        socketActivity.sendMessageThroughSocket("ready;false");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
