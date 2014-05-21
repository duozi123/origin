package com.adolphin.testcase.speeddial;

import java.util.ArrayList;

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
	 * 4.在320*480以上的分辨率,长按speed dial进入编辑状态 → 拖动到另一个speed dial
	 * icon的正上方,放开时创建文件夹，同时文件夹展开，文件夹名默认为：Folder 多次添加达到9个，达到12个，知道达到24而之后不能再添加
	 * 
	 * @author xzhou
	 * 
	 */
    @ClassOrder({ PackageOrder.screenspeeddial, 5 })
    @MediumTest
    @TestClass("speeddial添加到文件夹")
public class NewFolder5 extends BaseTest {
	
	public void test_NewFolderTest() throws Exception {
		solo.sleep(5000);
		View viewF;
		View viewT;
		Boolean toast;

		int count;
		int child = SPEEDDIAL.childcount30(solo,"Workspace");
		if (child > 25)
			count = 22;
		else count=child;
		

		// 添加第一个文件夹

		viewF = SPEEDDIAL.GetIcons30(solo, child-2,"Workspace");
		viewT = SPEEDDIAL.GetIcons30(solo, child-3,"Workspace");
		clickLongAndDrag(viewF, viewT);
		solo.sleep(Resource.TIME_SMALL);

		// 判断已经建立一个文件夹
		toast = solo.searchText("Folder");
		assertTrue("not found floder", toast);
        
		// 循环添加
		for (int j = 1; j <= count; j++) {
		   
		    solo.sleep(Resource.TIME_SMALL);
			viewF = SPEEDDIAL.FindIcons30(solo);
			
			viewT =SPEEDDIAL.FindFolder30("Folder", solo);
			clickLongAndDrag(viewF, viewT);
			solo.sleep(Resource.TIME_SMALL);
		}

		// 添加第25个，达到24个不能再添加
		
		viewF = SPEEDDIAL.FindIcons30(solo);
		viewT =SPEEDDIAL.FindFolder30("Folder", solo);
		clickLongAndDrag(viewF, viewT);
		solo.sleep(Resource.TIME_SMALL);

		// 判断地25个没有添加成功
		ViewGroup g = (ViewGroup) viewF;
		TextView textview = (TextView) g.getChildAt(1);
		toast = solo.searchText(textview.getText().toString());
		assertTrue("not only 24", toast);

	}

}
