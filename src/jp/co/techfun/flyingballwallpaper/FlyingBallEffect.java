package jp.co.techfun.flyingballwallpaper;

import android.graphics.Canvas;
import android.graphics.Color;

// ��щ��ʂ̃G�t�F�N�g��\������N���X
public class FlyingBallEffect {
	// ��щ���
	private Ball ball;
	private Ball ball2;
	private Ball ball3;

	// �R���X�g���N�^
	public FlyingBallEffect() {
		// �ʃI�u�W�F�N�g�𐶐�
		ball = new Ball();
		ball2 = new Ball();
		ball3 = new Ball();
	}

	// draw���\�b�h(�ǎ��`�揈��)
	public void draw(Canvas canvas) {
		// ��ʂ��N���A
		canvas.drawColor(Color.BLACK);
		canvas.save();

		ball.setBounds((int) ball.ballX, (int) ball.ballY, (int) ball.ballX + ball.ballDiameter,
				(int) ball.ballY + ball.ballDiameter);
		ball2.setBounds((int) ball2.ballX, (int) ball2.ballY, (int) ball2.ballX + ball2.ballDiameter,
				(int) ball2.ballY + ball2.ballDiameter);
		ball3.setBounds((int) ball3.ballX, (int) ball3.ballY, (int) ball3.ballX + ball3.ballDiameter,
				(int) ball3.ballY + ball3.ballDiameter);
		ball.draw(canvas);
		ball2.draw(canvas);
		ball3.draw(canvas);

		canvas.restore();

		// ��ʊO�֋ʂ��o���ꍇ
		if (ball.width < ball.ballX || ball.height < ball.ballY || ball.ballX + ball.ballDiameter <= 0
				|| ball.ballY + ball.ballDiameter <= 0) {

			// �ʂ̕\���ʒu���Đݒ�
			ball.resetPosition();
		}
		
		if (ball2.width < ball2.ballX || ball2.height < ball2.ballY || ball2.ballX + ball2.ballDiameter <= 0
				|| ball2.ballY + ball2.ballDiameter <= 0){
			
			ball2.resetPosition();
		}
		
		if (ball3.width < ball3.ballX || ball3.height < ball3.ballY || ball3.ballX + ball3.ballDiameter <= 0
				|| ball3.ballY + ball3.ballDiameter <= 0){
			
			ball3.resetPosition();
		}
	}
}
