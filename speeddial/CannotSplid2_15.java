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
 * 26.横屏时，不超过一屏时,当speed dial在手机屏幕上可以全部显示，左右或上下滑动屏幕,屏幕不能左右滑动和上下滑动
 * @author xzhou
 * 
 */
@ClassOrder({ PackageOrder.screenspeeddial, 15 })
@MediumTest
@TestClass("横屏时，不超过一屏时,当speed dial在手机屏幕上可以全部显示，左右或上下滑动屏幕,屏幕不能左右滑动和上下滑动")
public class CannotSplid2_15 extends BaseTest {

	public void test_CannotSplidTest() throws Exception {
		solo.sleep(10000);
		
		
		boolean toast=false;
		float screenHeight = getActivity().getWindowManager()
				.getDefaultDisplay().getHeight();
		float screenWidth = getActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		int child=SPEEDDIAL.childcount("CellLayout", 0, this);
		View view=SPEEDDIAL.GetIcons("CellLayout", this, 0, child-1);
		
		int xy1[]=new int[2];
		int xy2[]=new int[2];
		float height= view.getHeight();
		view.getLocationOnScreen(xy1);
		//不可以上下滑动，但是稍微有坐标移动
		if(child <=10){
			solo.drag(screenWidth/2.0f, screenWidth/2.0f,screenHeight/4.0f, screenHeight*3/4.0f, 10);//竖向滑动，从上到下
			solo.sleep(Resource.TIME_SMALL);
			view=SPEEDDIAL.GetIcons("CellLayout", this, 0, child-1);
			view.getLocationOnScreen(xy2);
			if(xy2[1]-xy1[1]<height){
				xy1[1]=xy2[1];
				solo.drag(screenWidth/2.0f, screenWidth/2.0f,screenHeight*3/4.0f, screenHeight/4.0f, 10);//竖向滑动，从下到上
				solo.sleep(Resource.TIME_SMALL);
				view=SPEEDDIAL.GetIcons("CellLayout", this, 0, child-1);
				view.getLocationOnScreen(xy2);
				if(xy1[1]-xy2[1]<height)
					toast=true;
				else
					toast=false;
			}
			//不可以有左右滑动
			if(toast){
				solo.drag(screenWidth/4.0f, screenWidth*3/4.0f,screenHeight/2.0f, screenHeight/2.0f, 10);//横向滑动，从左往右
				solo.sleep(Resource.TIME_SMALL);
				view=SPEEDDIAL.GetIcons("CellLayout", this, 0, child-1);
				view.getLocationOnScreen(xy2);
				if(xy1[0]==xy2[0]){
					solo.drag(screenWidth*3/4.0f,screenWidth/4.0f, screenHeight/2.0f, screenHeight/2.0f, 10);//横向滑动，从右往左
					solo.sleep(Resource.TIME_SMALL);
					view=SPEEDDIAL.GetIcons("CellLayout", this, 0, child-1);
					view.getLocationOnScreen(xy2);
					if(xy1[0]==xy2[0])
						toast=true;
					else
						toast=false;
				}
			}
		}
		assertTrue("can move",toast);
	
	        solo.sleep(Resource.TIME_SMALL);//换回竖屏
	        clickControlPanel("Settings");
	        solo.clickOnText("Customize");
	        solo.sleep(Resource.TIME_SMALL);
	        solo.clickOnText("Orientation");
	        solo.sleep(Resource.TIME_SMALL);
	        solo.clickOnText("Portrait");
	        solo.sleep(Resource.TIME_SMALL);

	
	}
}
