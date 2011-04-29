package com.makotoishida.intent01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Intent01Activity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		Log.d("test", "onDestroy");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

        procData(intent);
	}
	
	private void procData(Intent intent) {
    	
    	if (Intent.ACTION_VIEW.equals(intent.getAction()) ){
			String url = intent.getDataString();
			
			//TextViewにURLを表示
			TextView txt = (TextView)findViewById(R.id.txtUrl);
			txt.setText(url);
			
			//外部ブラウザで開く
			sendViewIntent(url);
    	}
    }

    private void sendViewIntent(String url){
		Intent intent = (Intent)getIntent().clone();
//		intent.setAction(Intent.ACTION_VIEW);
//		intent.addCategory(Intent.CATEGORY_DEFAULT);
//		intent.addCategory(Intent.CATEGORY_BROWSABLE);
//		intent.setData(Uri.parse(url));
		intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
		startActivity(intent);
    }


}