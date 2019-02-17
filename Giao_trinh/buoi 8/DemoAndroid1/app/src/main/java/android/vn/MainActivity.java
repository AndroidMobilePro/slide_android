package android.vn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button mBtnCreate, mBtnList, mBtnDeleteAll;
    private TextView mTvList;
    private EditText mEdtUserName;
    private EditText mEdtPass;
    private EditText mEdtName;
    private Button mBtnLogin;
    private MyDatabase database = new MyDatabase(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreate = (Button) findViewById(R.id.btnCreate);
        mBtnList = (Button) findViewById(R.id.btnList);
        mBtnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        mTvList = (TextView) findViewById(R.id.textView2);
        mEdtUserName = (EditText) findViewById(R.id.edtUserName);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtPass = (EditText) findViewById(R.id.edtPass);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);

        mBtnCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                database.open();
                database.createData(mEdtUserName.getText().toString(), mEdtPass.getText().toString(), mEdtName.getText().toString());
                database.close();
            }
        });

        mBtnList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                database.open();
                String ds = database.getData();
                database.close();
                mTvList.setText(ds);
            }
        });

        mBtnDeleteAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                database.open();
                database.deleteAccountAll();
                database.close();
            }
        });
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}