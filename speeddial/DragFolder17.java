package com.adolphin.testcase.speeddial;

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
 * 7.拖动文件夹到任意的位置 ,可以停留在想拖到的位置
 * 
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 17 })
@MediumTest
@TestClass("拖动文件夹到任意的位置 ,可以停留在想拖到的位置")
public class DragFolder17 extends BaseTest {
	public void test_DragFolderTest() throws Exception{
		solo.sleep(5000);
		int count;
		View viewF=SPEEDDIAL.FindFolder("CellLayout",this);//找到主页第一个文件夹
		
		View viewT=SPEEDDIAL.FindIcons("CellLayout",this,0);//找到第一个icon
		count=SPEEDDIAL.childcount("CellLayout", 0, this);
		
		View view=SPEEDDIAL.GetIcons("CellLayout", this, 0,count-1);//获得homepage第一个位置的名称
		
		String text1=SPEEDDIAL.GetIconName(view);
		solo.sleep(Resource.TIME_SMALL);
		clickLongAndDrag(viewF, viewT);//交换位置
		solo.sleep(Resource.TIME_SMALL);
		
		 view=SPEEDDIAL.GetIcons("CellLayout", this, 0,count-1);
		
		String text2=SPEEDDIAL.GetIconName(view);//再次获得第一个位置的名称
		
		assertTrue("not move", !text1.equals(text2));//判断两次获得的名称不一样，确认位置发生变换
		
		solo.sleep(Resource.TIME_SMALL);
		
		
	}

}
