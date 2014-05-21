package com.adolphin.testcase.speeddial;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 2. 打开一个网页www.baidu.com, 等待加载完全时, 点击menu -> Add to speed dial 点击Add 按钮
 * 首页新增“百度一下”的快速访问
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 20 })
@MediumTest
@TestClass("新增百度为speeddial")
public class AddByWebTest20 extends BaseTest {
	
	


	public void test_AddbyWeb() throws Exception {
		solo.sleep(5000);
		// 添加百度为speeddial
		visitUrl("baidu.com");// 访问百度
		waitForPageLoad(10000);// 等待页面加载完
		
		solo.sleep(Resource.TIME_BIG);
		// clickOnDolphinMenuBar(3);//点下面工具条
		// solo.clickOnView(solo.getViewByPath("menu_bar[2]"));
		solo.sleep(Resource.TIME_SMALL);
		clickOnDolphinMenuItem("Add");// 包括点下面工具条
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("option_add_speeddial");// 按照id点击按钮 加为speeddial

		// 判断是否有对话框
		Boolean toast = solo.waitForDialogToOpen();// solo.searchText("ADD SPEED DIAL");
		assertTrue("not found dialogue", toast);

		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("OK");// 点击add
		
		//判断是否添加成功
		toast = solo.waitForText("Successfully added to speed dial");
		assertTrue("not found toast", toast);
		solo.sleep(Resource.TIME_SMALL);
		clickOnDolphinMenuItem("Home");// 包括点下面工具条
		solo.sleep(Resource.TIME_SMALL);
		
		//回首页看是否有百度的speeddial
		toast = solo.searchText("百度一下");
		assertTrue("not found speeddial", toast);
		solo.sleep(Resource.TIME_SMALL);

	}

}
