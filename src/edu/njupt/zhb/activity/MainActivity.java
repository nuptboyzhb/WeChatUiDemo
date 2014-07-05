package edu.njupt.zhb.activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;
import edu.njupt.zhb.adapter.WeChatPagerAdapter;
import edu.njupt.zhb.fragment.ChatFragment_;
import edu.njupt.zhb.fragment.ContactsFragment_;
import edu.njupt.zhb.fragment.DiscoveryFragment_;
import edu.njupt.zhb.view.PagerSlidingTabStrip;
import edu.njupt.zhb.wechatui.R;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-7-5  Nanjing,njupt,China
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
	
	@ViewById
	PagerSlidingTabStrip tabs;

	@ViewById(R.id.pager)
	ViewPager pager;
	
	private DisplayMetrics dm;
	
	@AfterViews
	void onCreate() {
		setOverflowShowingAlways();
		dm = getResources().getDisplayMetrics();
		WeChatPagerAdapter weChatPagerAdapter = new WeChatPagerAdapter(
				getSupportFragmentManager());
		weChatPagerAdapter.setTitles(new String[] {
				getString(R.string.title_chat),
				getString(R.string.title_discovery),
				getString(R.string.title_contacts) });
		weChatPagerAdapter.setFragments(new Fragment[] { new ChatFragment_(),
				new DiscoveryFragment_(), new ContactsFragment_() });
		pager.setAdapter(weChatPagerAdapter);
		tabs.setViewPager(pager);
		setTabsValue();
	}

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值。
	 */
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(Color.parseColor("#45c01a"));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}