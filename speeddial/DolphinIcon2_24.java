
package com.adolphin.testcase.speeddial;

import android.util.Log;
import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 11.横屏模式下长按Dolphin文件夹中的Feedback的iCon，此时只会出现Remove选项，不会出现Add to home screen的选项
 * 竖屏模式下长按文件夹中其他的任意一个快速访问，文件夹依然处于展开状态，顶部的Bar显示发送图标和Add to home
 * screen，底部的Bar显示垃圾桶图标和Remove 竖屏模式下拖到首页的顶部， 手机桌面显示添加的speed dia，再添加一次，没有异常
 * 竖屏模式下长按iCon拖入到首页的底部，此时Remove Bar中背景色变成红色，iCon从文件夹中消失
 * 
 * @author xzhou
 */
@ClassOrder({
        PackageOrder.screenspeeddial, 24
})
@MediumTest
@TestClass("横屏模式下长按Dolphin文件夹，Remove选项和Add to home screen的选项的状态")
public class DolphinIcon2_24 extends BaseTest {
    public void test_DophinIcon2Test() throws Exception {
        
        solo.sleep(5000);
        clickControlPanel("Settings");//改为横屏
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Orientation");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Landscape");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.goBack();
        Boolean toast;
        View view;
        
        int[] xy = new int[2];
        int viewWidth;
        int viewHeight;
        float x;
        float y;
        

        float screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        String name;
        Utils.clickScreenIconByType("Dolphin", 1, 1, this);// 打开文件夹
        solo.sleep(Resource.TIME_SMALL);
        view = SPEEDDIAL.GetIconByName("CellLayout", this, 1, "Feedback");// 找到Feedback

        toast =SPEEDDIAL.clickOnScreen(view, solo, 2000, "Remove", "Add to home screen", 2);// 长按
                                                                                        // 同时判断是否有Remove，且没有Add
                                                                                        // to
                                                                                        // home
                                                                                        // screen

        assertTrue("not only Remove", toast);
        solo.sleep(Resource.TIME_SMALL);

        view = SPEEDDIAL.GetOtherIconByName("CellLayout", this, 1, "Feedback");// 找到非Feedback的第一个icon
        toast = SPEEDDIAL.clickOnScreen(view, solo, 2000, "Remove", "Add to home screen", 1);// 长按
                                                                                        // 同时判断是否有Remove，且有Add
                                                                                        // to
                                                                                        // home
                                                                                        // screen

        assertTrue("not all shown ", toast);
        solo.sleep(Resource.TIME_SMALL);

        name = SPEEDDIAL.GetIconName(view);// 获得非Feedback的第一个icon的名称

        view.getLocationOnScreen(xy);

        viewWidth = view.getWidth();
        viewHeight = view.getHeight();
        x = xy[0] + (viewWidth / 2.0f);
        y = xy[1] + (viewHeight / 2.0f);
        
        
        SPEEDDIAL.clickLongAndDrag(x, screenWidth / 4.0f, y, 0, 10,solo);// 拖到添加栏
     
        // 判断是否出添加提示
        // toast=solo.waitForText("Shortcut \""+name+"\" created.");
        // assertTrue("not added to home screen", toast);
        solo.sleep(Resource.TIME_SMALL);

        Utils.clickScreenIconByType("Dolphin", 1, 1, this);// 打开文件夹
        view =SPEEDDIAL.GetIconByName("CellLayout", this, 1, name);// 找到第一次添加的icon
        view.getLocationOnScreen(xy);
        view.getLocationOnScreen(xy);
        viewWidth = view.getWidth();
        viewHeight = view.getHeight();
        x = xy[0] + (viewWidth / 2.0f);
        y = xy[1] + (viewHeight / 2.0f);

        SPEEDDIAL.clickLongAndDrag(x, screenWidth / 4.0f, y, 0, 10,solo);// 拖到添加栏，再添加一次
        solo.sleep(Resource.TIME_SMALL);

        Utils.clickScreenIconByType("Dolphin", 1, 1, this);// 打开文件夹
        solo.sleep(Resource.TIME_SMALL);
        view = SPEEDDIAL.GetOtherIconByName("CellLayout", this, 1, "Feedback");// 找到非Feedback的第一个icon
        name = SPEEDDIAL.GetIconName(view);// 获得名称
        view.getLocationOnScreen(xy);
        viewWidth = view.getWidth();
        viewHeight = view.getHeight();
        x = xy[0] + (viewWidth / 2.0f);
        y = xy[1] + (viewHeight / 2.0f);

        SPEEDDIAL.clickLongAndDrag(x, screenWidth * 3 / 4.0f, y, 0, 10,solo);// 拖到删除栏
        solo.sleep(Resource.TIME_SMALL);

        Utils.clickScreenIconByType("Dolphin", 1, 1, this);// 打开文件夹，判断是否删除
        toast = solo.searchText(name, true);
        assertTrue("not delete", !toast);

        solo.sleep(Resource.TIME_SMALL);

        solo.goBack();
        solo.sleep(Resource.TIME_SMALL);//换回竖屏
        clickControlPanel("Settings");
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Orientation");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Portrait");
        solo.sleep(Resource.TIME_SMALL);

    }
}
