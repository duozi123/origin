
package com.adolphin.testcase.speeddial;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import junit.framework.Assert;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.jayway.android.robotium.solo.Solo;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 13.长押文件夹,被长押的文件夹变大，可以随意拖动移动其位置,首页顶部不会出现发送到手机桌面的Bar,首页底部不会出现删除的Bar
 * 
 * @author xzhou
 */
@ClassOrder({
        PackageOrder.screenspeeddial, 26
})
@MediumTest
@TestClass("长押文件夹")
public class ClickLongOnFolder26 extends BaseTest {

    public void test_ClickLongOnFolderTest() throws Exception {

        
        solo.sleep(10000);

        View view = SPEEDDIAL.GetIconByName("CellLayout", this, 0, "Dolphin");

        boolean toast = SPEEDDIAL.clickOnScreen(view, solo, 2000, "Remove", "Add to home screen", 3);
        assertTrue("have bar", toast);
        solo.sleep(Resource.TIME_SMALL);
    }

}
