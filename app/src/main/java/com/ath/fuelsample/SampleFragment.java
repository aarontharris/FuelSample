package com.ath.fuelsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SampleFragment extends Fragment {
	private TextView mLayout;

	@Override
	@Nullable public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
		mLayout = new TextView( getContext() );
		mLayout.setText( "Hello Fragment" );
		return mLayout;
	}

}
