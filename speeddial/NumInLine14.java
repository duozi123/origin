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
 * 13.竖屏下，分辨率（>=320*480 ) ，点击文件夹,文件夹自动展开, icon数目>4，每行最多显示3个icon
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 14})
@MediumTest
@TestClass("竖屏下，分辨率（>=320*480 ) ，点击文件夹,文件夹自动展开, icon数目>4，每行最多显示3个icon")
public class NumInLine14 extends BaseTest {
    public void test_NumInLineTest() throws Exception {

        solo.sleep(5000);
        boolean toast = false;
        View view1;

        int[] xy = new int[2];

        float y1 = 0;
        float y2 = 0;
        float y3 = 0;
        float y4 = 0;
        View view = SPEEDDIAL.FindFolder("CellLayout", this);

        solo.clickOnView(view);
        solo.sleep(Resource.TIME_SMALL);
        int child = SPEEDDIAL.childcount("CellLayout", 1, this);
        if (child >= 4) {
            view1 = SPEEDDIAL.GetIcons("CellLayout", this, 1, child - 1);

            view1.getLocationOnScreen(xy);
            y1 = xy[1];
           
            view1 = SPEEDDIAL.GetIcons("CellLayout", this, 1, child - 2);

            view1.getLocationOnScreen(xy);
            y2 = xy[1];
            
            view1 = SPEEDDIAL.GetIcons("CellLayout", this, 1, child - 3);
            view1.getLocationOnScreen(xy);
            y3 = xy[1];
            
            view1 = SPEEDDIAL.GetIcons("CellLayout", this, 1, child - 4);
            view1.getLocationOnScreen(xy);
            y4 = xy[1];
           
        }
        if (y1 == y2 && y2 == y3 && y3 != y4)
            toast = true;
        assertTrue("not 3 in line", toast);
       
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
      
        clickControlPanel("Settings");// 改为横屏
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Customize");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Orientation");
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnText("Landscape");
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();
        solo.goBack();

    }
	
}
