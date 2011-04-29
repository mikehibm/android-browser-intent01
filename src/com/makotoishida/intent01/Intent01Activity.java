package com.makotoishida.intent01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Intent01Activity extends Activity {

	static ArrayAdapter<String> adapter;
	ListView list;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        if (adapter == null){
        	adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
        
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        processIntent(getIntent());
    }
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		processIntent(intent);
	}
	
	private void processIntent(Intent intent) {
    	
    	if (Intent.ACTION_VIEW.equals(intent.getAction()) ){
			String url = intent.getDataString();
			
			//リスト表示用の配列に追加
			if (!isExisting(url)){
				adapter.insert(url, 0);				
			}
			
			//標準ブラウザで開く
			Intent newintent = new Intent(); 
			newintent.fillIn(intent, 0);
			newintent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
			startActivity(newintent);
    	}

		//TextViewに件数を表示
		TextView txt = (TextView)findViewById(R.id.txtCount);
    	if (adapter.getCount() > 0){
    		txt.setText("Count: " + adapter.getCount());
    	} else {
    		txt.setText(R.string.initial_msg);
    	}
	}
	
	private boolean isExisting(String url){
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(url) ){
				return true;
			}
		}
		return false;
	}



}