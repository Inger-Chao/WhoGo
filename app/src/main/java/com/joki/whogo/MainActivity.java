package com.joki.whogo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static com.joki.whogo.R.id.btn_restart;
import static com.joki.whogo.R.id.img_logo;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity";

    private ImageView imgLogo;
    private Button btnRestart;
    private RecyclerView RvList;

    private WhoAdapter whoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgLogo= (ImageView) findViewById(img_logo);
        btnRestart= (Button) findViewById(btn_restart);
        RvList = (RecyclerView) findViewById(R.id.rv_list);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int flag = 0;//重复次数
                while(true){
                    //产生0-100的随机数
                    int index=random.nextInt(100);
                    //判断是否已经存在
                    boolean isRepeat = false;//标识是否存在
                    for(int i=0;i<whoAdapter.data.size();i++){
                        if(Integer.parseInt(whoAdapter.data.get(i)) == index){
                            //已经存在
                            isRepeat=true;
                            break;
                        }
                    }
                    if(isRepeat){
                        //重复
                        flag++;
                    } else{
                        //向列表的数据中添加
                        whoAdapter.data.add(index + "");//用" "将index转化为字符串
                        //动画效果，插入到最后一位
                        whoAdapter.notifyItemInserted(whoAdapter.data.size() - 1);
                        break;
                    }
                    if(flag>=16){
                        Toast.makeText(MainActivity.this,"休息会",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                // whoAdapter.notifyDataSetChanged();//通知RecycleView数据发生改变，让他显示新的数据
                RvList.scrollToPosition(whoAdapter.data.size() - 1 );//使RecycleView滚动到最后一位
                //为按钮添加旋转动画
                ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(imgLogo,"rotation",0f,360f);
                //为按钮添加拉长效果
                ObjectAnimator scaleX=ObjectAnimator.ofFloat(imgLogo,"scaleX",1f,1.2f);
                ObjectAnimator scaleY=ObjectAnimator.ofFloat(imgLogo,"scaleY",1f,1.2f);
                //三种效果同时实现（依次执行）
                AnimatorSet set = new AnimatorSet();
                set.setDuration(500);
                set.playTogether(rotateAnim,scaleX,scaleY);
                //set.play(rotateAnim).after(scaleX).after(scaleY);
                set.start();

            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whoAdapter.data.clear(); //清空数据
                whoAdapter.notifyDataSetChanged();//通知RecycleView进行更新
            }
        });
        //Adapter Data(ArrayList<String>)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);//创建一个线性布局管理器对象
        RvList.setLayoutManager(layoutManager);
        whoAdapter = new WhoAdapter();
        RvList.setAdapter(whoAdapter);//设置适配器
        for(int i = 0;i < 50 ;i++){
       /*     whoAdapter.data.add( i + "");*/
        }
    }
}
