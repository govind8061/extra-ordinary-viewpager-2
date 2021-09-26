package com.example.extraordinaryviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.extraordinaryviewpager.Fragments.FiveFragment;
import com.example.extraordinaryviewpager.Fragments.FourFragment;
import com.example.extraordinaryviewpager.Fragments.OneFragment;
import com.example.extraordinaryviewpager.Fragments.ThreeFragment;
import com.example.extraordinaryviewpager.Fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragmentList;
    ViewPager2 pager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList=new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());
        fragmentList.add(new FiveFragment());

        pager2=findViewById(R.id.pager2);
        SliderAdapter adapter=new SliderAdapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        pager2.setAdapter(adapter);
        pager2.setOffscreenPageLimit(3);
        pager2.setClipToPadding(false);
        pager2.setClipChildren(false);
        pager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer pageTransformer=new CompositePageTransformer();
        pageTransformer.addTransformer(new MarginPageTransformer(40));
        pageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        pager2.setPageTransformer(pageTransformer);


    }
}