package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.InfoBean.Historybean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryAdapter;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MyRentHistoryActivity extends Activity {
	/**
	 * listview
	 */
	private PullToRefreshListView HistoryMessge_list;
	/**
	 * �����б�������
	 */
	private HouseHistoryAdapter messgeListAdapter;
	private List<Historybean> data = new ArrayList<Historybean>();
	/**
	 * ��������
	 */
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();
	String masg = "";// ������Ϣ

	/**
	 * �ȴ�����
	 */
	private ProgressDialog progressDialog;

	/**
	 * ��¼��������
	 */
	private Boolean fls = true;

	/*
	 * @Override protected void onResume() { // TODO Auto-generated method stub
	 * super.onResume(); // ��activity����ʱ,������������ // ��ʾ�ȴ��� progressDialog =
	 * ProgressDialog.show(this, "���Ե�", "���ڻ�ȡ...", true); // ��ȡ���� new
	 * Thread(thread).start(); }
	 */

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.history_rent_activity);
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_RentHouseMessge_list);
		data = getData();  
		messgeListAdapter = new HouseHistoryAdapter(data, this);
		HistoryMessge_list.setAdapter(messgeListAdapter);
		HistoryMessge_list.setMode(Mode.BOTH);

		init();
		HistoryMessge_list
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						Historybean bean = new Historybean();  
						bean.setHistoryname("");
						bean.setHistoryownername("zhang");
						bean.setHistoryadress("��������");
						bean.setHistorytime("2015/12/15");   
		               //messgeListAdapter.addFirst(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						Historybean bean = new Historybean();  
						bean.setHistoryname("");
						bean.setHistoryownername("zhang");
						bean.setHistoryadress("��������");
						bean.setHistorytime("2015/12/15");  
						//messgeListAdapter.addLast(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}
				});
	};

	private void init() {
		ILoadingLayout startLabels = HistoryMessge_list.getLoadingLayoutProxy(
				true, false);
		startLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = HistoryMessge_list.getLoadingLayoutProxy(
				false, true);
		endLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		endLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ
	}

	private List<Historybean> getData() {
		List<Historybean> list = new ArrayList<Historybean>();
		for (int i = 0; i < 10; i++) {
			Historybean bean = new Historybean();
			bean.setHistoryname("�ٲ�·"+i+"��");
			bean.setHistoryownername("zhang"+i);
			bean.setHistoryadress("��������"+i);
			bean.setHistorytime("2015/12/15");
			list.add(bean);
		}

		return list;
	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void> {
		List<Historybean> list = new ArrayList<Historybean>();
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
				for (int i = 0; i < 10; i++) {
					Historybean bean = new Historybean();
					bean.setHistoryname("�ٲ�·"+i+"��");
					bean.setHistoryownername("zhang"+i);
					bean.setHistoryadress("��������"+i);
					bean.setHistorytime("2015/12/15");
					list.add(bean);
				}
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			for (int i = 0; i < 10; i++) {
				Historybean bean = new Historybean();
				bean.setHistoryname("�ٲ�·1"+i+"��");
				bean.setHistoryownername("zhang"+i);
				bean.setHistoryadress("��������"+i);
				bean.setHistorytime("2015/12/15");
				data.add(bean);
			}
 
			messgeListAdapter.notifyDataSetChanged();
			HistoryMessge_list.onRefreshComplete();
			Toast.makeText(MyRentHistoryActivity.this,"ִ�н���", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}
}
