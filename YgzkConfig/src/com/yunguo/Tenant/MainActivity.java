package com.yunguo.Tenant;


import java.util.ArrayList;

import com.yunguo.TenantAdapter.MainPagerAdapter;
import com.yunguo.TenantFragment.HouseMessageFragment;
import com.yunguo.TenantFragment.RecordFragment;
import com.yunguo.TenantFragment.UserMessageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity{
	
	/**
	 *������
	 */
	private RadioGroup navigete_radiogroup;
	private RadioButton house_radio;
	private RadioButton record_raio;
	private RadioButton user_raio;
	
	
	/**
	 * ѡ�
	 */
	private ViewPager mainpager;
	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();//fragmeng  list
	private MainPagerAdapter myAdapter;//fragment  adapter
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		/**
		 * ���ҿؼ�ID
		 */
		findView();
		
		/**
		 * setAdapter
		 */
		initPageAdapter();
		
		/**
		 * ��Ӽ���
		 */
		addOnClik();
	}
	
	/**
	 * ���ҿؼ�
	 */
	public void findView(){
		navigete_radiogroup = (RadioGroup) findViewById(R.id.navigete_radiogroup);
		house_radio = (RadioButton) findViewById(R.id.house_radio);
		record_raio = (RadioButton) findViewById(R.id.record_raio);
		user_raio = (RadioButton) findViewById(R.id.user_raio);
		
		mainpager = (ViewPager) findViewById(R.id.mainpager);
	}
	
	public void initPageAdapter() {
		pagerItemList.add(new HouseMessageFragment());
		pagerItemList.add(new RecordFragment());
		pagerItemList.add(new UserMessageFragment());
		
		
		myAdapter = new MainPagerAdapter(getSupportFragmentManager(), pagerItemList);
		mainpager.setAdapter(myAdapter);
	}
	
	/**
	 * ��Ӽ���
	 */
	@SuppressWarnings("deprecation")
	public void addOnClik(){
		/**
		 * Ϊ��������Ӽ���
		 */
		navigete_radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.house_radio:
					mainpager.setCurrentItem(0);
					break;
				case R.id.record_raio:
					mainpager.setCurrentItem(1);
					break;
				case R.id.user_raio:
					mainpager.setCurrentItem(2);
					break;
				}
			}
		});
		
		/**
		 * viewPger ��������
		 */
		
		mainpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					house_radio.setChecked(true);
					break;
				case 1:
					record_raio.setChecked(true);
					break;
				case 2:
					user_raio.setChecked(true);
					break;
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
	}
}
