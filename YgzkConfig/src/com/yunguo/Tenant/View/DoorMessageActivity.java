package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.DoorMessageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class DoorMessageActivity extends Activity{
	
	private DoorMessageAdapter doorMessageAdapter;
	private PullToRefreshListView DoorMessge_list;
	private List<Map<String,String>> doorMessageAdapterList = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.doormessage_activity);
		
		
		findView();
		setAdapter();
		setOnClick();
	}
		
	public void findView(){
		DoorMessge_list = (PullToRefreshListView) findViewById(R.id.DoorMessge_list);
	}
	
	public void setAdapter(){
		doorMessageAdapter = new DoorMessageAdapter(setData(), getApplicationContext());
		DoorMessge_list.setAdapter(doorMessageAdapter);
	}
	
	public void setOnClick(){
		DoorMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// ������ʱ���������� 
				//��ȡ����
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // ������ʱ�����ѡ��  
				//��ȡ����
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
	}
	
	@SuppressWarnings("rawtypes")
	public List setData(){
		
		for (int i = 0; i < 10; i++) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("DoorId", "��ţ�2467001"+i);
			map.put("DoorStatus", "��ǰ״̬���ѹر�");
			//map.put("DoorId", "2467001"+i);
			doorMessageAdapterList.add(map);
		}
		return doorMessageAdapterList;
	}
}
