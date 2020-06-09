package com.app_nfusion.mmsorter.ui.dashboard;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app_nfusion.mmsorter.R;
import com.skydoves.progressview.ProgressView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static com.app_nfusion.mmsorter.ui.dashboard.DashboardViewModel.startTimer;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.blueColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.brownColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.greenColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.orangeColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.redColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.savedColors;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.unidentifiedColor;
import static com.app_nfusion.mmsorter.ui.home.HomeFragment.yellowColor;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private static final int REQUEST_ENABLE_BT = 0;
    private static BluetoothAdapter bluetoothAdapter;
    private static BluetoothSocket bluetoothSocket;
    private Set<BluetoothDevice> pairedDevices;
    private ProgressDialog progress;
    private boolean isBtConnected = false;
    public static String EXTRA_ADDRESS;
    public static String EXTRA_ADDRESS_HC05 = "98:D3:71:F5:C2:DC";
    public static String EXTRA_ADDRESS_HC06 = "98:D3:51:F9:5F:05";
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    ProgressView progressViewRed;
    ProgressView progressViewYellow;
    ProgressView progressViewBlue;
    ProgressView progressViewGreen;
    ProgressView progressViewOrange;
    ProgressView progressViewBrown;
    ProgressView progressViewUnidentified;

    boolean startSwitch = false;
    public static boolean refresh = false;
    private boolean showPercentagesSwitch = false;

    //Turn On Bluetooth
    public void turnOnBluetooth(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(getActivity(), "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            Toast.makeText(getActivity(), "Enabling Bluetooth", Toast.LENGTH_SHORT).show();
        }
        else {
            bluetoothAdapter.isEnabled();
            Toast.makeText(getActivity(), "Bluetooth Already Enabled", Toast.LENGTH_SHORT).show();
        }
    }

    //Turn Off Bluetooth
    public void turnOffBluetooth(){
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(getActivity(), "Disabling Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    //Refreshing the progress bar
    public void refreshScreen(){
        if (startSwitch == true) {
            if (refresh == true) {
                try {
                    showPairedArduino();
                    progressViewRed.setProgress(redColor);
                    progressViewGreen.setProgress(greenColor);
                    progressViewBlue.setProgress(blueColor);
                    progressViewBrown.setProgress(brownColor);
                    progressViewYellow.setProgress(yellowColor);
                    progressViewOrange.setProgress(orangeColor);
                    progressViewUnidentified.setProgress(unidentifiedColor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("Refresh", "No Update on Data");
            }
        }
        else if (startSwitch == false){
            Toast.makeText(getActivity(), "Not Connected to Arduino", Toast.LENGTH_SHORT).show();
        }
    }

    //Show Stats
    public void showStats(){
        if (showPercentagesSwitch == false) {
            progressViewRed.setLabelText(String.valueOf(redColor));
            progressViewGreen.setLabelText(String.valueOf(greenColor));
            progressViewBlue.setLabelText(String.valueOf(blueColor));
            progressViewBrown.setLabelText(String.valueOf(brownColor));
            progressViewYellow.setLabelText(String.valueOf(yellowColor));
            progressViewOrange.setLabelText(String.valueOf(orangeColor));
            progressViewUnidentified.setLabelText(String.valueOf(unidentifiedColor));
            showPercentagesSwitch = true;
        }
        else {
            progressViewRed.setLabelText(String.valueOf("Red"));
            progressViewYellow.setLabelText(String.valueOf("Yellow"));
            progressViewBlue.setLabelText(String.valueOf("Blue"));
            progressViewGreen.setLabelText(String.valueOf("Green"));
            progressViewOrange.setLabelText(String.valueOf("Orange"));
            progressViewBrown.setLabelText(String.valueOf("Brown"));
            progressViewUnidentified.setLabelText(String.valueOf("Unidentified"));
            showPercentagesSwitch = false;
        }
    }

    //Connect to Arduino
    public void showPairedArduino() throws IOException {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                if (bt.getName().equals("HC-05") && bt.getAddress().equals(EXTRA_ADDRESS_HC05)){
                    Toast.makeText(getActivity(), "Connected to Arduino", Toast.LENGTH_SHORT).show();
                    Intent newint = getActivity().getIntent();
                    EXTRA_ADDRESS = EXTRA_ADDRESS_HC05;
                    newint.getStringExtra(EXTRA_ADDRESS); //receive the address of the bluetooth device
                    new ConnectBT().execute(); //Call the class to connect
                }
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getActivity(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_SHORT).show();
        }
        Log.i("Bluetooth List : ", String.valueOf(list));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard);
        final Button refreshButton = root.findViewById(R.id.buttonRefresh);
        final Button startButton = root.findViewById(R.id.buttonStart);
        final Button statsButton = root.findViewById(R.id.buttonStats);

        progressViewRed = root.findViewById(R.id.dashboardRed);
        progressViewYellow = root.findViewById(R.id.dashboardYellow);
        progressViewBlue = root.findViewById(R.id.dashboardBlue);
        progressViewGreen = root.findViewById(R.id.dashboardGreen);
        progressViewOrange = root.findViewById(R.id.dashboardOrange);
        progressViewBrown = root.findViewById(R.id.dashboardBrown);
        progressViewUnidentified = root.findViewById(R.id.dashboardUnidentified);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        turnOnBluetooth();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPercentagesSwitch = true;
                refreshScreen();
                showStats();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()){
                if (startSwitch == false) {
                    startTimer(textView, false);
                    startButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_stop_white_24dp, 0, 0);
                    startButton.setText("Stop");
                    Toast.makeText(getActivity(), "Initiating M&M Sorter", Toast.LENGTH_SHORT).show();
                    startSwitch = true;
                    try {
                        showPairedArduino();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (startSwitch == true){
                    startTimer(textView, true);
                    startButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_play_arrow_white_24dp, 0, 0);
                    startButton.setText("Start");
                    Toast.makeText(getActivity(), "Stopping M&M Sorter", Toast.LENGTH_SHORT).show();
                    startSwitch = false;

                    savedColors.clear();

                    savedColors.add(0, redColor);
                    savedColors.add(1, yellowColor);
                    savedColors.add(2, blueColor);
                    savedColors.add(3, greenColor);
                    savedColors.add(4, orangeColor);
                    savedColors.add(5, brownColor);
                    savedColors.add(6, unidentifiedColor);

                    Log.i("Saved Values", String.valueOf(savedColors));
                }
            }
                else{
                    turnOnBluetooth();
                }
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStats();
            }
        });

        return root;
    }

    //Connect Bluetooth
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(getActivity(), "Connecting...", "Please wait!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (bluetoothSocket == null || !isBtConnected)
                {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = bluetoothAdapter.getRemoteDevice(EXTRA_ADDRESS);//connects to the device's address and checks if it's available
                    bluetoothSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    bluetoothSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                Log.e("Error","Connection Failed. Is it a SPP Bluetooth? Try again.");
                //finish();
            }
            else
            {
                Log.e("Success","Connected.");
                InputStream inputStream = null;
                try {
                    inputStream = bluetoothSocket.getInputStream();
                    inputStream.skip(inputStream.available());
                    for (int i = 0; i <= 7; i++) {
                        byte b = (byte) inputStream.read();
                        if (i == 0){

                        }
                        else if (i == 1){
                            greenColor = b;
                            Log.i("GREEN", String.valueOf(greenColor));
                            progressViewGreen.setProgress(greenColor);
                        }
                        else if (i == 2){
                            blueColor = b;
                            Log.i("BLUE", String.valueOf(blueColor));
                            progressViewBlue.setProgress(blueColor);
                        }
                        else if (i == 3){
                            brownColor = b;
                            Log.i("BROWN", String.valueOf(brownColor));
                            progressViewBrown.setProgress(brownColor);
                        }
                        else if (i == 4){
                            yellowColor = b;
                            Log.i("YELLOW", String.valueOf(yellowColor));
                            progressViewYellow.setProgress(yellowColor);
                        }
                        else if (i == 5){
                            orangeColor = b;
                            Log.i("ORANGE", String.valueOf(orangeColor));
                            progressViewOrange.setProgress(orangeColor);
                        }
                        else if (i == 6){
                            unidentifiedColor = b;
                            Log.i("UNIDENTIFIED", String.valueOf(unidentifiedColor));
                            progressViewUnidentified.setProgress(unidentifiedColor);
                        }
                        else{
                            redColor = b;
                            Log.i("RED", String.valueOf(redColor));
                            progressViewRed.setProgress(redColor);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Closing the socket here
                //bluetoothSocket.close();
                System.out.println(bluetoothSocket.isConnected());
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
