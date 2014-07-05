/*
 * $filename: WeChatPagerAdapter.java,v $
 * $Date: 2014-7-5  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package edu.njupt.zhb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-7-5  Nanjing,njupt,China
 */
public class WeChatPagerAdapter extends FragmentPagerAdapter {

	public WeChatPagerAdapter(FragmentManager fm,String[] titles,Fragment [] fragments) {
		super(fm);
		this.titles = titles;
		this.fragments = fragments;
		if(titles.length != fragments.length){
			System.out.println("WeChatPagerAdapter ERROR...");
		}
	}
	
	public WeChatPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	
	private String[] titles;
	
	private Fragment [] fragments;
	
	public void setFragments(Fragment [] fragments){
		this.fragments = fragments;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

}
