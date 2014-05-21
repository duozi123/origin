package com.adolphin.testcase.speeddial;

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;
/**
 * 19.打开一个Tab“www.dailymotion.com”并点击menu -> Add to speed dial,添加快速访问的弹框中显示网页的Title以及对应的URL
 * 弹框中点击Add按钮,屏幕出现该Speeddial的iCon，iCon底部名称显示为“Dailymotion”
 * 同样访问“www.nutshellsoft.com" “www.foreignpolicy.com"
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 19})
@MediumTest
@TestClass("添加“www.dailymotion.com” “www.nutshellsoft.com” “www.foreignpolicy.com”为 speed dial")
public class AddByWeb2_19 extends BaseTest {

	public void test_AddByWeb2Test() throws Exception {
		solo.sleep(5000);
		Boolean toast;
		
		// 添加dailymotion为speeddial
		visitUrl("touch.dailymotion.com");// 访问dailymotion
		waitForPageLoad(20000);// 等待页面加载完
		
		solo.sleep(Resource.TIME_BIG);
		solo.sleep(Resource.TIME_BIG);
		// clickOnDolphinMenuBar(3);//点下面工具条
		// solo.clickOnView(solo.getViewByPath("menu_bar[2]"));
		solo.sleep(Resource.TIME_SMALL);
		clickOnDolphinMenuItem("Add");// 包括点下面工具条
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("option_add_speeddial");// 按照id点击按钮 加为speeddial

		// 判断是否有对话框
		toast = solo.waitForDialogToOpen();// solo.searchText("ADD SPEED DIAL");
		assertTrue("not found dialogue", toast);

		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("OK");// 点击add
		
		//判断是否添加成功
		toast = solo.waitForText("Successfully added to speed dial");
		assertTrue("not found toast", toast);
		solo.sleep(Resource.TIME_SMALL);
		clickOnDolphinMenuItem("Home");// 包括点下面工具条
		solo.sleep(Resource.TIME_SMALL);
		
		//回首页看是否有dailymotion的speeddial
		toast = solo.searchText("Dailymotion");
		assertTrue("not found speeddial", toast);
		solo.sleep(Resource.TIME_SMALL);
	
		
		////////////////////////////////////////////////
		
		// 添加nutshellsoft为speeddial
				visitUrl("nutshellsoft.com");// 访问dailymotion
				waitForPageLoad(20000);// 等待页面加载完
				
				solo.sleep(Resource.TIME_BIG);
				solo.sleep(Resource.TIME_BIG);
				// clickOnDolphinMenuBar(3);//点下面工具条
				// solo.clickOnView(solo.getViewByPath("menu_bar[2]"));
				solo.sleep(Resource.TIME_SMALL);
				clickOnDolphinMenuItem("Add");// 包括点下面工具条
				solo.sleep(Resource.TIME_SMALL);
				solo.clickOnView("option_add_speeddial");// 按照id点击按钮 加为speeddial

				// 判断是否有对话框
				toast = solo.waitForDialogToOpen();// solo.searchText("ADD SPEED DIAL");
				assertTrue("not found dialogue", toast);

				solo.sleep(Resource.TIME_SMALL);
				solo.clickOnView("OK");// 点击add
				
				//判断是否添加成功
				toast = solo.waitForText("Successfully added to speed dial");
				assertTrue("not found toast", toast);
				solo.sleep(Resource.TIME_SMALL);
				clickOnDolphinMenuItem("Home");// 包括点下面工具条
				solo.sleep(Resource.TIME_SMALL);
				
				//回首页看是否有nutshellsoft的speeddial
				toast = solo.searchText("便民导航");
				assertTrue("not found speeddial", toast);
				solo.sleep(Resource.TIME_SMALL);
				
				
				
				/////////////////////////////////////////////
				
				// 添加foreignpolicy为speeddial
				visitUrl("foreignpolicy.com");// 访问foreignpolicy
				waitForPageLoad(20000);// 等待页面加载完
				
				solo.sleep(Resource.TIME_BIG);
				solo.sleep(Resource.TIME_BIG);
				
				// clickOnDolphinMenuBar(3);//点下面工具条
				// solo.clickOnView(solo.getViewByPath("menu_bar[2]"));
				solo.sleep(Resource.TIME_SMALL);
				clickOnDolphinMenuItem("Add");// 包括点下面工具条
				solo.sleep(Resource.TIME_SMALL);
				solo.clickOnView("option_add_speeddial");// 按照id点击按钮 加为speeddial

				// 判断是否有对话框
				toast = solo.waitForDialogToOpen();// solo.searchText("ADD SPEED DIAL");
				assertTrue("not found dialogue", toast);

				solo.sleep(Resource.TIME_SMALL);
				solo.clickOnView("OK");// 点击add
				
				//判断是否添加成功
				toast = solo.waitForText("Successfully added to speeddial");
				assertTrue("not found toast", toast);
				solo.sleep(Resource.TIME_SMALL);
				clickOnDolphinMenuItem("Home");// 包括点下面工具条
				solo.sleep(Resource.TIME_SMALL);
				
				//回首页看是否有foreignpolicy的speeddial
				toast = solo.searchText("Foreign Polic.");
				assertTrue("not found speeddial", toast);
				solo.sleep(Resource.TIME_SMALL);
			
				


	}
}