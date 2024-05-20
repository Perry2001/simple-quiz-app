package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {
            "56 + 63",
            "159 + 286",
            "145 - 67",
            "289 - 333",
            "What is the capital of the Philippines?"
    };

    String[] choose_list = {
            "119", "129", "109",
            "455", "445", "435",
            "76", "68", "78",
            "-34", "-44", "-43",
            "Cagayan", "Pangasinan", "Manila"
    };

    String[] correct_list = {
            "119", "445", "78", "-44", "Manila"
    };

    TextView cpt_question, text_question;
    Button btn_choose1, btn_choose2, btn_choose3, btn_next;

    int currentQuestion = 0;
    int scorePlayer = 0;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(a -> finish());

        // Set ClickChoose as the onClick listener for the buttons
        btn_choose1.setOnClickListener(this::ClickChoose);
        btn_choose2.setOnClickListener(this::ClickChoose);
        btn_choose3.setOnClickListener(this::ClickChoose);

        remplirData();

        btn_next.setOnClickListener(view -> {
            if (isclickBtn) {
                isclickBtn = false;

                if (!valueChoose.equals(correct_list[currentQuestion])) {
                    Toast.makeText(playActivity.this, "Wrong", Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_erreur);
                } else {
                    Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                    scorePlayer++;
                }

                new Handler().postDelayed(() -> {
                    if (currentQuestion != question_list.length - 1) {
                        currentQuestion++;
                        remplirData();
                        valueChoose = "";
                        btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                        btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                        btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                    } else {
                        Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                        intent.putExtra("Result", scorePlayer);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            } else {
                Toast.makeText(playActivity.this, "You must choose an answer", Toast.LENGTH_LONG).show();
            }
        });
    }

    void remplirData() {
        cpt_question.setText((currentQuestion + 1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[3 * currentQuestion]);
        btn_choose2.setText(choose_list[3 * currentQuestion + 1]);
        btn_choose3.setText(choose_list[3 * currentQuestion + 2]);
    }

    public void ClickChoose(View view) {
        btn_click = (Button) view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();
    }

    void chooseBtn() {
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}
