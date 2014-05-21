package com.adolphin.testcase.speeddial;

import android.view.View;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;
/**
 * 16.长按speed dial移动到任意的位置，可以停留在想拖到的位置
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 18 })
@MediumTest
@TestClass("长按speed dial移动到任意的位置，可以停留在想拖到的位置")
public class DragCoin18 extends BaseTest {
	public void test_DragIconTest() throws Exception {
		solo.sleep(5000);
		float fromX;
		float fromY;
		float toX;
		float toY;
		int[] xyf = new int[2];
		int[] xyt = new int[2];
		int count = SPEEDDIAL.childcount("CellLayout", 0, this);
		View viewt = SPEEDDIAL.GetIcons("CellLayout", this, 0, count - 2);//获得倒数第二个icon
		viewt.getLocationOnScreen(xyt);
		toX = xyt[0] + viewt.getWidth() / 2;//获得icon的中心最上端坐标，因为如果完全重合就会形成文件夹
		toY = xyt[1];
	
		View viewf = SPEEDDIAL.GetIcons("CellLayout", this, 0, count - 1);//获得最后一个icon
		String name = SPEEDDIAL.GetIconName(viewf);
		viewf.getLocationOnScreen(xyf);//获得中心坐标
		fromX = xyf[0] + viewf.getWidth() / 2;
		fromY = xyf[1] + viewf.getHeight() / 2;
		solo.sleep(Resource.TIME_SMALL);
		SPEEDDIAL.clickLongAndDrag(fromX, toX, fromY, toY, 20,solo);// 交换位置
		solo.sleep(Resource.TIME_SMALL);
		
		
		View view= SPEEDDIAL.GetIcons("CellLayout", this, 0, count - 1);//再次获得最后一个icon的名字，来判断位置变换了
		String name2 = SPEEDDIAL.GetIconName(view);
		boolean toast = !name.equals(name2);
		assertTrue("not change", toast);

	}
}