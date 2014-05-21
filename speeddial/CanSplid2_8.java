package com.adolphin.testcase.speeddial;

import android.R.string;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;
/**
	 * 25.横屏时，超过一屏时，屏幕不能左右滑动，但可以向下滑动，且地址栏或标题不变 当屏幕被向下滑动后，再向上滑动，可以向上滑动，且地址栏或标题不变
	 * 在home页添加speed dial数目越多，大约30,可以连续向下滑动直至最后添加的speed dial显示出来之后就不能继续向下滑动
	 * ,向下滑动屏幕后，也可以向上滑动返回屏幕
	 * 
	 * @author xzhou
	 * 
	 */
@ClassOrder({ PackageOrder.screenspeeddial,8 })
@MediumTest
@TestClass("横屏滑动首页")
public class CanSplid2_8 extends BaseTest {
	
	@SuppressWarnings("deprecation")
	public void test_CanSplidTest() throws Exception {
		solo.sleep(10000);
		boolean toast = false;
		View viewF;
		View viewT;
		View viewl;
		int[] xyf = new int[2];
		int[] xyt = new int[2];
		int[] xy = new int[2];
		int[] xybefore = new int[2];
		int[] xyafter = new int[2];
		float fromX;
		float fromY;
		float toX;
		float toY;
		float screenHeight = getActivity().getWindowManager()
				.getDefaultDisplay().getHeight();
		float screenWidth = getActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		 int childnum = SPEEDDIAL.childcount30(solo,"Workspace")-1 ;

		// 不能左右滑动
		viewT = SPEEDDIAL.GetIcons30(solo, childnum,"Workspace");
		
		
		// 滑动前获得最后一个coin
		viewT.getLocationOnScreen(xyt);
		viewl = SPEEDDIAL.GetIcons30(solo, childnum - 5,"Workspace");
		viewl.getLocationOnScreen(xy);
		float high = xyt[1] + viewT.getHeight()
				+ (xy[1] - (xyt[1] + viewT.getHeight())) / 2.0f;
		solo.drag(screenWidth / 4.0f, screenWidth * 3 / 4.0f, high, high, 10);// 横向滑动，从左往右
		solo.sleep(Resource.TIME_SMALL);
		viewF = SPEEDDIAL.GetIcons30(solo, childnum,"Workspace");
		viewF.getLocationOnScreen(xyf);
		if (xyf[0] == xyt[0]) {
			solo.drag(screenWidth * 3 / 4.0f, screenWidth / 4.0f, high, high,
					10);// 横向滑动，从右往左
			solo.sleep(Resource.TIME_SMALL);
			viewF = SPEEDDIAL.GetIcons30(solo, childnum,"Workspace");
			viewF.getLocationOnScreen(xyf);
			if (xyf[0] == xyt[0])
				toast = true;
			else
				toast = false;

		} else
			toast = false;

		// 判断不能左右滑动
		assertTrue("can move to right and left", toast);

		View viewtop = solo.getView("top_container");// 获得地址栏的坐标
		viewtop.getLocationOnScreen(xybefore);
		// 向下滑到最下面
		while (childnum > 9) {
			viewT = SPEEDDIAL.GetIcons30(solo, childnum,"Workspace");// 上面
			viewT.getLocationOnScreen(xyt);
			viewF = SPEEDDIAL.GetIcons30(solo, childnum - 5,"Workspace");// 下面
			viewF.getLocationOnScreen(xyf);
			fromX = xyf[0] + viewF.getWidth() / 2;
			fromY = xyf[1] + viewF.getHeight() / 2;
			toX = xyt[0] + viewT.getWidth() / 2;
			toY = xyt[1] + viewT.getHeight() / 2;
			solo.drag(fromX, toX, fromY, toY, 20);
			solo.sleep(Resource.TIME_BIG);
			childnum = childnum - 5;
		}

		// 获得第一个子元素的名称
		View view = SPEEDDIAL.GetIcons30(solo, 1,"Workspace");
		String string = SPEEDDIAL.GetIconName(view);
		// 判断滑到最底部
		toast = solo.searchText(string, true);
		assertTrue("not to the end", toast);
		// 再次获得地址栏的坐标
		viewtop = solo.getView("top_container");
		viewtop.getLocationOnScreen(xyafter);
		// 判断地址栏没有变
		toast = (xybefore[0] == xyafter[0] && xybefore[1] == xyafter[1]);
		assertTrue("address bar move", toast);

		solo.sleep(Resource.TIME_SMALL);
		childnum = SPEEDDIAL.childcount30(solo,"Workspace");
		solo.sleep(Resource.TIME_SMALL);
		int child = 0;
		// 从下面滑到顶部
		while (child < childnum - 10) {
			viewT = SPEEDDIAL.GetIcons30(solo, child,"Workspace");// 下面
			viewT.getLocationOnScreen(xyt);
			viewF = SPEEDDIAL.GetIcons30(solo, child + 5,"Workspace");// 上面
			viewF.getLocationOnScreen(xyf);
			fromX = xyf[0] + viewF.getWidth() / 2;
			fromY = xyf[1] + viewF.getHeight() / 2;
			toX = xyt[0] + viewT.getWidth() / 2;
			toY = xyt[1] + viewT.getHeight() / 2;
			solo.drag(fromX, toX, fromY, toY, 20);
			solo.sleep(Resource.TIME_BIG);
			child = child + 5;
		}
		view = SPEEDDIAL.GetIcons30(solo, childnum - 1,"Workspace");// 获得最后一个cion
		string = SPEEDDIAL.GetIconName(view);
		// 判断滑到最顶部
		toast = solo.searchText(string, true);
		assertTrue("not to the top", toast);
		solo.sleep(Resource.TIME_SMALL);

		viewtop = solo.getView("top_container");// 获得地址栏
		viewtop.getLocationOnScreen(xyafter);
		// 判断地址栏没有变
		toast = (xybefore[0] == xyafter[0] && xybefore[1] == xyafter[1]);
		assertTrue("address bar move", toast);

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