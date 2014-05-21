
package com.adolphin.testcase.speeddial;

import com.adolphin.common.BaseTest;
import com.adolphin.common.Constants;
import com.jayway.android.robotium.solo.Solo;
import com.dolphin.browser.core.TabManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Rect;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import junit.framework.Assert;

@SuppressLint("Recycle")
public class SPEEDDIAL extends BaseTest {

  
   
   
    // 画出手势B
    public static void dragB() {

        Instrumentation inst = new Instrumentation();
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        float y = (float) 200;// fromY;
        float x = (float) 100;// fromX;
        float yStep1 = (float) 10;// (toY - fromY) / stepCount;
        float xStep1 = (float) 0;// (toX - fromX) / stepCount;
        float yStep2 = (float) 5;
        float xStep2 = (float) 10;// 折线往外
        float xStep3 = (float) -10;// 折线往内
        Random rd1 = new Random();
        // B的竖线部分
        MotionEvent event = MotionEvent.obtain(downTime, eventTime,// 一笔的开始
                MotionEvent.ACTION_DOWN, x, y, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }
        for (int i = 0; i < 40; ++i) {
            y = y + yStep1 + rd1.nextInt(3);
            x = x + xStep1 + rd1.nextInt(3);
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);// 点击事件
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }
        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 100, 600, 0);// 动作结束，没有特别意义，一笔的结束

        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }

        // B的折线部分
        y = (float) 220;
        x = (float) 120;
        event = MotionEvent.obtain(downTime, eventTime,// 另取一笔 ，折线开始
                MotionEvent.ACTION_DOWN, x, y, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }

        for (int i = 0; i < 80; ++i) {
            y = y + yStep2 + rd1.nextInt(3);
            if ((1 <= i && i <= 20) || (40 <= i && i <= 60)) {
                x = x + xStep2 + rd1.nextInt(3);
            } else if ((20 < i && i < 40) || (60 < i && i <= 80)) {
                x = x + xStep3 + rd1.nextInt(3);
            }

            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);// 点击事件
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }
        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 120, 620, 0);// 动作结束，没有特别意义

        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }
    }

    @SuppressLint("Recycle")
    public static  boolean clickOnScreen(View view, Solo solo, int time, String string1,
            String string2, int flag) {// 1为两个都选，2为前选后不选，3为都不选

        boolean toast = false;
        Instrumentation inst = new Instrumentation();
        if (view == null)
            Assert.assertTrue("View is null and can therefore not be clicked!", false);
        int[] xy = new int[2];

        view.getLocationOnScreen(xy);

        final int viewWidth = view.getWidth();
        final int viewHeight = view.getHeight();
        final float x = xy[0] + (viewWidth / 2.0f);
        float y = xy[1] + (viewHeight / 2.0f);
        boolean successfull = false;
        int retry = 0;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y,
                0);

        while (!successfull && retry < 10) {

            try {
                inst.sendPointerSync(event);
                successfull = true;

            } catch (SecurityException e) {
                hideSoftKeyboard(null, false, true, solo);
                retry++;
            }
        }
        if (!successfull) {
            Assert.assertTrue("Click can not be completed!", false);
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x + 1.0f,
                y + 1.0f, 0);
        inst.sendPointerSync(event);

        if (time > 0) {

            solo.sleep(time / 2);
            if (!string1.equals("") && string2.equals("")) {
                toast = solo.searchText(string1, true);
            }
            if (!string2.equals("") && string1.equals("")) {
                toast = solo.searchText(string2, true);
            }
            if (!string2.equals("") && !string1.equals("")) {
                if (flag == 1)
                    toast = solo.searchText(string2, true) && solo.searchText(string1, true);
                if (flag == 2)
                    toast = !solo.searchText(string2, true) && solo.searchText(string1, true);
                if (flag == 3)
                    toast = !solo.searchText(string2, true) && !solo.searchText(string1, true);

            }
            solo.sleep(time / 2);

        } else
            solo.sleep((int) (ViewConfiguration.getLongPressTimeout() * 2.5f));

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
        inst.sendPointerSync(event);
        solo.sleep(500);
        return toast;

    }

    public static void hideSoftKeyboard(EditText editText, boolean shouldSleepFirst,
            boolean shouldSleepAfter, Solo solo) {

        Activity activity = solo.getCurrentActivity();

        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (editText != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            return;
        }
        View focusedView = activity.getCurrentFocus();

        if (!(focusedView instanceof EditText)) {
            EditText freshestEditText = getFreshestView(solo.getCurrentViews(EditText.class));
            if (freshestEditText != null) {
                focusedView = freshestEditText;
            }
        }
        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
        if (shouldSleepAfter) {
            solo.sleep(500);
        }
    }

    public final static <T extends View> T getFreshestView(ArrayList<T> views) {

        final int[] locationOnScreen = new int[2];
        T viewToReturn = null;
        long drawingTime = 0;
        if (views == null) {
            return null;
        }
        for (T view : views) {

            view.getLocationOnScreen(locationOnScreen);

            if (locationOnScreen[0] < 0)
                continue;

            if (view.getDrawingTime() > drawingTime && view.getHeight() > 0) {
                drawingTime = view.getDrawingTime();
                viewToReturn = view;
            }
        }
        views = null;
        return viewToReturn;
    }

    // 获得子元素i=0,表示从homepage获得；i=1表示从folder中获得
    public static View GetIcons(String name, BaseTest test, int i, int index) {// name大类的类名

        // index 返回子元素的索引号

        ViewGroup realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(i);

        View getView = (View) realContent.getChildAt(index);

        return getView;

    }

    public static ArrayList<View> getAllViewByClassName(final String className, final Solo solo) {

        final ArrayList<View> currentViews = solo.getCurrentViews();

        ArrayList<View> results = new ArrayList<View>();

        for (View view : currentViews) {
            // Log.d("TEST", "class name: " + view.getClass().getName());
            if (view.getClass().getName().endsWith(className)) {
                results.add(view);
            }
        }
        return results;
    }

    // 获得icon 的名字
    public static String GetIconName(View view) {

        ViewGroup g = (ViewGroup) view;
        TextView textview = (TextView) g.getChildAt(1);
        String text = textview.getText().toString();// 获得speeddial的名称
        return text;
    }

    // 孩子的个数i=0为homepage,i=1为folder
    public static int childcount(String name, int i, BaseTest test) {// name为类名

        ViewGroup realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(i);
        return realContent.getChildCount();

    }

    // 获得子元素i=0,表示从homepage获得；i=1表示从folder中获得 除了 iconname都可以
    public static View GetOtherIconByName(String name, BaseTest test, int i, String iconname) {// name大类的类名

        // index 返回子元素的名称
        int childCount = childcount(name, i, test);
        View getView = null;
        View view = null;
        ViewGroup realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(i);
        
        for (int j = childCount - 1; j >= 0; j--) {
            getView = (View) realContent.getChildAt(j);
            if (!GetIconName(getView).equals(iconname)
                    && !getView.toString().contains("FolderIcon")) {

                view = getView;
                break;
            }
        }

        return view;

    }

    // 获得子元素i=0,表示从homepage获得；i=1表示从folder中获得
    public static View GetIconByName(String name, BaseTest test, int i, String iconname) {// name大类的类名

        // index 返回子元素的名称
        int childCount = childcount(name, i, test);
        
        View getView = null;
        View view = null;
        ViewGroup realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(i);
        
        for (int j = childCount - 1; j >= 0; j--) {
            getView = (View) realContent.getChildAt(j);
            if (GetIconName(getView).equals(iconname)) {
               
                view = getView;
                break;
            }
        }
       
        return view;

    }

    // 获得最后一个icon元素,flag=0,表示从homepage获得；flag=1表示从folder中获得
    public static View FindIcons(String name, BaseTest test, int flag) {// name大类的类名

        ViewGroup realContent = null;
        int childCount;

        View getView = null;
        realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(flag);
        childCount = realContent.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            final ViewGroup g = (ViewGroup) realContent.getChildAt(i);

            final TextView textname = (TextView) g.getChildAt(1);
            if (!String.valueOf(textname.getText()).equals("")
                    && !g.toString().contains("FolderIcon")) {
                getView = g;

                break;
            }
        }
        return getView;

    }

    // homepage 获得文件夹
    public static View FindFolder(String name, BaseTest test) {// name大类的类名

        ViewGroup realContent = null;
        int childCount;

        View getView = null;
        realContent = (ViewGroup) getAllViewByClassName(name, test.solo).get(0);
        childCount = realContent.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            final ViewGroup g = (ViewGroup) realContent.getChildAt(i);

            if (g.toString().contains("FolderIcon")) {
                getView = g;

                break;
            }
        }
        return getView;

    }

    public static boolean waitfortext(String text, Solo solo) {

        TextView view = solo.getText(text, true);
        return !view.getText().toString().equals("");

    }

    public static  void DragToNextPage(View viewFrom, Solo solo, float sh) {
        Instrumentation inst = new Instrumentation();
        final Rect rct = new Rect();
        // final Rect rct1 = new Rect();
        viewFrom.getGlobalVisibleRect(rct);
        // viewTo.getGlobalVisibleRect(rct1);

        int stepCount = 10;
        float fromX = rct.left + viewFrom.getWidth() / 2;
        float fromY = rct.top + viewFrom.getHeight() / 2;
        float toX = 0;
        float toY = fromY + viewFrom.getHeight() / 2;

        // Log.d("TEST", "moving: fx: " + fromX + " fy: " + fromY + " toX: " +
        // toX + " toY: " + toY);

        boolean successfull = false;
        int retry = 0;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, fromX,
                fromY, 0);

        while (!successfull && retry < 10) {
            try {
                inst.sendPointerSync(event);
                successfull = true;
            } catch (SecurityException e) {
                retry++;
            }
        }
        if (!successfull) {
            Assert.assertTrue("Click can not be completed!", false);
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, fromX + 1.0f,
                fromY + 1.0f, 0);
        inst.sendPointerSync(event);

        solo.sleep((int) (ViewConfiguration.getLongPressTimeout() * 2.5f));

        float y = fromY + 1.0f;
        float x = fromX + 1.0f;
        float yStep = (sh - viewFrom.getHeight()) / stepCount;
        float xStep = 0;

        for (int i = 0; i < stepCount; ++i) {
            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }
        solo.sleep(1000);
        yStep = -viewFrom.getHeight() / stepCount;
        xStep = -fromX / stepCount;
        x = toX;
        y = toY;

        for (int i = 0; i < stepCount; ++i) {
            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }
        solo.sleep(500);
        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, toX, toY, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }

    }

    protected static void addSpeedDial() {

        String format = "dolphin://jsreq/dolphin/addToHome?name=%s&url=%s";

        TabManager
                .getInstance()
                .getCurrentTab()
                .getWebViewCallback()
                .shouldOverrideUrlLoading(
                        TabManager.getInstance().getCurrentTab(),
                        String.format(format, "Google", "http://www.google.com/",
                                "http://opsen.dolphin-browser.com/resources/icon/google_1_1.png"));

    }

    protected static void addSpeedDials() {

        String format = "dolphin://jsreq/dolphin/addToHome?name=%s&url=%s";
        for (int i = 0; i < 23; i++) {
            TabManager
                    .getInstance()
                    .getCurrentTab()
                    .getWebViewCallback()
                    .shouldOverrideUrlLoading(TabManager.getInstance().getCurrentTab(),
                            String.format(format, "coin" + i, "http://www.google.com/"));
            //"http%3A%2F%2Ftest" + i + ".com"
        }
    }

    public static  void clickLongAndDrag(float fromX, float toX, float fromY, float toY, int stepCount,Solo solo) throws Exception {

        Log.d("TEST", "moving: fx: " + fromX + " fy: " + fromY + " toX: " + toX + " toY: " + toY);
        Instrumentation inst = new Instrumentation();
        boolean successfull = false;
        int retry = 0;
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, fromX,
                fromY, 0);

        while (!successfull && retry < 10) {
            try {

                inst.sendPointerSync(event);

                successfull = true;
            } catch (SecurityException e) {
                retry++;
            }
        }
        if (!successfull) {
            Assert.assertTrue("Click can not be completed!", false);
        }

        eventTime = SystemClock.uptimeMillis();

        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, fromX + 1.0f,
                fromY + 1.0f, 0);

        inst.sendPointerSync(event);

        solo.sleep((int) (ViewConfiguration.getLongPressTimeout() * 2.5f));

        float y = fromY + 1.0f;
        float x = fromX + 1.0f;
        float yStep = (toY - fromY) / stepCount;
        float xStep = (toX - fromX) / stepCount;

        for (int i = 0; i < stepCount; ++i) {

            y += yStep;
            x += xStep;
            eventTime = SystemClock.uptimeMillis();
            event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x, y, 0);
            try {
                inst.sendPointerSync(event);
            } catch (SecurityException ignored) {
            }
        }

        eventTime = SystemClock.uptimeMillis();
        event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, toX, toY, 0);
        try {
            inst.sendPointerSync(event);
        } catch (SecurityException ignored) {
        }

    }
    // name="Workspace"为homepage,name="MyScrollView"为folder
    public static int childcount30(Solo solo,String name){
        ViewGroup realContent = (ViewGroup) SPEEDDIAL.getAllViewByClassName(name, solo)
                .get(0);// 获得Workspace
        ViewGroup childGroup = (ViewGroup) realContent.getChildAt(0);// 获得CellLayout

        int childnum = childGroup.getChildCount() ;// 获得CellLayout的孩子数
        return childnum;
    }
 // name="Workspace"为homepage,name="MyScrollView"为folder
    public static View  GetIcons30(Solo solo,int i,String name){
        ViewGroup realContent = (ViewGroup) SPEEDDIAL.getAllViewByClassName(name,solo)
                .get(0);// 获得Workspace
        ViewGroup childGroup = (ViewGroup) realContent.getChildAt(0);// 获得CellLayout
        View view =childGroup.getChildAt(i);
        return view;
    }
    public static View FindIcons30(Solo solo) {//找到第一个不是文件的icon

        ViewGroup realContent  = (ViewGroup) getAllViewByClassName("Workspace", solo).get(0);
        ViewGroup childGroup = (ViewGroup) realContent.getChildAt(0);// 获得第一个CellLayout

        int childCount = childGroup.getChildCount() ;// 获得CellLayout的孩子数
        View getView = null;
        
        
        for (int i = childCount - 1; i >= 0; i--) {
            final ViewGroup g = (ViewGroup) childGroup.getChildAt(i);

            final TextView textname = (TextView) g.getChildAt(1);
            if (!String.valueOf(textname.getText()).equals("")
                    && !g.toString().contains("FolderIcon")) {
                getView = g;

                break;
            }
        }
        return getView;

    }
    public static View FindFolder30(String name, Solo solo) {// name文件夹的名字

     
         

        ViewGroup realContent  = (ViewGroup) getAllViewByClassName("Workspace", solo).get(0);
        ViewGroup childGroup = (ViewGroup) realContent.getChildAt(0);// 获得第一个CellLayout

        int childCount = childGroup.getChildCount() ;// 获得CellLayout的孩子数
        View getView = null;
        for (int i = childCount - 1; i >= 0; i--) {
            final ViewGroup g = (ViewGroup) childGroup.getChildAt(i);
            final TextView textname = (TextView) g.getChildAt(1);
            if (g.toString().contains("FolderIcon")&&String.valueOf(textname.getText()).equals(name)) {
                getView = g;

                break;
            }
        }
        return getView;

    }

}
