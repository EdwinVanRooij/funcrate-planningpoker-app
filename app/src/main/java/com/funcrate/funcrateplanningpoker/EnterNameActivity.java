package com.funcrate.funcrateplanningpoker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.parceler.Parcels;

import static com.funcrate.funcrateplanningpoker.Constants.KEY_URL;

public class EnterNameActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        ButterKnife.bind(this);

        url = Parcels.unwrap(getIntent().getParcelableExtra(KEY_URL));
        Toast.makeText(this, String.format("URL is: %s", url), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnConfirm)
    public void onButtonConfirmClick() {
        Toast.makeText(this, String.format("Username is: %s", etUsername.getText().toString()), Toast.LENGTH_SHORT).show();
    }
}
