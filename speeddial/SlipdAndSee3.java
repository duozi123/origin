package com.adolphin.testcase.speeddial;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.jayway.android.robotium.solo.Solo;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 6.打开一个folder 应用可以正常显示，可以滑动查看 文件夹展开时 → 点击文件夹以外的空白区域→ 查看文件夹
 * 文件会收回关闭，文件夹在桌面呈圆形同其他peed dial icon一致
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 3 })
@MediumTest
@TestClass("滑动查看文件夹")
public class SlipdAndSee3 extends BaseTest {
	public void test_SlipdAndSeeTest() throws Exception {
		View viewF;
		View viewT;
		Boolean toast;
		float fromX;
		float fromY;
		float toX;
		float toY;
		int[] xyf = new int[2];
		int[] xyt = new int[2];
		// final Rect rct = new Rect();
		// final Rect rct1 = new Rect();
		solo.sleep(5000);
		int childnum;

		// 滑动查看
		View viewfolder = SPEEDDIAL.GetIconByName("CellLayout", this,0,"Folder");

        solo.clickOnView(viewfolder);
        solo.sleep(Resource.TIME_SMALL);
		childnum = SPEEDDIAL.childcount30(solo,"MyScrollView") - 1;
		while (childnum > 8) {
			viewT = SPEEDDIAL.GetIcons30(solo, childnum,"MyScrollView");// 上面
			viewT.getLocationOnScreen(xyt);
			viewF = SPEEDDIAL.GetIcons30(solo, childnum - 3,"MyScrollView");// 下面
			viewF.getLocationOnScreen(xyf);
			fromX = xyf[0] + viewF.getWidth() / 2;
			fromY = xyf[1] + viewF.getHeight() / 2;
			toX = xyt[0] + viewT.getWidth() / 2;
			toY = xyt[1] + viewT.getHeight() / 2;
			solo.drag(fromX, toX, fromY, toY, 20);
			solo.sleep(Resource.TIME_BIG);
			childnum = childnum - 3;
		}
		

		// 获得第一个子元素的名称
		View view=SPEEDDIAL.GetIcons30(solo, 0,"MyScrollView");
		String string = SPEEDDIAL.GetIconName(view);
		//判断滑到最底部
		toast = solo.searchText(string);
		assertTrue("not to the end", toast);
		// 退出文件夹 ，返回主界面

		view = SPEEDDIAL.getAllViewByClassName("Folder", solo).get(0);// 获得打开的文件夹
		view.getLocationOnScreen(xyf);// 获得坐标
		fromX = xyf[0] ;
		fromY = xyf[1] + view.getHeight() +5;
		solo.clickOnScreen(fromX, fromY);
		// 文件夹关闭之后就不能获得第一个字元素的名称，来判断文件夹已经关闭

		toast = solo.searchText(string,true);
		assertTrue("not return", !toast);
		solo.sleep(Resource.TIME_SMALL);
		

	}

}
