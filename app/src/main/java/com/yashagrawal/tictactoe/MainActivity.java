package com.yashagrawal.tictactoe;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int tapCount = 0;
    // 0 - x;
    //  1 - o
    int gameState[] = {2,2,2,2,2,2,2,2,2};
 /*    state meanings
   0 -> x;
    1 ->O;
    2 -> null
  */
    int[][] winPositions = {{0,1,2},
                           {3,4,5},
                           {6,7,8},
                           {0,3,6},
                           {1,4,7},
                           {2,5,8},
                           {2,4,6},
                           {0,4,8}};
    public void playerTap(View view) {
        if (!gameActive) {
            // Reset the game
            resetGame(view);
        }
        else {
            TextView status = findViewById(R.id.status);
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                tapCount++;
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    status.setText("0's turn , tap to play . . .");
                } else {
                    activePlayer = 0;
                    img.setImageResource(R.drawable.o);

                    status.setText("X's turn , tap to play . . .");
                }
                // Below code will move the img upside down
                img.setTranslationY(1000f);    //this is only for creating animation

                // Below code will move the img downside up
                img.animate().translationYBy(-1000f).setDuration(300);   //this is only for creating animation
            }
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
//               SomeOne has win , find out who
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        status.setText("X has won\nTap any block");
                    }
                    else {
                        status.setText("0 has won\nTap any block");
                    }
                    return;
                }
                else if (tapCount >= 9){
                    gameActive = false;
                    status.setText("Draw\nTap any block");
                }
            }
        }

    }

    private void resetGame(View view) {
        Button ok,cancel;
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        cancel = dialog.findViewById(R.id.cancel);
        ok = dialog.findViewById(R.id.ok);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                gameActive = true;
                activePlayer = 0;
                tapCount = 0;
                for (int i = 0; i < gameState.length; i++) {
                    gameState[i] = 2;
                }
                ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
                TextView status = findViewById(R.id.status);
                status.setText("X's turn , tap to play . . .");
            }
        });
    }

    @Override
        protected void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
                // Player tap
        }

}