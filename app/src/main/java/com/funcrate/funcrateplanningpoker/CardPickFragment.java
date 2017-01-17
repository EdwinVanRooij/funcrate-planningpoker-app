package com.funcrate.funcrateplanningpoker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardPickFragment extends Fragment {

    @BindView(R.id.iv0)
    ImageView iv0;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv8)
    ImageView iv8;
    @BindView(R.id.iv13)
    ImageView iv13;
    @BindView(R.id.iv20)
    ImageView iv20;
    @BindView(R.id.iv40)
    ImageView iv40;
    @BindView(R.id.iv100)
    ImageView iv100;

    private Unbinder unbinder;


    boolean selected = false;
    int getal = 0;

    public CardPickFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.btnConfirm)
    public void onButtonConfirmClick() {
        System.out.println("Clicked button confirm");

        SocketActivity activity = (SocketActivity) getActivity();

        if (selected) {
            activity.sendMessageThroughSocket(String.format("vote;%s", getal));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_pick, container, false);

        unbinder = ButterKnife.bind(this, view);

        iv0.setOnClickListener(v -> getal = 0);
        iv1.setOnClickListener(v -> getal = 1);
        iv2.setOnClickListener(v -> getal = 2);
        iv5.setOnClickListener(v -> getal = 5);
        iv8.setOnClickListener(v -> getal = 8);
        iv13.setOnClickListener(v -> getal = 13);
        iv20.setOnClickListener(v -> getal = 20);
        iv40.setOnClickListener(v -> getal = 40);
        iv100.setOnClickListener(v -> getal = 100);

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

}
