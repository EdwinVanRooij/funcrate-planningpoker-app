package com.funcrate.funcrateplanningpoker;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.parceler.Parcels;

import java.net.URI;
import java.net.URISyntaxException;

import static com.funcrate.funcrateplanningpoker.Constants.KEY_URL;

public class SocketActivity extends AppCompatActivity {

    private String url;
    private String ip = "145.93.56.28";
    private int port = 8080;

    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);

        // Get the url from previous activity
        url = Parcels.unwrap(getIntent().getParcelableExtra(KEY_URL));
        Toast.makeText(this, String.format("URL is: %s", url), Toast.LENGTH_SHORT).show();

        setFragment(EnterNameFragment.class, false);

        connectWebSocket();
    }

    private void onMessageReceived(String s) {
        System.out.println(String.format("Received: %s", s));
    }

    public void sendMessageThroughSocket(String string) {
        runOnUiThread(() -> {
            // TODO: 16-1-17 uncomment
//            mWebSocketClient.send(string);
        });
    }

    private void connectWebSocket() {
        try {
            URI uri = new URI(String.format("ws://%s:%s/host", ip, port));

            mWebSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i("Websocket", "Opened");
                    mWebSocketClient.send(String.format("gameId;\"%s\"", url));
                }

                @Override
                public void onMessage(String s) {
                    onMessageReceived(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i("Websocket", "Closed " + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.i("Websocket", "Error " + e.getMessage());
                }
            };

            mWebSocketClient.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void setFragment(Class fragmentClass, boolean addToStack) {
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            // Add this transaction to the back stack
            if (addToStack) {
                ft.addToBackStack(null);
            }
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.replace(R.id.flContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
