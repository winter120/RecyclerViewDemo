package com.winter.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] menus;
    private LinearLayoutManager linearLayoutManagerVertical, linearLayoutManagerHorizontal;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        menus = getResources().getStringArray(R.array.main_menu);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setTitle(R.string.base_usage);
        }
        recyclerView.setAdapter(new MyAdapter());
        //线性布局，不指定方向时，默认竖直排列。
        linearLayoutManagerVertical = new LinearLayoutManager(this);
        //第三个参数指定是否把数据反转，后面的数据先显示。
        linearLayoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //第二个参数指定表格分为几列。
        gridLayoutManager = new GridLayoutManager(this, 3);
        //瀑布流也分竖直和水平方向，就不展开说明了。
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_active, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_linear_layout_vertical:
                recyclerView.setLayoutManager(linearLayoutManagerVertical);
                break;
            case R.id.menu_linear_layout_horizontal:
                recyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                break;
            case R.id.menu_grid_layout:
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.menu_staggered_layout:
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
        }
        return super.onOptionsItemSelected(item);
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
