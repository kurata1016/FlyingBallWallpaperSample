package jp.co.techfun.flyingballwallpaper;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class Ball extends ShapeDrawable {
	// 乱数生成用
	private static final Random RDM = new Random();

	// 画面の幅
	int width;
	// 画面の高さ
	int height;

	// 画面の中心座標
	int centerX;
	int centerY;

	// 玉を表示する位置
	float ballX;
	float ballY;

	// 玉を移動する距離
	float movX;
	float movY;

	// 玉の直径
	int ballDiameter;

	// コンストラクタ
	public Ball() {
		super(new OvalShape());

		// 初期値を設定
		ballDiameter = 150;

		ballX = -ballDiameter;
		ballY = -ballDiameter;
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);

		// 画面の幅を取得
		width = canvas.getWidth();
		// 画面の高さを取得
		height = canvas.getHeight();
		// 画面の中心を再計算
		centerX = width / 2 - ballDiameter / 2;
		centerY = height / 2 - ballDiameter / 2;

		// 円を移動
		ballX += movX;
		ballY += movY;

	}

	// resetPositionメソッド(玉の表示位置再設定処理)
	public void resetPosition() {

		// 円の直径を変更
		ballDiameter = 75 * (RDM.nextInt(4) + 1);

		// 画面の中心を再計算
		centerX = width / 2 - ballDiameter / 2;
		centerY = height / 2 - ballDiameter / 2;

		// 横の出現位置
		int rdmX = RDM.nextInt(width + ballDiameter) - ballDiameter;
		// 縦の出現位置
		int rdmY = RDM.nextInt(height + ballDiameter) - ballDiameter;

		// 円の出現方向を決定
		switch (RDM.nextInt(4)) {
		case 0:
			// 画面上部より出現
			ballX = rdmX;
			ballY = -ballDiameter;
			break;

		case 1:
			// 画面右側より出現
			ballX = width;
			ballY = rdmY;
			break;

		case 2:
			// 画面下部より出現
			ballX = rdmX;
			ballY = height;
			break;

		default:
			// 画面左端より出現
			ballX = -ballDiameter;
			ballY = rdmY;
			break;
		}

		// 1ステップで移動する距離を変更
		// 乱数で生成した値(1～3)を乗算し、違いを明確にする
		int step = (RDM.nextInt(2) + 1) * 3;

		// 画面中央までの距離から1ステップで移動する距離を算出
		float difX = Math.abs(ballX - centerX);
		float difY = Math.abs(ballY - centerY);
		if (difX < difY) {
			movX = difX / difY * step;
			movY = step;
		} else {
			movX = step;
			movY = difY / difX * step;
		}

		// 移動方向を決定
		// 右端から出現する場合、左方向へ移動
		if (width / 2 < ballX) {
			movX = -movX;
		}
		// 下部から出現する場合、上方向へ移動
		if (height / 2 < ballY) {
			movY = -movY;
		}
		
		Ball.this.getPaint().setShader(getRandomRadialGradient(ballDiameter));
	}
	
	// getRandomRadialGradientメソッド(ランダムグラデーション取得処理)
	private Shader getRandomRadialGradient(int ballDiameter) {

		// ランダムにグラデーションを選択
		switch (RDM.nextInt(4)) {
		case 0:
			// 赤
			return new RadialGradient(15, 15, ballDiameter, Color.YELLOW,
					Color.RED, Shader.TileMode.MIRROR);

		case 1:
			// 黄
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.YELLOW, Shader.TileMode.MIRROR);

		case 2:
			// 青
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.BLUE, Shader.TileMode.MIRROR);

		default:
			// 緑
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.GREEN, Shader.TileMode.MIRROR);
		}
	}
}
