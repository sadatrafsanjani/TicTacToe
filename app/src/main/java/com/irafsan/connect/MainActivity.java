package com.irafsan.connect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private boolean flag = true;
    private byte chipsColor = 0;
    private byte position[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private byte wins[][] = { {0,1,2},{3,4,5},{6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void onTap(View view){

        ImageView chip = (ImageView) view;
        int tappedPosition = Integer.parseInt(chip.getTag().toString());

        if(position[tappedPosition] == 2 && flag){

            position[tappedPosition] = chipsColor;
            chip.setTranslationY(-1000f);

            if(chipsColor == 0){
                chip.setImageResource(R.drawable.bluechip);
                chipsColor = 1;
            }
            else{
                chip.setImageResource(R.drawable.redchip);
                chipsColor = 0;
            }

            chip.animate().translationYBy(1000f).rotation(360).setDuration(300);
            new Sound().chipDrop(this);

            for(byte w[] : wins){

                TextView messageView = (TextView) findViewById(R.id.messageView);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                if((position[w[0]] == position[w[1]]) && (position[w[1]] == position[w[2]]) && (position[w[0]] != 2)){

                    flag = false;

                    if(position[w[0]] == 0){
                        messageView.setText("Blue Won!");
                    }
                    else{
                        messageView.setText("Red Won!");
                    }

                    linearLayout.setVisibility(View.VISIBLE);
                    new Sound().endGame(this);
                }
                else{
                    boolean over = true;

                    for(int counter : position){

                        if(counter == 2){
                            over = false;
                        }
                    }

                    if(over){
                        messageView.setText("It's a draw!");
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        flag = true;
        chipsColor = 0;

        for(byte i=0; i<position.length; i++){
            position[i] = 2;
        }

        for(byte j=0; j<gridLayout.getChildCount(); j++){

            ((ImageView)gridLayout.getChildAt(j)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
