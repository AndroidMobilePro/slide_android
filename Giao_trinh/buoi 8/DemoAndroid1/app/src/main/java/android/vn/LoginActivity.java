package android.vn;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button mBtnCreate, mBtnList, mBtnUpdateName;
	private TextView mTvList;
	private EditText mEdtName, mEdtPass;
	private MyDatabase database = new MyDatabase(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mBtnCreate = (Button) findViewById(R.id.btnCreate);
		mBtnList = (Button) findViewById(R.id.btnList);
		mBtnUpdateName = (Button) findViewById(R.id.btnDeleteAll);
		mTvList = (TextView) findViewById(R.id.textView2);
		mEdtName = (EditText) findViewById(R.id.edtName);
		mEdtPass = (EditText) findViewById(R.id.edtPass);
		
		mBtnCreate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				database.open();
				Boolean ketQuaDangNhap = database.kiemTraLogin(mEdtName.getText().toString(), mEdtPass.getText().toString());
				database.close();
				if (ketQuaDangNhap) {
					Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
				}
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
		
		mBtnUpdateName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				database.open();
				Boolean ketQuaDangNhap = database.kiemTraLogin(mEdtName.getText().toString(), mEdtPass.getText().toString());
				database.close();
				if (ketQuaDangNhap) {
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
					builder.setTitle("Cập nhật tên hiển thị");
					builder.setMessage("Mời nhập tên cần thay đổi:");
					final EditText input = new EditText(LoginActivity.this);
					builder.setView(input);
					builder.setCancelable(false);
					builder.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									database.open();
									Boolean kq = database.setNameHienThi(mEdtName.getText().toString(), mEdtPass.getText().toString(), input.getText().toString());
									database.close();
									if (kq) {
										Toast.makeText(LoginActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
									} else {
										Toast.makeText(LoginActivity.this, "Cập nhật tên thất bại", Toast.LENGTH_SHORT).show();
									}
								}
							});
					builder.setNegativeButton("Hủy",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {

								}
							});

					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main, menu);
		menu.findItem(R.id.i1).setTitle("Trang Chính");
		menu.findItem(R.id.i2).setTitle("Thoát");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.i1:
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.i2:
			finish();
			break;
		
		}
		return false;
	}
}
