package com.funcrate.funcrateplanningpoker;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import org.parceler.Parcels;

import static android.app.Activity.RESULT_OK;
import static com.funcrate.funcrateplanningpoker.Constants.KEY_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.etManualURL)
    EditText etManualURL;

    static final int SCAN_QR_REQUEST = 2;  // The request code
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private String lobby_url;

    private Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnScan)
    public void onButtonScanClick() {
        startScanActivity();
    }

    @OnClick(R.id.btnJoin)
    public void onButtonJoinClick() {
        lobby_url = etManualURL.getText().toString();
        startEnterNameActivity();
    }

    private void startEnterNameActivity() {
        Intent intent = new Intent(getActivity(), EnterNameActivity.class);

        intent.putExtra(KEY_URL, Parcels.wrap(lobby_url));

        startActivity(intent);
    }

    private void startScanActivity() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(getActivity(), SimpleScannerActivity.class);
            startActivityForResult(intent, SCAN_QR_REQUEST);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCAN_QR_REQUEST) {
            if (resultCode == RESULT_OK) {
                lobby_url = data.getData().toString();
                startEnterNameActivity();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
