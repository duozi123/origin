package com.adolphin.testcase.speeddial;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.adolphin.common.Utils;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 8.展开文件夹→点击名称右侧的编辑按钮,点击软键盘的删除按钮，删除以前的文件夹名称后不输入新名称，退出编辑状态,文件夹名称变为folder
 * 输入新的名称：test→点击完成,文件夹的名称被替换为test 点击文件夹以外的空白区域 文件夹自动关闭，文件夹iCon下面显示新的名称test
 * 退出程序→再次启动时进入 文件夹的名称依然是test
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 12 })
@MediumTest
@TestClass("修改文件夹名")
public class EditFolderName12 extends BaseTest {
	public void test_EditFolderNameTest() throws Exception {
		solo.sleep(5000);
		Boolean toast;
		
		View view = SPEEDDIAL.FindFolder("CellLayout", this);// 找到主页第一个文件夹
		String name = SPEEDDIAL.GetIconName(view);// 获得文件名
		solo.clickOnView(view);// 打开
		solo.sleep(Resource.TIME_SMALL);

		toast = solo.searchText(name);// 收索文件名

		assertTrue("not show name", toast);// 判断显示了文件名

		// 判断显示在正中间//

		solo.clickOnView("id/folder_name");// 点击编辑栏
		solo.sleep(Resource.TIME_SMALL);

		solo.clearEditText(0);// 删除文件名
		solo.sleep(Resource.TIME_SMALL);
		toast = solo.searchEditText(name);// 搜索文件名
		solo.sleep(Resource.TIME_SMALL);
		assertTrue("not delete name", !toast);// 判断删除了文件名
		solo.sleep(Resource.TIME_SMALL);

		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);

		view = SPEEDDIAL.FindFolder("CellLayout", this);// 找到主页第一个文件夹
		toast = SPEEDDIAL.GetIconName(view).equals("Folder");
		assertTrue(" name is not Folder", toast);// 判断文件名为Folder

		solo.clickOnView(view);// 打开
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("id/folder_name");// 点击编辑栏
		solo.sleep(Resource.TIME_SMALL);
		solo.clearEditText((EditText) solo.getView("id/folder_name"));
		solo.sleep(Resource.TIME_SMALL);
		solo.enterText((EditText) solo.getView("id/folder_name"), "test");
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.sleep(Resource.TIME_SMALL);

		// 点击屏幕空白区域
		int height = getActivity().getWindowManager().getDefaultDisplay()
				.getHeight();
		int width = getActivity().getWindowManager().getDefaultDisplay()
				.getWidth();
		solo.clickOnScreen(width, height / 2);
		solo.sleep(Resource.TIME_SMALL);
		// 判断文件夹名称和收回状态
		assertTrue("not found Folder", solo.searchText("test"));
		solo.sleep(Resource.TIME_SMALL);
	
	}

}
