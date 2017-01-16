package com.funcrate.funcrateplanningpoker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.funcrate.funcrateplanningpoker.R.id.etUsername;
import static java.security.AccessController.getContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterNameFragment extends Fragment {

    @BindView(R.id.etUsername)
    EditText etUsername;

    public EnterNameFragment() {
        // Required empty public constructor
    }

    private Unbinder unbinder;

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_name, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onButtonConfirmClick() {
        System.out.println("Clicked button confirm");
        String username = etUsername.getText().toString();

        Toast.makeText(getContext(), String.format("Username is: %s", username), Toast.LENGTH_SHORT).show();

        SocketActivity activity = (SocketActivity) getActivity();
        activity.sendMessageThroughSocket(String.format("join;\"%s\"", username));

        activity.setFragment(ReadyFragment.class, false);

        // TODO: 16-1-17 this is just for the simulation effect, remove this in production
        new Thread(() -> {
            try {
                Thread.sleep(4000);

                activity.setFragment(CardPickFragment.class, false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
