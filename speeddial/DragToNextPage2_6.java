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
 * 24.横屏时超过一屏时移动speed dial或文件夹至下屏,可以正常移动，易用性比较好
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 6 })
@MediumTest
@TestClass("横屏时，超过一屏时移动speed dial或文件夹至下屏,可以正常移动")
public class DragToNextPage2_6 extends BaseTest {
	public void test_HomeIconTest() throws Exception {
		solo.sleep(10000);
		
		View viewt;
		String name2;
		int[] xyf = new int[2];
		int[] xyt = new int[2];
		int count = SPEEDDIAL.childcount30(solo,"Workspace");
		boolean toast = (count > 11);
		float screenHeight = getActivity().getWindowManager()
                .getDefaultDisplay().getHeight();
		assertTrue("no two pages", toast);
		View viewf = SPEEDDIAL.GetIcons30(solo, count - 6,"Workspace");// 获得第一页最左下角的coin
		String name = SPEEDDIAL.GetIconName(viewf);

		SPEEDDIAL.DragToNextPage(viewf,solo,screenHeight);
		solo.sleep(Resource.TIME_SMALL);

		viewt = SPEEDDIAL.GetIcons30(solo, count - 1,"Workspace");// 获得移动后的应该在的位置的coin
		viewf.getLocationOnScreen(xyf);
		name2 = SPEEDDIAL.GetIconName(viewf);
		if (name.equals(name2)) {
			viewt = SPEEDDIAL.GetIcons30(solo, count - 11,"Workspace");// 获得下一特第二个coin
			viewf.getLocationOnScreen(xyt);
		}

		toast = (xyf[1] == xyt[1]);

		assertTrue("not move to next page", toast);
		
		solo.sleep(Resource.TIME_SMALL);
        
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