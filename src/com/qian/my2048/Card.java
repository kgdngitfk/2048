package com.qian.my2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	private int num = 0;
	private TextView label;

	public Card(Context context) {
		super(context);
		label = new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(Gravity.CENTER);
		label.setBackgroundColor(0x33ffffff);// 调整卡片背景
		LayoutParams lp = new LayoutParams(-1, -1);
		addView(label, lp);
		lp.setMargins(10, 10, 0, 0);// 调整每个卡片间距
		setNum(0);

	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if (num <= 0)
			label.setText("");
		else
			label.setText(num + "");
	}

	public boolean equals(Card c) {
		// TODO Auto-generated method stub
		return getNum() == c.getNum();
	}

}
