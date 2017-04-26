package com.pfi2017.mea_a2b217b.guitarsimulator;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class ChordsActivity extends AppCompatActivity {
    MediaPlayer[] chords = new MediaPlayer[30];
    Button[][] chordsButtons = new Button[6][];
    private View.OnTouchListener chordsOnTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (view.getId() == chordsButtons[0][0].getId()) {
                    chords[0] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_a);
                    chords[0].start();
                } else if (view.getId() == chordsButtons[0][1].getId()) {
                    chords[1] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_am);
                    chords[1].start();
                } else if (view.getId() == chordsButtons[0][2].getId()) {
                    chords[2] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_a2);
                    chords[2].start();
                } else if (view.getId() == chordsButtons[0][3].getId()) {
                    chords[3] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_a7);
                    chords[3].start();
                } else if (view.getId() == chordsButtons[0][4].getId()) {
                    chords[4] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_am7);
                    chords[4].start();
                } else if (view.getId() == chordsButtons[1][0].getId()) {
                    chords[5] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_b);
                    chords[5].start();
                } else if (view.getId() == chordsButtons[1][1].getId()) {
                    chords[6] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_b7);
                    chords[6].start();
                } else if (view.getId() == chordsButtons[1][2].getId()) {
                    chords[7] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_bm);
                    chords[7].start();
                } else if (view.getId() == chordsButtons[1][3].getId()) {
                    chords[8] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_bm7);
                    chords[8].start();
                } else if (view.getId() == chordsButtons[1][4].getId()) {
                    chords[9] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_c);
                    chords[9].start();
                } else if (view.getId() == chordsButtons[2][0].getId()) {
                    chords[10] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_c7);
                    chords[10].start();
                } else if (view.getId() == chordsButtons[2][1].getId()) {
                    chords[11] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_cadd2);
                    chords[11].start();
                } else if (view.getId() == chordsButtons[2][2].getId()) {
                    chords[12] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_cm);
                    chords[12].start();
                } else if (view.getId() == chordsButtons[2][3].getId()) {
                    chords[13] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_cmaj7);
                    chords[13].start();
                } else if (view.getId() == chordsButtons[2][4].getId()) {
                    chords[14] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_d);
                    chords[14].start();
                } else if (view.getId() == chordsButtons[3][0].getId()) {
                    chords[15] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_d7);
                    chords[15].start();
                } else if (view.getId() == chordsButtons[3][1].getId()) {
                    chords[16] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_dm);
                    chords[16].start();
                } else if (view.getId() == chordsButtons[3][2].getId()) {
                    chords[17] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_dm7);
                    chords[17].start();
                } else if (view.getId() == chordsButtons[3][3].getId()) {
                    chords[18] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_e);
                    chords[18].start();
                } else if (view.getId() == chordsButtons[3][4].getId()) {
                    chords[19] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_e7);
                    chords[19].start();
                } else if (view.getId() == chordsButtons[4][0].getId()) {
                    chords[20] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_em);
                    chords[20].start();
                } else if (view.getId() == chordsButtons[4][1].getId()) {
                    chords[21] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_em7);
                    chords[21].start();
                } else if (view.getId() == chordsButtons[4][2].getId()) {
                    chords[22] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_f);
                    chords[22].start();
                } else if (view.getId() == chordsButtons[4][3].getId()) {
                    chords[23] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_f7);
                    chords[23].start();
                } else if (view.getId() == chordsButtons[4][4].getId()) {
                    chords[24] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_fm);
                    chords[24].start();
                } else if (view.getId() == chordsButtons[5][0].getId()) {
                    chords[25] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_fm7);
                    chords[25].start();
                } else if (view.getId() == chordsButtons[5][1].getId()) {
                    chords[26] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_g);
                    chords[26].start();
                } else if (view.getId() == chordsButtons[5][2].getId()) {
                    chords[27] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_g7);
                    chords[27].start();
                } else if (view.getId() == chordsButtons[5][3].getId()) {
                    chords[28] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_gm);
                    chords[28].start();
                } else if (view.getId() == chordsButtons[5][4].getId()) {
                    chords[29] = MediaPlayer.create(com.pfi2017.mea_a2b217b.guitarsimulator.ChordsActivity.this, R.raw.chord_gm7);
                    chords[29].start();
                }
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (view.getId() == chordsButtons[0][0].getId()) {
                    chords[0].stop();
                    chords[0].release();
                } else if (view.getId() == chordsButtons[0][1].getId()) {
                    chords[1].stop();
                    chords[1].release();
                } else if (view.getId() == chordsButtons[0][2].getId()) {
                    chords[2].stop();
                    chords[2].release();
                } else if (view.getId() == chordsButtons[0][3].getId()) {
                    chords[3].stop();
                    chords[3].release();
                } else if (view.getId() == chordsButtons[0][4].getId()) {
                    chords[4].stop();
                    chords[4].release();
                } else if (view.getId() == chordsButtons[1][0].getId()) {
                    chords[5].stop();
                    chords[5].release();
                } else if (view.getId() == chordsButtons[1][1].getId()) {
                    chords[6].stop();
                    chords[6].release();
                } else if (view.getId() == chordsButtons[1][2].getId()) {
                    chords[7].stop();
                    chords[7].release();
                } else if (view.getId() == chordsButtons[1][3].getId()) {
                    chords[8].stop();
                    chords[8].release();
                } else if (view.getId() == chordsButtons[1][4].getId()) {
                    chords[9].stop();
                    chords[9].release();
                } else if (view.getId() == chordsButtons[2][0].getId()) {
                    chords[10].stop();
                    chords[10].release();
                } else if (view.getId() == chordsButtons[2][1].getId()) {
                    chords[11].stop();
                    chords[11].release();
                } else if (view.getId() == chordsButtons[2][2].getId()) {
                    chords[12].stop();
                    chords[12].release();
                } else if (view.getId() == chordsButtons[2][3].getId()) {
                    chords[13].stop();
                    chords[13].release();
                } else if (view.getId() == chordsButtons[2][4].getId()) {
                    chords[14].stop();
                    chords[14].release();
                } else if (view.getId() == chordsButtons[3][0].getId()) {
                    chords[15].stop();
                    chords[15].release();
                } else if (view.getId() == chordsButtons[3][1].getId()) {
                    chords[16].stop();
                    chords[16].release();
                } else if (view.getId() == chordsButtons[3][2].getId()) {
                    chords[17].stop();
                    chords[17].release();
                } else if (view.getId() == chordsButtons[3][3].getId()) {
                    chords[18].stop();
                    chords[18].release();
                } else if (view.getId() == chordsButtons[3][4].getId()) {
                    chords[19].stop();
                    chords[19].release();
                } else if (view.getId() == chordsButtons[4][0].getId()) {
                    chords[20].stop();
                    chords[20].release();
                } else if (view.getId() == chordsButtons[4][1].getId()) {
                    chords[21].stop();
                    chords[21].release();
                } else if (view.getId() == chordsButtons[4][2].getId()) {
                    chords[22].stop();
                    chords[22].release();
                } else if (view.getId() == chordsButtons[4][3].getId()) {
                    chords[23].stop();
                    chords[23].release();
                } else if (view.getId() == chordsButtons[4][4].getId()) {
                    chords[24].stop();
                    chords[24].release();
                } else if (view.getId() == chordsButtons[5][0].getId()) {
                    chords[25].stop();
                    chords[25].release();
                } else if (view.getId() == chordsButtons[5][1].getId()) {
                    chords[26].stop();
                    chords[26].release();
                } else if (view.getId() == chordsButtons[5][2].getId()) {
                    chords[27].stop();
                    chords[27].release();
                } else if (view.getId() == chordsButtons[5][3].getId()) {
                    chords[28].stop();
                    chords[28].release();
                } else if (view.getId() == chordsButtons[5][4].getId()) {
                    chords[29].stop();
                    chords[29].release();
                }
            }

            return false;
        }
    };

    //boolean[] chordsPicked=new boolean[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        /*for(int i=0;i<chordsPicked.length;i++){
            chordsPicked[i]=false;
        }*/
        chordsButtons[0] = new Button[]{
                (Button) findViewById(R.id.Button_a),
                (Button) findViewById(R.id.Button_am),
                (Button) findViewById(R.id.Button_a2),
                (Button) findViewById(R.id.Button_a7),
                (Button) findViewById(R.id.Button_am7)
        };
        chordsButtons[1] = new Button[]{
                (Button) findViewById(R.id.Button_b),
                (Button) findViewById(R.id.Button_b7),
                (Button) findViewById(R.id.Button_bm),
                (Button) findViewById(R.id.Button_bm7),
                (Button) findViewById(R.id.Button_c)
        };
        chordsButtons[2] = new Button[]{
                (Button) findViewById(R.id.Button_c7),
                (Button) findViewById(R.id.Button_cadd2),
                (Button) findViewById(R.id.Button_cm),
                (Button) findViewById(R.id.Button_cmaj7),
                (Button) findViewById(R.id.Button_d)
        };
        chordsButtons[3] = new Button[]{
                (Button) findViewById(R.id.Button_d7),
                (Button) findViewById(R.id.Button_dm),
                (Button) findViewById(R.id.Button_dm7),
                (Button) findViewById(R.id.Button_e),
                (Button) findViewById(R.id.Button_e7)
        };
        chordsButtons[4] = new Button[]{
                (Button) findViewById(R.id.Button_em),
                (Button) findViewById(R.id.Button_em7),
                (Button) findViewById(R.id.Button_f),
                (Button) findViewById(R.id.Button_f7),
                (Button) findViewById(R.id.Button_fm)
        };
        chordsButtons[5] = new Button[]{
                (Button) findViewById(R.id.Button_fm7),
                (Button) findViewById(R.id.Button_g),
                (Button) findViewById(R.id.Button_g7),
                (Button) findViewById(R.id.Button_gm),
                (Button) findViewById(R.id.Button_gm7)
        };
        for (View[] button : chordsButtons) {
            for (View i : button) {
                i.setOnTouchListener(chordsOnTouch);
            }
        }
//        Intent pickChords=getIntent();
    }
/*public void onChordsCheckboxClicked(View view){
    boolean checked=((CheckBox) view).isChecked();
    switch (view.getId()){
        case R.id.checkbox_a:
            if(checked) chordsPicked[0]=true;
            break;
        case R.id.checkbox_am:
            if(checked) chordsPicked[1]=true;
            break;
        case R.id.checkbox_a2:
            if(checked) chordsPicked[2]=true;
            break;
        case R.id.checkbox_a7:
            if(checked) chordsPicked[3]=true;
            break;
        case R.id.checkbox_am7:
            if(checked) chordsPicked[4]=true;
            break;
        case R.id.checkbox_b:
            if(checked) chordsPicked[5]=true;
            break;
        case R.id.checkbox_b7:
            if(checked) chordsPicked[6]=true;
            break;
        case R.id.checkbox_bm:
            if(checked) chordsPicked[7]=true;
            break;
        case R.id.checkbox_bm7:
            if(checked) chordsPicked[8]=true;
            break;
        case R.id.checkbox_c:
            if(checked) chordsPicked[9]=true;
            break;
        case R.id.checkbox_c7:
            if(checked) chordsPicked[10]=true;
            break;
        case R.id.checkbox_cadd2:
            if(checked) chordsPicked[11]=true;
            break;
        case R.id.checkbox_cm:
            if(checked) chordsPicked[12]=true;
            break;
        case R.id.checkbox_cmaj7:
            if(checked) chordsPicked[13]=true;
            break;
        case R.id.checkbox_d:
            if(checked) chordsPicked[14]=true;
            break;
        case R.id.checkbox_d7:
            if(checked) chordsPicked[15]=true;
            break;
        case R.id.checkbox_dm:
            if(checked) chordsPicked[16]=true;
            break;
        case R.id.checkbox_dm7:
            if(checked) chordsPicked[17]=true;
            break;
        case R.id.checkbox_e:
            if(checked) chordsPicked[18]=true;
            break;
        case R.id.checkbox_e7:
            if(checked) chordsPicked[19]=true;
            break;
        case R.id.checkbox_em:
            if(checked) chordsPicked[20]=true;
            break;
        case R.id.checkbox_em7:
            if(checked) chordsPicked[21]=true;
            break;
        case R.id.checkbox_f:
            if(checked) chordsPicked[22]=true;
            break;
        case R.id.checkbox_f7:
            if(checked) chordsPicked[23]=true;
            break;
        case R.id.checkbox_fm:
            if(checked) chordsPicked[24]=true;
            break;
        case R.id.checkbox_fm7:
            if(checked) chordsPicked[25]=true;
            break;
        case R.id.checkbox_g:
            if(checked) chordsPicked[26]=true;
            break;
        case R.id.checkbox_g7:
            if(checked) chordsPicked[27]=true;
            break;
        case R.id.checkbox_gm:
            if(checked) chordsPicked[28]=true;
            break;
        case R.id.checkbox_gm7:
            if(checked) chordsPicked[29]=true;
            break;
    }
}*/
/*public void onStartButtonClicked(View view){
    Intent returnIntent=new Intent();
    returnIntent.putExtra("chordsPicked",chordsPicked);
    setResult(RESULT_OK, returnIntent);
    finish();
}*/
}
