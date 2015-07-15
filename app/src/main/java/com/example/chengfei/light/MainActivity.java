package com.example.chengfei.light;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private LinearLayout mylayout;
    private Resources myColor;
    private int li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //HideStatusBase();
        setContentView(R.layout.activity_main);

        mylayout = (LinearLayout) findViewById(R.id.mylayout);
        SetColor(R.color.white);
        li = 0;

        SetBright(1.0F);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        openOptionsMenu();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id)
        {
            case R.id.about:
                about();
                return true;
            case R.id.setcolor:
                selectColor();
                return true;
            case R.id.setbright:
                selectBright();
                return true;
            case R.id.seteffer:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*设置颜色*/
    public void SetColor(int color)
    {
        myColor = getBaseContext().getResources();
        Drawable color_m = myColor.getDrawable(color);
        //mylayout.setBackground(color_m);
        mylayout.setBackgroundDrawable(color_m);
        //mylayout.setBackgroundColor(Color.argb(255,255,255,255));
    }
    /* 选择颜色 */
    public void selectColor()
    {
        final String[] items = {"白色","红色","黑色","黄色","粉色"};
        new AlertDialog.Builder(this)
                .setTitle("选择颜色")
                .setItems(items,new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getApplicationContext(),items[which],Toast.LENGTH_SHORT).show();//将选中的文本内容按照土司提示 方式显示出来, 此处的getApplicationContext() 得到的也是当前的Activity对象，可用当前Activity对象的名字.this代替（Activity.this）
                        switch (which)
                        {
                            case 0:
                                SetColor(R.color.white);
                                break;
                            case 1:
                                SetColor(R.color.red);
                                break;
                            case 2:
                                SetColor(R.color.black);
                                break;
                            case 3:
                                SetColor(R.color.yellow);
                                break;
                            case 4:
                                SetColor(R.color.fs);
                                break;
                            default:
                                SetColor(R.color.white);
                                break;
                        }
                    }
                }).show();
    }

    /*设置亮度*/
    private void SetBright(float light)
    {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = light;
        getWindow().setAttributes(lp);
    }

    public void selectBright()
    {
        final String[] items = {"100%","75%","50%","25%","10%"};
        new AlertDialog.Builder(this).setTitle("选择亮度")
                .setSingleChoiceItems(items, li, new DialogInterface.OnClickListener() {//此处数字为选项的下标，从0开始， 表示默认哪项被选中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();

                        switch (which) {
                            case 0:
                                SetBright(1.0F);
                                li = 0;
                                break;
                            case 1:
                                SetBright(0.75F);
                                li = 1;
                                break;
                            case 2:
                                SetBright(0.5F);
                                li = 2;
                                break;
                            case 3:
                                SetBright(0.25F);
                                li = 3;
                                break;
                            case 4:
                                SetBright(0.10F);
                                li = 4;
                                break;
                            default:
                                SetBright(1.0F);
                                li = 0;
                                break;
                        }
                        dialog.cancel();
                    }
                }).show();

    }

    /**
     * 全屏设置
     */
    private void HideStatusBase()
    {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window myWindow = this.getWindow();
        myWindow.setFlags(flag,flag);
    }
    /*关于*/
    public void about()
    {
        new AlertDialog.Builder(MainActivity.this).setTitle("关于我们")
                .setMessage("作者：程斐 \n邮件：chengfei609@gmail.com")
                .setIcon(R.drawable.abc_ic_voice_search_api_mtrl_alpha)
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int whichButton)
                    {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                })
                .show();
    }
}
