package com.winter.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        menus = getResources().getStringArray(R.array.main_menu);
        recyclerView.setAdapter(new MyAdapter());
        //LayoutManager是必须的，否则无法显示。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            this.textView = (AppCompatTextView) itemView;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.item_main_menu, parent, false);
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, int position) {
            holder.textView.setText(menus[position]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Toast.makeText(MainActivity.this, "position " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return menus.length;
        }
    }
}
