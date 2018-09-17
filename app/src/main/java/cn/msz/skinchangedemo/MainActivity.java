package cn.msz.skinchangedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.msz.skinchangedemo.base.BaseActivity;
import cn.msz.skinchangedemo.skinattr.TextSizeAttr;
import cn.msz.skinchangedemo.util.DynamicSkinAttrItem;
import cn.msz.skinchangedemo.util.LoadSkinCallback;
import cn.msz.skinchangedemo.util.SkinManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvChangeSkin = findViewById(R.id.tv_change_skin);
        TextView tvResetSkin = findViewById(R.id.tv_reset_skin);
        RecyclerView recyclerView = findViewById(R.id.rv_change_skin);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_change_skin, viewGroup, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Holder holder = (Holder) viewHolder;
                DynamicSkinAttrItem dynamicSkinAttrItem = new DynamicSkinAttrItem(MainActivity.this, holder.tvItem, new TextSizeAttr(), R.dimen.textSize14);
                skinInflaterFactory.addItem(dynamicSkinAttrItem);
            }

            @Override
            public int getItemCount() {
                return 30;
            }

            class Holder extends RecyclerView.ViewHolder {
                TextView tvItem;

                Holder(@NonNull View itemView) {
                    super(itemView);
                    tvItem = itemView.findViewById(R.id.tv_item);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvChangeSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().loadAssetSkin("black.skin", new LoadSkinCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onProgress(int progress) {

                    }
                });
            }
        });
        tvResetSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().resetDefualtSkin();
            }
        });
    }
}
