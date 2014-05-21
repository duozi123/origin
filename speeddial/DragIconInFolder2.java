package com.adolphin.testcase.speeddial;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;
/**
 * 9.随意拖动文件夹中的几个iCon,iCon之间可以切换位置
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 2 })
@MediumTest
@TestClass("随意拖动文件夹中的几个iCon,iCon之间可以切换位置")
public class DragIconInFolder2 extends BaseTest {
	public void test_SplidIconInFolderTest() throws Exception {
		solo.sleep(5000);
		View viewF;
		View viewT;
	
		View view = SPEEDDIAL.FindFolder("CellLayout", this);// 找到主页第一个文件夹
		solo.clickOnView(view);
		int count = SPEEDDIAL.childcount("CellLayout", 1, this);
		viewF = SPEEDDIAL.GetIcons("CellLayout", this, 1, count - 1);//找到folder第一个icon

		String text1 = SPEEDDIAL.GetIconName(viewF);// 获得第一个icon的名称

		viewT = SPEEDDIAL.GetIcons("CellLayout", this, 1, count - 2);//找到folder第二个icon

		clickLongAndDrag(viewF, viewT);// 交换位置

		solo.sleep(Resource.TIME_SMALL);

		View view2 = SPEEDDIAL.GetIcons("CellLayout", this, 1, count - 1);//交换后找到folder第一个icon

		String text2 =SPEEDDIAL.GetIconName(view2);// 再次获得第一个icon的名称
		assertTrue("not move", !text1.equals(text2));// 判断两次获得的名称不一样，确认位置发生变换

		solo.sleep(Resource.TIME_SMALL);

		
	}
}