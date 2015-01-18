package com.animator.enphoneh.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    List<ImageView> imageViewList = new ArrayList<ImageView>();
    List<ImageView> imageViewListArc = new ArrayList<ImageView>();
    int[] res = {R.id.a,R.id.b,R.id.c,R.id.d,R.id.e,R.id.f,R.id.g};
    int[] resArc = {R.id.u,R.id.o,R.id.p,R.id.q,R.id.r,R.id.s,R.id.t};
    private boolean isOpen = false;
    private boolean isArcOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0 ; i < res.length ; i++){
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }
        for(int i = 0 ; i < resArc.length ; i++){
            ImageView imageView = (ImageView) findViewById(resArc[i]);
            imageView.setOnClickListener(this);
            imageViewListArc.add(imageView);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.a:
                if(!isOpen){
                    openMenu();
                    isOpen = true;
                }else{
                    closeMenu();
                    isOpen = false;
                }
                break;
            case R.id.u:
                if(!isArcOpen){
                    openArcMenu(v);
                    isArcOpen = true;
                }else{
                    closeArcMenu(v);
                    isArcOpen = false;
                }
                break;
            default:
                Toast.makeText(this," "+String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeArcMenu(View view) {
        float startX = view.getLeft();
        float startY = view.getTop();

        for(int i = 1; i < resArc.length ; i++){
            float endX = (float) ((float) 250*Math.sin(Math.toRadians(18*(i-1))));
            float endY = startY - (float) Math.sqrt(62500-(endX-startX)*(endX-startX));
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageViewListArc.get(i),
                    "X",endX,startX);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageViewListArc.get(i),
                    "Y",endY,startY);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animatorX, animatorY);
            set.setDuration(1000);
//            set.setInterpolator(new BounceInterpolator());
            set.start();
        }
    }

    private void openArcMenu(View view) {
        float startX = view.getLeft();
        float startY = view.getTop();
        for(int i = 1; i < resArc.length ; i++){
            float endX = (float) ((float) 250*Math.sin(Math.toRadians(18*(i-1))));
            float endY = startY - (float) Math.sqrt(62500-(endX-startX)*(endX-startX));
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageViewListArc.get(i),
                    "X",startX,endX);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageViewListArc.get(i),
                    "Y",startY,endY);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animatorX, animatorY);
//            set.play(animatorX).with(animatorY);
            set.setDuration(1000);
//            set.setInterpolator(new BounceInterpolator());
            set.start();
        }

    }

    private void closeMenu() {
        for(int i = 1 ; i < res.length ; i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY",0F,100*i);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
        }
    }

    private void openMenu() {
        for(int i = 1 ; i < res.length ; i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),
                    "translationY",100*i,0F);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
        }
    }
}
