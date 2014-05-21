package com.adolphin.testcase.speeddial;

import android.os.SystemClock;

import android.view.MotionEvent;
import android.widget.Scroller;
/**
 * 3. 自定义一个添加speeddial的手势，访问亚马逊网页，将其添加为speeddial，再回主页看是否有亚马逊的speeddial
 * 
 * @author xzhou
 * 
 */

import com.adolphin.common.BaseTest;
import com.adolphin.common.PackageOrder;
import com.adolphin.common.Resource;
import com.test.annotation.ClassOrder;
import com.test.annotation.MediumTest;
import com.test.annotation.TestClass;

/**
 * 3.进入gesture设置页面-> 点击More Actions-> 选择Website Navigation下面的Added to speed
 * dial-> 设置手势Ｂ成功->返回home打开一个网页www.amazon.com,
 * 进入gesture页面，划出手势Ｂ，弹出toast：Successfully added to speed dial
 * 回到首页，首页新增“Amazon”的快速访问
 * 
 * 
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 11 })
@MediumTest
@TestClass("用手势添加speeddial")
public class GestureTest11 extends BaseTest {

	public void test_GestureTest() throws Exception {

		solo.sleep(5000);
		clickOnDolphinMenuBar(5);// 点下面工具条 手势
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("vg_btn_settings");// 按照id点击按钮 点击设置
		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnText("More actions");// 点击这个按钮
		// solo.clickOnView("action_item");// 按照id点击按钮 more actions
		// solo.scrollDown();
		// solo.scrollDownList(list);
		solo.sleep(Resource.TIME_SMALL);
		// solo.clickOnView(solo.getViewByPath("tope_view[0][0][10]"));
		solo.clickOnText("Add to speed dial");// 点击这个按钮
		solo.sleep(Resource.TIME_SMALL);

		SPEEDDIAL.dragB();// 画手势B

		solo.sleep(Resource.TIME_SMALL);
		solo.clickOnView("done");// 点击done完成,手势设置完成

		// 判断是否有完成手势设置的提示
		Boolean toast = solo.waitForText("Gesture was created successfully");
		assertTrue("not found toast", toast);

		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();
		solo.goBack();
		// solo.clickOnText("No, thanks");// 点击这个按钮
		// solo.sleep(Resource.TIME_SMALL);
		// clickOnDolphinMenuItem("Home");//返回主界面
		visitUrl("amazon.com");// 访问亚马逊
		waitForPageLoad(40000);// 等待页面加载完
		solo.sleep(Resource.TIME_SMALL);
		// solo.sleep(40000);

		clickOnDolphinMenuBar(5);// 点下面工具条 手势
		solo.sleep(Resource.TIME_SMALL);
		SPEEDDIAL.dragB();// 画手势B

		// 判断是否由设置成功的提示
		toast = solo.waitForText("Successfully added to speed dial");
		assertTrue("not found toast", toast);
		solo.sleep(Resource.TIME_SMALL);
		solo.goBack();// 回主页

		// 判断主页上是否有Amazon是speeddial
		toast = solo.searchText("Amazon");
		assertTrue("not found toast", toast);
		solo.sleep(Resource.TIME_SMALL);

	}

}
