
package com.adolphin.testcase.speeddial;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;

import com.jayway.android.robotium.solo.By;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 1.在home页点击 + New，进入web store网页
 * 
 * @author xzhou
 */
@ClassOrder({
        PackageOrder.screenspeeddial, 10
})
@MediumTest
@TestClass("在home页点击 + New，进入web store网页")
public class NewSpeeddial10 extends BaseTest {

    public void test_NewSpeeddialTest() throws Exception {

        // 点击homepage的加号按钮
        solo.sleep(5000);
        int[] xy = new int[2];
        float x;
        float y;
        int viewWidth;
        int viewHeight;
        boolean toast;


        solo.clickOnView(solo.getViewByPath("workspace[0][0]"));
        solo.sleep(Resource.TIME_BIG);

        SPEEDDIAL.addSpeedDial();//添加google
        solo.sleep(Resource.TIME_SMALL);
        solo.goBack();

        solo.sleep(4000);
        toast = solo.searchText("Google", true);
        assertTrue("not add google", toast);

        solo.clickOnView(solo.getViewByPath("workspace[0][0]"));
        solo.sleep(Resource.TIME_BIG);

        SPEEDDIAL.addSpeedDials();//添加至30个
        solo.sleep(Resource.TIME_SMALL);

        solo.goBack();
        
        
        int count = SPEEDDIAL.childcount("CellLayout", 0, this);
        
        toast = (count > 20);
        assertTrue("not add speeddials", toast);
        solo.sleep(Resource.TIME_SMALL);

      
        

    }
    
    

}
