package com.mredrock.cyxbs.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.Config;
import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.io.request.ApplicationController;
import com.mredrock.cyxbs.io.request.StringRequest;
import com.mredrock.cyxbs.model.Account;
import com.mredrock.cyxbs.model.AccountCache;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;
import com.mredrock.cyxbs.util.JsonUtils;
import com.mredrock.cyxbs.util.LogUtils;
import com.mredrock.cyxbs.util.SpUtils;
import com.mredrock.cyxbs.util.UIUtils;

import net.smalinuxer.spillover.frameDesign.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;
import static com.mredrock.cyxbs.util.LogUtils.LOGE;
import static com.mredrock.cyxbs.util.LogUtils.LOGI;

/**
 * A login screen that offers login via stu_num/password.
 */
public class LoginActivity extends SwipeBackActivity implements OnClickListener,AdapterView.OnItemClickListener,TextView.OnEditorActionListener {

    private final static String TAG = LogUtils.makeLogTag(LoginActivity.class);

    private SharedPreferences mSP;
    private EditText mEditStuNum;
    private EditText mEditPassword;
    private Button mBtnLogin;
    private ImageView mIvDrop;
    private ListPopupWindow listPopupWindow;
    private AccountCache[] accounts;
    private AccountListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSP = SpUtils.getPreference(LoginActivity.this);

        configureViews();
        fillData();
    }

    private void configureViews() {
        mEditStuNum = (EditText) findViewById(R.id.et_stuNum);
        mEditPassword = (EditText) findViewById(R.id.et_password);

        mBtnLogin = (Button) findViewById(R.id.sign_in_button);
        mIvDrop = (ImageView) findViewById(R.id.iv_login_dropdown);

        mBtnLogin.setOnClickListener(this);
        mIvDrop.setOnClickListener(this);

        mEditStuNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEditPassword.setText("");
                if(accounts!=null){
                    for(int i = 0;i<accounts.length;i++){
                        if(s.toString().equals(accounts[i].getNumber())){
                            mEditPassword.setText(accounts[i].getPassword());
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditPassword.setOnEditorActionListener(this);

        String mAccount=mSP.getString(Config.LOCAL_SP_ACCOUNT, "null");
        if(!mAccount.equals("null")){
            Account main = (Account) JsonUtils.json2Bean(mAccount, Account.class);
            mEditStuNum.setText(main.getStuNum());
            mEditPassword.setText(main.getIdNum());
        }
        mBtnLogin.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        listPopupWindow.setWidth(mBtnLogin.getWidth());
                        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
                        mBtnLogin.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });
        listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAnchorView(mEditStuNum);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                mEditStuNum.setEnabled(false);
                mEditPassword.setEnabled(false);
                mBtnLogin.setEnabled(false);
                attemptLogin();
                break;
            case R.id.iv_login_dropdown:
                listPopupWindow.show();
                break;
            default:
                break;
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() == 6;
    }

    private boolean isStuNumValid(String num) {
        return num.length() == 10;
    }

    private void attemptLogin(){
        String stuNum = mEditStuNum.getText().toString();
        String password = mEditPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (!isStuNumValid(stuNum)) {
            mEditStuNum.setError(getString(R.string.error_invalid_stu_num));
            focusView = mEditStuNum;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            mEditPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEditPassword;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        } else {

            //network request

            Map<String,String> params = new HashMap<String, String>();
            params.put(Config.PARAM_STU_NUM,stuNum);
            params.put(Config.PARAM_ID_NUM,password);

            ApplicationController.add2RequestQueue(new StringRequest(Config.API_VERIFY, new Request.ResponseListener<String>() {
                @Override
                public void callBack(Object responseData) {
                    login(responseData.toString());
                }

                @Override
                public void callErrorBack(byte[] responseContent, String callBackdata) {
//                    LOGE(TAG,callBackdata);
                    if(callBackdata!=null){
                        login(callBackdata);
                    }
                    UIUtils.Toast(LoginActivity.this, getString(R.string.net_error));
                }
            }, params));
        }
        mEditStuNum.setEnabled(true);
        mEditPassword.setEnabled(true);
        mBtnLogin.setEnabled(true);
    }

    private void login(String responseData){
        Account.AccountResult result = (Account.AccountResult) JsonUtils.json2Bean(responseData, Account.AccountResult.class);
        switch (result.getStatus()) {
            case Config.STATUS_SUCCESS:
                Account account;
                account = result.getData();
                if (account == null) {
                    UIUtils.Toast(LoginActivity.this, getString(R.string.edu_no_info));
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));//go to mainActivity firstly,and handle data in background.
                saveData(account);
                LoginActivity.this.finish();
                break;
            case Config.STATUS_AUTHENTICATION_ERROR:
                UIUtils.Toast(LoginActivity.this, getString(R.string.authentication_error));
                break;
            default:
                UIUtils.Toast(LoginActivity.this, getString(R.string.error));
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == R.id.sign_in_button || actionId == EditorInfo.IME_NULL){
            attemptLogin();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mEditStuNum.setText(((AccountCache)(mAdapter.getItem(position))).getNumber());
        mEditPassword.setText(((AccountCache)(mAdapter.getItem(position))).getPassword());

        listPopupWindow.dismiss();
    }

    private void fillData() {
        accounts = (AccountCache[]) JsonUtils.json2Bean(mSP.getString(Config.LOCAL_SP_ACCOUNTS,""),AccountCache[].class);
        mAdapter=new AccountListAdapter(this, accounts);
        listPopupWindow.setAdapter(mAdapter);
    }

    private void saveData(Account mAccount){
        //当账号改变,清空临时数据
        if(getApplicationControllor().getMainAccount()!=null&&!getApplicationControllor().getMainAccount().getStuNum().endsWith(mAccount.getStuNum())){
            getApplicationControllor().clearActivityState();
        }
        getApplicationControllor().setMainAccount(mAccount);

        AccountCache newAccount = new AccountCache();
        newAccount.setName(mAccount.getName());
        newAccount.setNumber(mAccount.getStuNum());
        newAccount.setPassword(mEditPassword.getText().toString());

        mSP.edit().putString(Config.LOCAL_SP_ACCOUNT, JsonUtils.bean2Json(mAccount)).apply();
        AccountCache[] newAccounts=null;
        if(accounts!=null){
            for(AccountCache account:accounts){//历遍看是否已经存过
                if(account.getNumber().equals(newAccount.getNumber())){
                    return;
                }
            }
            //将新账户加进账户列表
            newAccounts= Arrays.copyOf(accounts, accounts.length + 1);
            newAccounts[accounts.length]=newAccount;
        }else{
            //没有账户列表则新建一个
            newAccounts=new AccountCache[1];
            newAccounts[0]=newAccount;
        }
        mSP.edit().putString(Config.LOCAL_SP_ACCOUNTS, JsonUtils.bean2Json(newAccounts)).apply();

    }



    class AccountListAdapter extends BaseAdapter{

        private AccountCache[] accounts;
        private LayoutInflater mInflater;

        public AccountListAdapter(Context ctx,AccountCache[] accounts) {
            this.accounts = accounts;
            mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if(accounts == null){
                return 0;
            }else{
                return accounts.length;
            }

        }

        @Override
        public Object getItem(int position) {
            return accounts[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=mInflater.inflate(R.layout.item_login_dropdown, parent,false);
            ((TextView)(view.findViewById(R.id.number))).setText(accounts[position].getNumber());
            ((TextView)(view.findViewById(R.id.name))).setText(accounts[position].getName());
            return view;
        }
    }
}



