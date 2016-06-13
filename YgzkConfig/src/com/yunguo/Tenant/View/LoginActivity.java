package com.yunguo.Tenant.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yunguo.Tenant.MainActivity;
import com.yunguo.Tenant.R;

public class LoginActivity extends Activity implements TextWatcher{

	
	private EditText mEtIdcardnumber;
	private EditText mEtpass;
	private Button mBtnLogin;
	private CheckBox mCbremberpass;
	private TextView mTvforgetpass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		initView();
	}
	
	//初始化界面组件
	private void initView(){
		mEtIdcardnumber = (EditText)findViewById(R.id.et_idcardnum);
		mEtpass = (EditText)findViewById(R.id.et_pass);
		mBtnLogin = (Button)findViewById(R.id.btn_login);
		mCbremberpass = (CheckBox)findViewById(R.id.cb_rember);
		mTvforgetpass =(TextView)findViewById(R.id.tv_forget);
		
		mEtIdcardnumber.addTextChangedListener(this);
		
		
		mBtnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
			}
		});
		
		
	}
	
	/**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
     public boolean personIdValidation(String text) {
          String regx = "[0-9]{17}x";
          String reg1 = "[0-9]{15}";
          String regex = "[0-9]{18}";
          return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String idcardnum = mEtIdcardnumber.getText().toString();
		if(idcardnum.equals("")){
			mBtnLogin.setEnabled(false);
		}else{
			if(personIdValidation(idcardnum)){
				mBtnLogin.setEnabled(true);
			}else{
				mBtnLogin.setEnabled(false);	
			}
		}
		
	}
}
