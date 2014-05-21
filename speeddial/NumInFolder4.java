package com.adolphin.testcase.speeddial;

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
 * 20.speed dial/文件夹的排列,小分辨率手机,大分辨率手机，平板每屏显示3*3,一屏显示9个，按照3*3排
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 4 })
@MediumTest
@TestClass("speed dial文件夹的排列一屏显示9个，按照3*3排")
public class NumInFolder4 extends BaseTest {
	public void test_NumInLineTest() throws Exception {

		solo.sleep(10000);
		int[] xy = new int[2];
		float y1 = 0;
		float y2 = 0;
		boolean toast = false;
		View viewcoin;
		String name;
		View view = SPEEDDIAL.GetIconByName("CellLayout", this,0,"Folder");

		solo.clickOnView(view);
		solo.sleep(Resource.TIME_SMALL);
	
		int child =SPEEDDIAL.childcount30(solo,"MyScrollView") - 1;
		
		// viewcoin = Utils.GetIcons("CellLayout", this, 1, child);
		// viewcoin.getLocationOnScreen(xy);
		y1 = 0;
		y2 = 0;

		if (child >= 9) {
			for (int j = 1; j <= 3; j++) {
				for (int i = 1; i <= 3; i++) {
					viewcoin = SPEEDDIAL.GetIcons30(solo, child,"MyScrollView");
					name = SPEEDDIAL.GetIconName(viewcoin);
					viewcoin.getLocationOnScreen(xy);
					if (i == 1) {
						y1 = xy[1];
						child--;
					} else {
						if (y1 == xy[1]) {
							toast = true;
							child--;
							continue;
						} else {
							toast = false;
							break;
						}
					}
				}
				
				if (y1 != y2 && toast) {
					toast = true;
					y2 = y1;
				} else {
					toast = false;
					break;
				}
			}

		}
		assertTrue(toast);
		viewcoin = SPEEDDIAL.GetIcons30(solo, child,"MyScrollView");
		name = SPEEDDIAL.GetIconName(viewcoin);
		toast = solo.searchText(name, true);
		assertTrue("not 9", !toast);
		

	}
}
