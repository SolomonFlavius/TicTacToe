package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    boolean gameActive = true;
    boolean activePlayer = true;//true yellow false red
    int[] game = {0,0,0,0,0,0,0,0,0};//0 none 1 yellow 2 red

    public void Drop(View view)
    {
        ImageView image = (ImageView) view;

        int tag = Integer.parseInt(image.getTag().toString());

        if(game[tag] == 0 && gameActive)
        {
            PieceMoving(image);

            if (activePlayer) {
                game[tag]=1;
                image.setImageResource(R.drawable.yellow);
            }
            else {
                game[tag]=2;
                image.setImageResource(R.drawable.red);
             }
            activePlayer = !activePlayer;//change player
        }


        if(WinningCondition())
            Winner(!activePlayer);//it is !activePlayer because we change the active player after every move and before the winning condition
        else if(Draw())
        {
            gameActive = false;

            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

            winnerTextView.setText("DRAW");

            playAgainButton.setVisibility(View.VISIBLE);

            winnerTextView.setVisibility(View.VISIBLE);
        }



    }

    public void PieceMoving(ImageView image)
    {
        image.setTranslationY(-1500);
        image.animate().translationYBy(1500).rotation(3600).setDuration(300);
    }

    public boolean Draw()
    {
        int i;
        for(i=0 ; i<9 ; i++)
        {
            if(game[i]==0) {
                return false;
            }
        }
        return true;
    }


    public void Winner(boolean activePlayer)
    {
        gameActive = false;

        String winner = "";

        if (activePlayer == true)
        {
            winner = "Yellow";
        }
        else
        {
            winner = "Red";
        }

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setText(winner + " has won!");

        playAgainButton.setVisibility(View.VISIBLE);

        winnerTextView.setVisibility(View.VISIBLE);
    }

    public void PlayAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        int i;
        //reseting the grid and the behind game logic
        for (i = 0; i < 9; i++) {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
            game[i] = 0;
        }

        activePlayer = true;

        gameActive = true;


    }

    public boolean WinningCondition()
    {
        int i;
        if(game[0] == game[1] && game[1] == game[2] && game[2]!=0)
            return true;
        if(game[3] == game[4] && game[4] == game[5] && game[5]!=0)
            return true;
        if(game[6] == game[7] && game[7] == game[8] && game[8]!=0)
            return true;
        if(game[0] == game[3] && game[3] == game[6] && game[6]!=0)
            return true;
        if(game[1] == game[4] && game[4] == game[7] && game[7]!=0)
            return true;
        if(game[2] == game[5] && game[5] == game[8] && game[8]!=0)
            return true;
        if(game[0] == game[4] && game[4] == game[8] && game[8]!=0)
            return true;
        if(game[2] == game[4] && game[4] == game[6] && game[6]!=0)
            return true;
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}