package com.example.mediacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Layout-widgets
    private Button pauseButton;
    private Button minusButton;
    private Button plusButton;
    private Button saveIPButton;
    private Button prevtrackButton;
    private Button nexttrackButton;
    private EditText ipTextField;

    // Other variables
    private String my_ip;
    // initialize SharedPreferences var
    SharedPreferences sharedPref;

    // Gets loaded if app starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Connects java file with layout activity_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get or create SharedPreferences
        sharedPref = getSharedPreferences("myPref", MODE_PRIVATE);

        my_ip = sharedPref.getString("user_ip", "192.168.178.0");

        // Connects layout-widgets to variables
        pauseButton = findViewById(R.id.pause);
        minusButton = findViewById(R.id.minus);
        plusButton = findViewById(R.id.plus);
        prevtrackButton = findViewById(R.id.prevtrack);
        nexttrackButton = findViewById(R.id.nexttrack);
        saveIPButton = findViewById(R.id.saveIP);
        ipTextField = findViewById(R.id.editIP);

        // Activates listener for save-button
        pauseButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        prevtrackButton.setOnClickListener(this);
        nexttrackButton.setOnClickListener(this);
        saveIPButton.setOnClickListener(this);
        ipTextField.setText(my_ip);


    }

    // Gets called if anything on the ui is pressed
    @Override
    public void onClick(View v) throws IllegalStateException {
        switch (v.getId()) {
            case R.id.pause:
                sendToPC("pause");
                break;
            case R.id.minus:
                sendToPC("minus");
                break;
            case R.id.plus:
                sendToPC("plus");
                break;
            case R.id.prevtrack:
                sendToPC("prev");
                break;
            case R.id.nexttrack:
                sendToPC("next");
                break;
            case R.id.saveIP:
                my_ip = ipTextField.getText().toString();
                // save your string in SharedPreferences
                sharedPref.edit().putString("user_ip", my_ip).commit();
                System.out.println("changed ip to "+ my_ip);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void sendToPC(String action) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket as = new Socket(my_ip, 9004);

                    switch (action) {
                        case "pause":
                            System.out.println("Sending pause...");
                            as.getOutputStream().write(1); // 1 = pause
                            break;
                        case "minus":
                            System.out.println("Sending minus...");
                            as.getOutputStream().write(2); // 2 = minus
                            break;
                        case "plus":
                            System.out.println("Sending plus...");
                            as.getOutputStream().write(3); // 3 = plus
                            break;
                        case "prev":
                            System.out.println("Sending prev...");
                            as.getOutputStream().write(4); // 4 = prevtrack
                            break;
                        case "next":
                            System.out.println("Sending next...");
                            as.getOutputStream().write(5); // 5 = nexttrack
                            break;
                        default:
                            System.out.println("default");
                    }
                    as.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}

