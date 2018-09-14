package ru.sequenia.bouncece;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ru.sequenia.bouncece.nestedscrollview.NestedScrollViewActivity;
import ru.sequenia.bouncece.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        RecyclerViewActivity.class));
            }
        });

        findViewById(R.id.nestedscrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        NestedScrollViewActivity.class));
            }
        });
    }
}
