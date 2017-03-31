package com.android.leo.flexboxexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Map<String, Class<? extends AppCompatActivity>> mActivitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivitys = new HashMap<>();
        mActivitys.put("固定顶部布局", FixBottomActivity.class);
        mActivitys.put("流式布局", FlowLayoutActivity.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new LayoutAdapter());
    }

    private class LayoutAdapter extends RecyclerView.Adapter<LayoutHolder> {

        private Set<String> mKeys;

        LayoutAdapter() {
            mKeys = mActivitys.keySet();
        }

        @Override
        public LayoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new LayoutHolder(inflater.inflate(R.layout.item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(LayoutHolder holder, int position) {
            holder.btn.setText(String.class.cast(mKeys.toArray()[position]));
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class<? extends AppCompatActivity> clazz = mActivitys.get((TextView.class.cast(v).getText().toString()));
                    Intent intent = new Intent();
                    intent.setClass(v.getContext(), clazz);
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mActivitys.size();
        }
    }


    private class LayoutHolder extends RecyclerView.ViewHolder {

        private TextView btn;

        LayoutHolder(View itemView) {
            super(itemView);
            btn = (TextView) itemView.findViewById(R.id.btn);
        }
    }
}
