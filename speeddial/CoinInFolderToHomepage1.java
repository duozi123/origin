package com.adolphin.testcase.speeddial;

import android.R.string;
import android.app.Instrumentation;
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
 * 12.随意拖动任意一个iCon到文件夹外面去,iCon可以拖出 当文件夹中只剩下一个iCon,文件夹自动消失，拖出来的iCon正常显示在首页
 * 长按移到文件夹外面的iCon,可以进行删除和发送到桌面的操作
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 1})
@MediumTest
@TestClass("拖动任意一个iCon到文件夹外面去")
public class CoinInFolderToHomepage1 extends BaseTest {
	public void test_IconInFolderToHomepageTest() throws Exception {
		solo.sleep(10000);
		
		int[] xy = new int[2];
		int[] xyfolder = new int[2];
		int viewcoinWidth;
		int viewcoinHeight;

		int viewHeight;
		float xcoin;
		float ycoin;

		float y;
		String name;
		View viewcoin;
		View view;

		view = SPEEDDIAL.FindFolder("CellLayout", this);// 获得文件夹

		name =SPEEDDIAL.GetIconName(view);// 获得文件夹名字
		solo.clickOnView(view);// 打开文件夹
		solo.sleep(Resource.TIME_SMALL);
		int childcount = SPEEDDIAL.childcount("CellLayout", 1, this);// 文件夹中子元素的个数

		view = SPEEDDIAL.getAllViewByClassName("Folder", solo).get(0);// 获得打开的文件夹
		view.getLocationOnScreen(xyfolder);// 获得坐标

		viewHeight = view.getHeight();

		

		viewcoin = SPEEDDIAL.FindIcons("CellLayout", this, 1);// 获得文件夹里面最后一个cion
		//String name2=SPEEDDIAL.GetIconName(viewcoin);

		viewcoin.getLocationOnScreen(xy);// 获得icon的坐标

		viewcoinWidth = viewcoin.getWidth();
		viewcoinHeight = viewcoin.getHeight();
		y = xyfolder[1] + (viewHeight)+viewcoinHeight;
		xcoin = xy[0] + (viewcoinWidth / 2.0f);
		ycoin = xy[1] + (viewcoinHeight / 2.0f);
		SPEEDDIAL.clickLongAndDrag(xcoin, xcoin, ycoin, y, 20,solo);// 拖到文件外
		solo.sleep(Resource.TIME_SMALL);
		// 把文件夹中的icon都拖到文件夹外
		for (int i = 1; i <= childcount - 2; i++) {
			view = SPEEDDIAL.GetIconByName("CellLayout", this, 0, name);
			solo.clickOnView(view);
			solo.sleep(Resource.TIME_SMALL);
			view = SPEEDDIAL.getAllViewByClassName("Folder", solo).get(0);
			view.getLocationOnScreen(xyfolder);

			viewHeight = view.getHeight();

			//y = xyfolder[1] + (viewHeight);

			viewcoin = SPEEDDIAL.FindIcons("CellLayout", this, 1);// 获得文件夹里面最后一个cion

			viewcoin.getLocationOnScreen(xy);

			viewcoinWidth = viewcoin.getWidth();
			viewcoinHeight = viewcoin.getHeight();
			xcoin = xy[0] + (viewcoinWidth / 2.0f);
			ycoin = xy[1] + (viewcoinHeight / 2.0f);
			SPEEDDIAL.clickLongAndDrag(xcoin, xcoin, ycoin, y, 30,solo);// 拖到文件外
		}
		solo.sleep(Resource.TIME_SMALL);
		boolean toast = solo.searchText(name);//判断文件夹消失
		assertTrue("not disappare folder ", !toast);
		solo.sleep(Resource.TIME_SMALL);
	
		view = SPEEDDIAL.GetOtherIconByName("CellLayout", this, 0, "Feedback");//获得homepage除Feedback第一个icon
		toast = SPEEDDIAL.clickOnScreen(view, solo, 2000, "Remove",
				"Add to home screen", 1);// 长按 同时判断是否有Remove，且没有Add to home
											// screen

		assertTrue("not only Remove and delete", toast);
		solo.sleep(Resource.TIME_SMALL);
		
	}
}