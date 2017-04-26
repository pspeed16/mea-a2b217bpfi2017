package com.pfi2017.mea_a2b217b.guitarsimulator;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    //static final int PICK_CHORDS_REQUEST = 1;
    //Was meant to be used for picking the chords in a startActivityForResult.

    static {
        //Necessary for vector graphics.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    //Declare variables
    String bpm = "120";
    int bpmInt = Integer.parseInt(bpm);
    int delay;
    EditText bpmPicker;
    Button metronomeStart;
    int scaleSelected = 0;
    Button[][] frets = new Button[6][];
    List<int[][]> scaleList = new ArrayList<>();
    List<int[]> chordList = new ArrayList<>();
    //boolean[] chordsPicked = new boolean[30];
    MediaPlayer click;
    MediaPlayer[] notes=new MediaPlayer[30];
    //int chordCounter = 0;
    private int startStopInt = 0;
    private int step = 0;
    private int string = 0;
    private int cycle = 0;
    //private int ticks = 0;
    //private int chordAmount = 0;
    //private int previousChordIndex = -1;
    private boolean playScale;
    private boolean stopRunnable = false;
    //private boolean playChords = false;
    private Handler metronomeHandler = null;
    //The Runnable for the metronome. This also highlights the frets.
    // Because a new thread is created that is tied to the metronomeHandler, it runs on the background thread.
    private Runnable metronomeRunnable = new Runnable() {
        public void run() {
            if (!stopRunnable) {
                click.stop();
                click.release();
                click = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.click);
                click.start();
                metronomeHandler.postDelayed(this, delay);
                /*Chords highlighting function was removed. Perhaps it can be reimplemented later.
                if (playChords && ticks == 0) {
                    highlightFrets();
                    ticks = 3;
                } else if (playChords) ticks--;
                else */

                    highlightFrets();
                //Makes sure the runnable actually stops.
                if (stopRunnable) {
                    //Removes callbacks (any queued calls to the Runnable.)
                    metronomeHandler.removeCallbacks(metronomeRunnable);
                    //Resets fret colours.
                    resetColours();
                    highlightFrets();
                }
            }
        }
    };
    //OnClickListener for the start button.
    private OnClickListener startOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (startStopInt == 0) {
                startMetronome(view);
                startStopInt = 1;
            } else if (startStopInt == 1) {
                stopMetronome(view);
                startStopInt = 0;
            }
        }
    };
    //OnTouchListener for the fret buttons. Could definitely be improved.
    private View.OnTouchListener fretsOnTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            //On ACTION_DOWN, it plays the relevant note.
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if(view.getId()==frets[0][0].getId()){
                            //Creates the MediaPlayer and starts the sound.
                            notes[0] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_1_g);
                            notes[0].start();
                        } else if(view.getId()==frets[0][1].getId()){
                            notes[1] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_1_gsharp);
                            notes[1].start();
                        }else if(view.getId()==frets[0][2].getId()){
                            notes[2] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_1_a);
                            notes[2].start();
                        }else if(view.getId()==frets[0][3].getId()){
                            notes[3] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_1_asharp);
                            notes[3].start();
                        }else if(view.getId()==frets[0][4].getId()){
                            notes[4] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_1_b);
                            notes[4].start();
                        }else if(view.getId()==frets[1][0].getId()){
                            notes[5] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_2_c);
                            notes[5].start();
                        }else if(view.getId()==frets[1][1].getId()){
                            notes[6] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_2_csharp);
                            notes[6].start();
                        }else if(view.getId()==frets[1][2].getId()){
                            notes[7] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_2_d);
                            notes[7].start();
                        }else if(view.getId()==frets[1][3].getId()){
                            notes[8] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_2_dsharp);
                            notes[8].start();
                        }else if(view.getId()==frets[1][4].getId()){
                            notes[9] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_2_e);
                            notes[9].start();
                        }else if(view.getId()==frets[2][0].getId()){
                            notes[10] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_3_f);
                            notes[10].start();
                        }else if(view.getId()==frets[2][1].getId()){
                            notes[11] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_3_fsharp);
                            notes[11].start();
                        }else if(view.getId()==frets[2][2].getId()){
                            notes[12] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_3_g);
                            notes[12].start();
                        }else if(view.getId()==frets[2][3].getId()){
                            notes[13] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_3_gsharp);
                            notes[13].start();
                        }else if(view.getId()==frets[2][4].getId()){
                            notes[14] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_3_a);
                            notes[14].start();
                        }else if(view.getId()==frets[3][0].getId()){
                            notes[15] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_4_asharp);
                            notes[15].start();
                        }else if(view.getId()==frets[3][1].getId()){
                            notes[16] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_4_b);
                            notes[16].start();
                        }else if(view.getId()==frets[3][2].getId()){
                            notes[17] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_4_c);
                            notes[17].start();
                        }else if(view.getId()==frets[3][3].getId()){
                            notes[18] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_4_csharp);
                            notes[18].start();
                        }else if(view.getId()==frets[3][4].getId()){
                            notes[19] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_4_d);
                            notes[19].start();
                        }else if(view.getId()==frets[4][0].getId()){
                            notes[20] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_5_d);
                            notes[20].start();
                        }else if(view.getId()==frets[4][1].getId()){
                            notes[21] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_5_dsharp);
                            notes[21].start();
                        }else if(view.getId()==frets[4][2].getId()){
                            notes[22] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_5_e);
                            notes[22].start();
                        }else if(view.getId()==frets[4][3].getId()){
                            notes[23] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_5_f);
                            notes[23].start();
                        }else if(view.getId()==frets[4][4].getId()){
                            notes[24] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_5_fsharp);
                            notes[24].start();
                        }else if(view.getId()==frets[5][0].getId()){
                            notes[25] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_6_g);
                            notes[25].start();
                        }else if(view.getId()==frets[5][1].getId()){
                            notes[26] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_6_gsharp);
                            notes[26].start();
                        }else if(view.getId()==frets[5][2].getId()){
                            notes[27] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_6_a);
                            notes[27].start();
                        }else if(view.getId()==frets[5][3].getId()){
                            notes[28] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_6_asharp);
                            notes[28].start();
                        }else if(view.getId()==frets[5][4].getId()){
                            notes[29] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.string_6_b);
                            notes[29].start();
                        }
                        if (scaleSelected==0) {
                            //Only changes background color when no scale is selected.
                            view.setBackgroundColor(Color.RED);
                        }
                    }
                    //On ACTION_UP it stops playing.
                    else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if(view.getId()==frets[0][0].getId()){
                            //Stops and releases the MediaPlayer. This requires less resources.
                            notes[0].stop();
                            notes[0].release();
                        } else if(view.getId()==frets[0][1].getId()){
                            notes[1].stop();
                            notes[1].release();
                        }else if(view.getId()==frets[0][2].getId()){
                            notes[2].stop();
                            notes[2].release();
                        }else if(view.getId()==frets[0][3].getId()){
                            notes[3].stop();
                            notes[3].release();
                        }else if(view.getId()==frets[0][4].getId()){
                            notes[4].stop();
                            notes[4].release();
                        }else if(view.getId()==frets[1][0].getId()){
                            notes[5].stop();
                            notes[5].release();
                        }else if(view.getId()==frets[1][1].getId()){
                            notes[6].stop();
                            notes[6].release();
                        }else if(view.getId()==frets[1][2].getId()){
                            notes[7].stop();
                            notes[7].release();
                        }else if(view.getId()==frets[1][3].getId()){
                            notes[8].stop();
                            notes[8].release();
                        }else if(view.getId()==frets[1][4].getId()){
                            notes[9].stop();
                            notes[9].release();
                        }else if(view.getId()==frets[2][0].getId()){
                            notes[10].stop();
                            notes[10].release();
                        }else if(view.getId()==frets[2][1].getId()){
                            notes[11].stop();
                            notes[11].release();
                        }else if(view.getId()==frets[2][2].getId()){
                            notes[12].stop();
                            notes[12].release();
                        }else if(view.getId()==frets[2][3].getId()){
                            notes[13].stop();
                            notes[13].release();
                        }else if(view.getId()==frets[2][4].getId()){
                            notes[14].stop();
                            notes[14].release();
                        }else if(view.getId()==frets[3][0].getId()){
                            notes[15].stop();
                            notes[15].release();
                        }else if(view.getId()==frets[3][1].getId()){
                            notes[16].stop();
                            notes[16].release();
                        }else if(view.getId()==frets[3][2].getId()){
                            notes[17].stop();
                            notes[17].release();
                        }else if(view.getId()==frets[3][3].getId()){
                            notes[18].stop();
                            notes[18].release();
                        }else if(view.getId()==frets[3][4].getId()){
                            notes[19].stop();
                            notes[19].release();
                        }else if(view.getId()==frets[4][0].getId()){
                            notes[20].stop();
                            notes[20].release();
                        }else if(view.getId()==frets[4][1].getId()){
                            notes[21].stop();
                            notes[21].release();
                        }else if(view.getId()==frets[4][2].getId()){
                            notes[22].stop();
                            notes[22].release();
                        }else if(view.getId()==frets[4][3].getId()){
                            notes[23].stop();
                            notes[23].release();
                        }else if(view.getId()==frets[4][4].getId()){
                            notes[24].stop();
                            notes[24].release();
                        }else if(view.getId()==frets[5][0].getId()){
                            notes[25].stop();
                            notes[25].release();
                        }else if(view.getId()==frets[5][1].getId()){
                            notes[26].stop();
                            notes[26].release();
                        }else if(view.getId()==frets[5][2].getId()){
                            notes[27].stop();
                            notes[27].release();
                        }else if(view.getId()==frets[5][3].getId()){
                            notes[28].stop();
                            notes[28].release();
                        }else if(view.getId()==frets[5][4].getId()){
                            notes[29].stop();
                            notes[29].release();
                        }
                        if (scaleSelected==0) {
                            //Only changes background color if scale is not selected.
                            view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.rosewood));
                        }
                    }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sets the volume control. This makes sure the user can turn the media volume up and down.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //Declares the spinner displaying the scales, declares the adapter and set a new OnItemSelectedListener.
        final Spinner scaleSpinner = (Spinner) findViewById(R.id.scales_spinner);
        ArrayAdapter<CharSequence> scalesAdapter = ArrayAdapter.createFromResource(this, R.array.scale_spinner_array, R.layout.support_simple_spinner_dropdown_item);
        scalesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(scalesAdapter);
        scaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //scaleSelected is used later.
                scaleSelected = position;
                //Resets colours.
                resetColours();
                //If the scale selected is not "No Scale", it highlights the relevant frets.
                if(scaleSelected!=0){
                    highlightFrets();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bpmPicker = (EditText) findViewById(R.id.bpm_picker);
        //Declare fret buttons in an array of arrays.
        frets[0] = new Button[]{
                (Button) findViewById(R.id.string_1_fret_1),
                (Button) findViewById(R.id.string_1_fret_2),
                (Button) findViewById(R.id.string_1_fret_3),
                (Button) findViewById(R.id.string_1_fret_4),
                (Button) findViewById(R.id.string_1_fret_5)
        };
        frets[1] = new Button[]{
                (Button) findViewById(R.id.string_2_fret_1),
                (Button) findViewById(R.id.string_2_fret_2),
                (Button) findViewById(R.id.string_2_fret_3),
                (Button) findViewById(R.id.string_2_fret_4),
                (Button) findViewById(R.id.string_2_fret_5)
        };
        frets[2] = new Button[]{
                (Button) findViewById(R.id.string_3_fret_1),
                (Button) findViewById(R.id.string_3_fret_2),
                (Button) findViewById(R.id.string_3_fret_3),
                (Button) findViewById(R.id.string_3_fret_4),
                (Button) findViewById(R.id.string_3_fret_5)
        };
        frets[3] = new Button[]{
                (Button) findViewById(R.id.string_4_fret_1),
                (Button) findViewById(R.id.string_4_fret_2),
                (Button) findViewById(R.id.string_4_fret_3),
                (Button) findViewById(R.id.string_4_fret_4),
                (Button) findViewById(R.id.string_4_fret_5)
        };
        frets[4] = new Button[]{
                (Button) findViewById(R.id.string_5_fret_1),
                (Button) findViewById(R.id.string_5_fret_2),
                (Button) findViewById(R.id.string_5_fret_3),
                (Button) findViewById(R.id.string_5_fret_4),
                (Button) findViewById(R.id.string_5_fret_5)
        };
        frets[5] = new Button[]{
                (Button) findViewById(R.id.string_6_fret_1),
                (Button) findViewById(R.id.string_6_fret_2),
                (Button) findViewById(R.id.string_6_fret_3),
                (Button) findViewById(R.id.string_6_fret_4),
                (Button) findViewById(R.id.string_6_fret_5)
        };
        for (View[] button : frets) {
            for (View i : button) {
                i.setOnTouchListener(fretsOnTouch);
            }
        }
        //Calls methods to define scales and chords.
        defineScales();
        defineChords();
        //Declares start button and sets the OnClickListener.
        metronomeStart = (Button) findViewById(R.id.start_button);
        metronomeStart.setOnClickListener(startOnClickListener);
        //Sets text for the bpm edit text and the start button.
        bpmPicker.setText(bpm);
        metronomeStart.setText(R.string.start);
        //Creates a new inputfilter for the bpm edit text to make sure the user does not input unrealistic values.
        bpmPicker.setFilters(new InputFilter[]{new InputFilterMinMax(1, 720)});
        click = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.MainActivity.this, R.raw.click);
        //Starts the thread for the metronomeHandler.
        startMetronomeHandlerThread();
    }

    public void bpmChange(View view) {
        //This method is called in the xml.
        //Switch case on ids.
        switch (view.getId()) {
            case R.id.bpm_button_down:
                //If the string is empty, it sets bpm to 720.
                if (bpmPicker.getText().toString().trim().equals("")) {
                    bpmInt = 720;
                } else {
                    //Gets bpm from the current string.
                    bpmInt = Integer.parseInt(bpmPicker.getText().toString().trim());
                    if (bpmInt != 1) {
                        //If bpm is not 1, increments bpm by -1. If it is 1, it sets it to 720
                        bpmInt--;
                    } else bpmInt = 720;
                }
                break;
            case R.id.bpm_button_up:
                if (bpmPicker.getText().toString().trim().equals("")) {
                    bpm = "1";
                    bpmPicker.setText(bpm);
                    bpmInt = Integer.parseInt(bpm);
                } else {
                    bpmInt = Integer.parseInt(bpmPicker.getText().toString().trim());
                    if (bpmInt != 720) {
                        bpmInt++;
                    } else bpmInt = 1;
                }
                break;
        }
        //Takes the bpmInt value and sets the bpmPicker text.
        bpm = Integer.toString(bpmInt);
        bpmPicker.setText(bpm);

    }

    public void startMetronome(View view) {
        //If bpmPicker has an empty string, displays a toast asking the user to input a value.
        if (bpmPicker.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please input a BPM value", Toast.LENGTH_LONG).show();
        } else {
            //Resets colours, sets a boolean, sets bpmInt from the text on bpmPicker, changes the text on the start button,
            // sets delay to a minute divided by the bpm.
            resetColours();
            playScale=true;
            bpmInt = Integer.parseInt(bpmPicker.getText().toString());
            metronomeStart.setText(R.string.stop);
            delay = 60000 / bpmInt;
            //Sets a boolean to false, sets all relevant ints to 0, post the runnable to the handler.
            stopRunnable = false;
            cycle = 0;
            step = 0;
            string = 0;
            metronomeHandler.post(metronomeRunnable);
        }
    }

    public void stopMetronome(View view) {
        //Changes start button text, sets some booleans and resets colours.
        metronomeStart.setText(R.string.start);
        stopRunnable = true;
        //playChords = false;
        playScale=false;
        resetColours();
        highlightFrets();
    }

    public void startMetronomeHandlerThread() {
        //Declares the HandlerThread for the metronome and sets the priority.
        HandlerThread metronomeHandlerThread = new HandlerThread("MetronomeThread", Process.THREAD_PRIORITY_BACKGROUND);
        //Starts the thread.
        metronomeHandlerThread.start();
        //Declares the Handler and gets the looper from the thread.
        metronomeHandler = new Handler(metronomeHandlerThread.getLooper());
    }

    private void highlightFrets() {
        /*Could be reimplemented later.
        if (playChords) {
            fretButtonColour(0, 0, 0, 0);
        } else */
            if(playScale&&scaleSelected!=0) {
            if (cycle == 0) {
                if (string > 0 && step > 0) {
                    fretButtonColour(string, scaleList.get(scaleSelected-1)[string][step - 1], string, scaleList.get(scaleSelected-1)[string][step]);
                } else if (step > 0) {
                    fretButtonColour(string, scaleList.get(scaleSelected-1)[string][step - 1], string, scaleList.get(scaleSelected-1)[string][step]);
                } else if (string > 0) {
                    fretButtonColour(string - 1, scaleList.get(scaleSelected-1)[string - 1][scaleList.get(scaleSelected-1)[string - 1].length - 1], string, scaleList.get(scaleSelected)[string][step]);
                } else if (string == 0 && step == 0) {
                    fretButtonColour(scaleList.get(scaleSelected-1).length - 1, scaleList.get(scaleSelected-1)[scaleList.get(scaleSelected-1).length - 1][scaleList.get(scaleSelected)[scaleList.get(scaleSelected).length - 1].length - 1], string, scaleList.get(scaleSelected)[string][step]);
                }
                if (string == scaleList.get(scaleSelected-1).length - 1 && step == scaleList.get(scaleSelected-1)[string].length - 1) {
                    cycle = 1;
                } else if (step == scaleList.get(scaleSelected-1)[string].length - 1) {
                    step = 0;
                    string++;
                } else step++;
            } else if (cycle == 1) {
                if (string > 0 && step > 0) {
                    fretButtonColour(string, scaleList.get(scaleSelected-1)[string][step], string, scaleList.get(scaleSelected-1)[string][step - 1]);
                } else if (step > 0) {
                    fretButtonColour(string, scaleList.get(scaleSelected-1)[string][step], string, scaleList.get(scaleSelected-1)[string][step - 1]);
                } else if (string > 0) {
                    fretButtonColour(string, scaleList.get(scaleSelected-1)[string][step],
                            string - 1, scaleList.get(scaleSelected-1)[string - 1][scaleList.get(scaleSelected-1)[string - 1].length - 1]);
                }
                if (string == 0 && step == 1) {
                    cycle = 0;
                } else if (step == 0) {
                    string--;
                    step = scaleList.get(scaleSelected-1)[string].length - 1;
                } else step--;
            }
        } else if(scaleSelected!=0){
            for (int i=0;i<scaleList.get(scaleSelected-1).length;i++){
                for (int j=0;j<scaleList.get(scaleSelected-1)[i].length;j++){
                    frets[i][scaleList.get(scaleSelected-1)[i][j]].setBackgroundColor(Color.YELLOW);
                }
            }
        }
    }

    private void resetColours() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //foreach to reset the colours.
                for (View[] i : frets) {
                    for (View item : i) {
                        item.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.rosewood));
                    }
                }
            }
        });
    }

    private void fretButtonColour(final int previousStepString, final int previousFret, final int stepString, final int fret) {
        //UI changes must be made on UI thread.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /* This could be reimplemented later.
                if (playChords) {
                    if (previousChordIndex != -1) {
                        resetColours();
                        if (chordCounter == chordAmount) {
                            chordCounter = 0;
                            previousChordIndex = -1;


                        }
                    }
                    for (int i = 0; i < chordsPicked.length; i++) {
                        if (chordsPicked[i] && i > previousChordIndex) {
                            chordCounter++;
                            for (int j = 0; j < chordList.get(i).length; j++) {
                                if (chordList.get(i)[j] != -1) {
                                    frets[j][chordList.get(i)[j]].setBackgroundColor(Color.YELLOW);
                                }
                            }
                            previousChordIndex = i;
                            break;
                        }
                    }


                } else {*/
                    //Sets background colour back to the default colour.
                    frets[previousStepString][previousFret].setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.rosewood));
                    //Sets background colour to yellow.
                    frets[stepString][fret].setBackgroundColor(Color.YELLOW);
                //}
            }
        });
    }
//Starts the chords activity.
    public void pickChords(View view) {
        Intent pickChordsIntent = new Intent(this, ChordsActivity.class);
        startActivity(pickChordsIntent);
        //startActivityForResult(pickChordsIntent, PICK_CHORDS_REQUEST);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CHORDS_REQUEST) {
            if (resultCode == RESULT_OK) {
                chordsPicked = data.getBooleanArrayExtra("chordsPicked");
                chordAmount = 0;
                for (boolean i : chordsPicked) {
                    if (i) chordAmount++;
                }
                playChords = true;
                bpmInt = Integer.parseInt(bpmPicker.getText().toString());
                metronomeStart.setText(R.string.stop);
                delay = 60000 / bpmInt;
                stopRunnable = false;
                chordCounter = 0;
                previousChordIndex = -1;
                cycle = 0;
                step = 0;
                string = 0;
                startStopInt = 1;
                metronomeHandler.post(metronomeRunnable);
            }
        }
    }*/

    private void defineScales() {
        int[][] lydian = new int[6][];
        lydian[0] = new int[]
                {0, 1, 3};
        lydian[1] = new int[]
                {0, 1, 3};
        lydian[2] = new int[]
                {0, 2};
        lydian[3] = new int[]
                {0, 2, 3};
        lydian[4] = new int[]
                {0, 2, 3};
        lydian[5] = new int[]
                {0, 1, 3};
        scaleList.add(lydian);
        int[][] ionian = new int[6][];
        ionian[0] = new int[]
                {0, 1, 3};
        ionian[1] = new int[]
                {1, 3};
        ionian[2] = new int[]
                {0, 2, 3};
        ionian[3] = new int[]
                {0, 2, 3};
        ionian[4] = new int[]
                {0, 1, 3};
        ionian[5] = new int[]
                {0, 1, 3};
        scaleList.add(ionian);
        int[][] mixolydian = new int[6][];
        mixolydian[0] = new int[]
                {1, 3};
        mixolydian[1] = new int[]
                {1, 3, 4};
        mixolydian[2] = new int[]
                {0, 2, 3};
        mixolydian[3] = new int[]
                {0, 1, 3};
        mixolydian[4] = new int[]
                {0, 1, 3};
        mixolydian[5] = new int[]
                {1, 3};
        scaleList.add(mixolydian);
        int[][] dorian = new int[6][];
        dorian[0] = new int[]
                {1, 3, 4};
        dorian[1] = new int[]
                {1, 3, 4};
        dorian[2] = new int[]
                {0, 1, 3};
        dorian[3] = new int[]
                {0, 1, 3};
        dorian[4] = new int[]
                {1, 3};
        dorian[5] = new int[]
                {1, 3, 4};
        scaleList.add(dorian);
        int[][] aeolian = new int[6][];
        aeolian[0] = new int[]
                {1, 3, 4};
        aeolian[1] = new int[]
                {1, 3, 4};
        aeolian[2] = new int[]
                {1, 3};
        aeolian[3] = new int[]
                {0, 1, 3};
        aeolian[4] = new int[]
                {1, 2, 4};
        aeolian[5] = new int[]
                {1, 3, 4};
        scaleList.add(aeolian);
        int[][] phrygian = new int[6][];
        phrygian[0] = new int[]
                {1, 2, 4};
        phrygian[1] = new int[]
                {1, 2, 4};
        phrygian[2] = new int[]
                {1, 3};
        phrygian[3] = new int[]
                {1, 3, 4};
        phrygian[4] = new int[]
                {1, 3, 4};
        phrygian[5] = new int[]
                {1, 2, 4};
        scaleList.add(phrygian);
        int[][] locrian = new int[6][];
        locrian[0] = new int[]
                {1, 2, 4};
        locrian[1] = new int[]
                {2, 4};
        locrian[2] = new int[]
                {1, 3, 4};
        locrian[3] = new int[]
                {1, 3, 4};
        locrian[4] = new int[]
                {1, 2, 4};
        locrian[5] = new int[]
                {1, 2, 4};
        scaleList.add(locrian);
    }

    private void defineChords() {
        //Define arrays for chords and add them to a list, making sure the index in the list fits the order in the boolean array.
        int[] a = {-1, -1, 1, 1, 1, -1};
        chordList.add(a);
        int[] am = {-1, -1, 1, 1, 0, -1};
        chordList.add(am);
        int[] a2 = {-1, -1, 1, 1, -1, -1};
        chordList.add(a2);
        int[] a7 = {-1, -1, 1, -1, 1, -1};
        chordList.add(a7);
        int[] am7 = {-1, -1, 1, -1, 0, -1};
        chordList.add(am7);
        int[] b = {-1, 1, 3, 3, 3, 1};
        chordList.add(b);
        int[] b7 = {-1, 1, 3, 1, 3, 1};
        chordList.add(b7);
        int[] bm = {-1, 1, 3, 3, 2, 1};
        chordList.add(bm);
        int[] bm7 = {-1, 1, 3, 1, 2, 1};
        chordList.add(bm7);
        int[] c = {-1, 2, 1, -1, 0, -1};
        chordList.add(c);
        int[] c7 = {-1, 2, 1, 2, 0, -1};
        chordList.add(c7);
        int[] cadd2 = {-1, 2, 1, -1, 2, -1};
        chordList.add(cadd2);
        int[] cm = {-1, 2, 4, 4, 3, 2};
        chordList.add(cm);
        int[] cmaj7 = {-1, 2, 1, -1, -1, -1};
        chordList.add(cmaj7);
        int[] d = {-1, -1, -1, 1, 2, 1};
        chordList.add(d);
        int[] d7 = {-1, -1, -1, 1, 0, 1};
        chordList.add(d7);
        int[] dm = {-1, -1, -1, 1, 2, 0};
        chordList.add(dm);
        int[] dm7 = {-1, -1, -1, 1, 0, 0};
        chordList.add(dm7);
        int[] e = {-1, 1, 1, 0, -1, -1};
        chordList.add(e);
        int[] e7 = {-1, 1, -1, 0, -1, -1};
        chordList.add(e7);
        int[] em = {-1, 1, 1, -1, -1, -1};
        chordList.add(em);
        int[] em7 = {-1, 1, -1, -1, -1, -1};
        chordList.add(em7);
        int[] f = {0, 2, 2, 1, 0, 0};
        chordList.add(f);
        int[] f7 = {0, 2, 0, 1, 0, 0};
        chordList.add(f7);
        int[] fm = {0, 2, 2, 0, 0, 0};
        chordList.add(fm);
        int[] fm7 = {0, 2, 0, 0, 0, 0};
        chordList.add(fm7);
        int[] g = {2, 1, -1, -1, 2, 2};
        chordList.add(g);
        int[] g7 = {2, 1, -1, -1, -1, 0};
        chordList.add(g7);
        int[] gm = {2, 4, 4, 2, 2, 2};
        chordList.add(gm);
        int[] gm7 = {2, 4, 2, 2, 2, 2};
        chordList.add(gm7);
    }

}


