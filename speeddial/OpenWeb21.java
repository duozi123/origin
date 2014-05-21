
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
 * 15.点击文件夹中的iCon,可以进入对应的网页
 * 
 * @author xzhou
 */
@ClassOrder({
        PackageOrder.screenspeeddial, 21
})
@MediumTest
@TestClass("点击文件夹中的iCon,可以进入对应的网页")
public class OpenWeb21 extends BaseTest {
    public void test_OpenWebTest() throws Exception {

        solo.sleep(5000);
        View viewf = SPEEDDIAL.GetIconByName("CellLayout", this, 0, "Facebook");
        View viewt = SPEEDDIAL.FindFolder("CellLayout", this);// 找到文件夹
        clickLongAndDrag(viewf, viewt);
        solo.sleep(Resource.TIME_SMALL);
        solo.clickOnView(viewt);// 打开文件夹
        solo.sleep(Resource.TIME_SMALL);

        View view1 = SPEEDDIAL.GetIconByName("CellLayout", this, 1, "Facebook");// 找到Facebook
        solo.clickOnView(view1);
        solo.sleep(Resource.TIME_SMALL);
        view1 = getViewByClassName("WebView", 1);// 获得网页的view
        assertNotNull(view1);// 确认有网页
        boolean toast = solo.searchText("www.facebook.com");// 确认进入相应网页
     
        
        assertTrue("not open", toast);
        solo.sleep(Resource.TIME_SMALL);

    }

}
