/**
 * 
 */
package com.qian.my2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wuhuaiqian
 * @param <Point>
 *
 */
public class GameView extends android.widget.GridLayout {

	private Card[][] cards = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<>();

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

	private void intiGameView() {
		this.setColumnCount(4);
		this.setBackgroundColor(0xffbbada0);// 设置背景色
		this.setOnTouchListener(new OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getX();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					// 水平方向上的移动大于垂直方向是的移动
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeDown();
						} else if (offsetY > 5) {
							swipeUp();
						}
					}
				}

				return true;//
			}
		});

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		// 根据不同手机屏幕的尺寸大小计算每个卡片的边长
		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCards(cardWidth, cardWidth);
		startGame();

	}

	private void addCards(int cardWidth, int cardHeight) {
		// TODO Auto-generated method stub
		Card c;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				cards[x][y] = c;
			}
		}

	}

	private void swipeRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >=0 ; x--) {
				for(int x1=x-1;x1>=0;x1--){
					if(cards[x1][y].getNum()>=0){
						if(cards[x][y].getNum()<=0){
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x++;
							break;
						}
						else if (cards[x][y].equals(cards[x1][y])) {
							cards[x][y].setNum(cards[x][y].getNum()*2);
							cards[x1][y].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private void swipeLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for(int x1=x+1;x1<4;x1++){
					if(cards[x1][y].getNum()>=0){
						if(cards[x][y].getNum()<=0){
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x--;
							break;
						}
						else if (cards[x][y].equals(cards[x1][y])) {
							cards[x][y].setNum(cards[x][y].getNum()*2);
							cards[x1][y].setNum(0);
							break;
						}
					}
				}
			}
		}

	}

	private void swipeUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for(int y1=y+1;y1<4;y1++){
					if(cards[x][y1].getNum()>=0){
						if(cards[x][y].getNum()<=0){
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y--;
							break;
						}
						else if (cards[x][y].equals(cards[x][y1])) {
							cards[x][y].setNum(cards[x][y].getNum()*2);
							cards[x][y1].setNum(0);
							break;
						}
					}
				}
			}
		}

	}

	private void swipeDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >=0; y--) {
				for(int y1=y-1;y1>=0;y1--){
					if(cards[x][y1].getNum()>=0){
						if(cards[x][y].getNum()<=0){
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y--;
							break;
						}
						else if (cards[x][y].equals(cards[x][y1])) {
							cards[x][y].setNum(cards[x][y].getNum()*2);
							cards[x][y1].setNum(0);
							break;
						}
					}
				}
			}
		}

	}

	private void startGame() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cards[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	private void addRandomNum() {
		emptyPoints.clear();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
		cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

}
