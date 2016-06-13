package com.yunguo.TenantFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.Tenant.R;
import com.yunguo.Tenant.View.DoorMessageActivity;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class HouseMessageFragment extends Fragment{
	
	/**
	 * ������
	 */
	private View view;
	
	/**
	 * listview
	 */
	private PullToRefreshListView HouseMessge_list;
	
	/**
	 * �����б�������
	 */
	private HouseMessageAdapter messgeListAdapter;
	private ArrayList<Map<String,String>> houseMessageList = new ArrayList<Map<String,String>>(); 
	List<Map<String,String>> listtmp = new ArrayList<Map<String,String>>();//��ʱ����
	
	/**
	 * ��������
	 */
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();
	String masg = "";//������Ϣ
	
	/**
	 * �ȴ�����
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * ��¼��������
	 */
	private Boolean fls = true;
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		
		 if(isVisibleToUser){
             //ֻ�е���Fragment���û��ɼ���ʱ��,�ż�����������
			//��ʾ�ȴ���
			progressDialog = ProgressDialog.show(getActivity(), "���Ե�", "���ڻ�ȡ...", true);
			//��ȡ����
			new Thread(thread).start(); 
     }else{
             //���򲻼�����������
     }
	}
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.housemesage_fragment,null);
		
		/**
		 * ���ҿؼ�
		 */
		findView();
		
		/**
		 * ��ʼ��������
		 */
		setAdapter();
		
		/**
		 * ��Ӽ���
		 */
		setOnClik();
		
		return view;
	}
	
	public void findView(){
		HouseMessge_list = (PullToRefreshListView) view.findViewById(R.id.HouseMessge_list);
		HouseMessge_list.setMode(Mode.BOTH);
	}
	
	public void setAdapter(){
		messgeListAdapter = new HouseMessageAdapter(houseMessageList, getActivity());
		HouseMessge_list.setAdapter(messgeListAdapter);
	}
	
	
	public void setOnClik(){
		/**
		 * ����ˢ�¼���
		 */
		HouseMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// ������ʱ���������� 
				//��ȡ����
				new Thread(thread).start(); 
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // ������ʱ�����ѡ��  
				//��ȡ����
				new Thread(thread).start(); 
			}
        }); 
		
		
		HouseMessge_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),DoorMessageActivity.class);
				@SuppressWarnings("unchecked")
				Map<String,String> map = (Map<String, String>) messgeListAdapter.getItem(arg2-1);
				intent.putExtra("HouseId", map.get("HouseId"));
				startActivity(intent);
				
			}
		});
	}
	
	
	/**
	 * ��������
	 */
	Handler handler = new Handler(){
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				 houseMessageList.clear();
				 houseMessageList.addAll(listtmp); 
				 messgeListAdapter.notifyDataSetChanged();
				 HouseMessge_list.setAdapter(messgeListAdapter);
				break;
			case 1:
				break;
			case 2:
				break;
			}
			
			/**
			 * ֹͣˢ��
			 */
			HouseMessge_list.onRefreshComplete();
			/**
			 * �رյȴ���
			 */
			if(progressDialog != null){
				progressDialog.dismiss();
			}
			
			Toast.makeText(getContext(), masg, Toast.LENGTH_SHORT).show();
		};
	};
	
	
	/**
	 * ��ѯ������Ϣ
	 */
	Thread thread = new Thread(){
		@Override
		public void run() {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			listtmp.clear();
			for (int i = 0; i < 10; i++) {
				Map<String,String> map = new HashMap<String, String>();
		          map.put("HouseId", "");
		          map.put("OwnerName", "Сǿ");
		          map.put("HouseName", "�������ʻ�԰"+i);
		          map.put("HouseAddress", "�ɶ��аٲ�·��������11��605");
		          listtmp.add(map);
			}
			masg = "��ȡ�ɹ�!";
			handler.sendEmptyMessage(0);
			/*String ret = houseMessageImpl.refreshHouseMessage("id", handler);
			
			if(ret != null){
				try {
					JSONObject jsonObject2 =new JSONObject(ret);
					String tmp = jsonObject2.getString("ret");
					masg = jsonObject2.getString("message");
					*//**
					 * �������
					 *//*
					listtmp.clear();
					if(!tmp.equals("0")){
							JSONArray jsonArray = jsonObject2.getJSONArray("houses"); 
						 for (int i=0;i<jsonArray.length();i++){
							  Map<String,String> map = new HashMap<String, String>();
					          JSONObject jsonObjectSon= (JSONObject)jsonArray.opt(i);
					          map.put("HouseId", jsonObjectSon.getString("HouseId"));
					          map.put("OwnerName", jsonObjectSon.getString("OwnerName"));
					          map.put("HouseName", jsonObjectSon.getString("HouseName"));
					          map.put("HouseAddress", jsonObjectSon.getString("HouseAddress"));
					          listtmp.add(map);
					          }
						 handler.sendEmptyMessage(0);
					}else{
						 handler.sendEmptyMessage(1);
						 masg = "����ʧ�ܣ������Ժ�����!";
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				masg = "��������ʧ�ܣ����������Ƿ���ã�";
				handler.sendEmptyMessage(2);
			}*/
		};
	};
	
	
}
