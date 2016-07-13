/**
 * 
 */
package com.qian.my2048;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author wuhuaiqian
 *
 */
public class GameView extends android.widget.GridLayout {

	public GameView(Context context) {
		super(context);
		intiGameView();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		intiGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		intiGameView();
	}
	private void intiGameView(){
		
	}

}
