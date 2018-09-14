package ru.sequenia.bouncece.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.sequenia.bouncece.R;
import ru.sequenia.bouncece_lib.Bouncece;
import ru.sequenia.bouncece_lib.BounceceBuilder;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Adapter adapter = new Adapter(getLayoutInflater());
        adapter.setItems(getItems());
        list.setAdapter(adapter);

        Bouncece bounce = new BounceceBuilder().build();
        bounce.setContent(list);
    }

    private List<Object> getItems() {
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add("postion " + i);
        }
        return items;
    }
}