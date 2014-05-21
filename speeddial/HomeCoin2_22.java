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
 * 18.横屏模式下长按首页中除find apps以外的任意一个快速访问,快速访问的iCon变淡,地址栏和Tab
 * bar消失，首页的顶部和底部同时显示两个绿色的Bar,首页的顶部左上角显示Add to home screen,首页的顶部右上角显示Remove
 * 横屏模式下拖到首页的顶部,弹出toast：Successfully added to home screen
 * 横屏模式下长按iCon拖入到首页的底部Remove中,iCon从首页消失
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 22})
@MediumTest
@TestClass("横屏模式下首页中Add to home screen和Remove的状态")
public class HomeCoin2_22 extends BaseTest {
	public void test_HomeIcon2Test() throws Exception {
		solo.sleep(5000);
		
		
		
		int[] xy = new int[2];
		int viewWidth;
		int viewHeight;
		float x;
		float y;
		boolean toast;
		View view;
		
		view = SPEEDDIAL.GetIconByName("CellLayout", this, 0, "Find Apps");// 找到FindApps的icon
		 																
		toast = SPEEDDIAL.clickOnScreen(view, solo, 2000, "Add to home screen",
				"Remove", 2);
		assertTrue("not only Add to home screen", toast);
		solo.sleep(Resource.TIME_SMALL);
		view = SPEEDDIAL.GetOtherIconByName("CellLayout", this, 0, "Find Apps");// 找到非Find
																			// Apps的第一个icon
		String name = SPEEDDIAL.GetIconName(view);
		toast = SPEEDDIAL.clickOnScreen(view, solo, 2000, "Remove",
				"Add to home screen", 1);// 长按 同时判断是否有Remove，且有Add to home
											// screen
		assertTrue("not show two bars", toast);

		view.getLocationOnScreen(xy);
		viewWidth = view.getWidth();
		viewHeight = view.getHeight();
		x = xy[0] + (viewWidth / 2.0f);
		y = xy[1] + (viewHeight / 2.0f);
		float screenWidth = getActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		SPEEDDIAL.clickLongAndDrag(x, screenWidth/4.0f, y, 0, 10,solo);// 拖到添加栏
		solo.sleep(Resource.TIME_SMALL);

		// 判断是否出添加提示
		// toast=solo.waitForText("Shortcut \""+name+"\" created.");
		// assertTrue("not added to home screen", toast);
		solo.sleep(Resource.TIME_SMALL);

		
		SPEEDDIAL.clickLongAndDrag(x, screenWidth*3/4.0f, y, 0, 10,solo);// 拖到删除栏
		toast = solo.searchText(name);
		assertTrue("not delete", !toast);

		
		
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
