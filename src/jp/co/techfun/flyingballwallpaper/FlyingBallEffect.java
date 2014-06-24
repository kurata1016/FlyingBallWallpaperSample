package jp.co.techfun.flyingballwallpaper;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

// ��щ��ʂ̃G�t�F�N�g��\������N���X
public class FlyingBallEffect {

	// ���������p
	private static final Random RDM = new Random();

	// ��ʂ̕�
	private int width;
	// ��ʂ̍���
	private int height;

	// ��ʂ̒��S���W
	private int centerX;
	private int centerY;

	// ��щ���
	private ShapeDrawable ball;
	private ShapeDrawable ball2;

	// �ʂ�\������ʒu
	private float ballX;
	private float ballY;
	private float ballX2;
	private float ballY2;

	// �ʂ��ړ����鋗��
	private float movX;
	private float movY;
	private float movX2;
	private float movY2;

	// �ʂ̒��a
	private int ballDiameter;
	private int ballDiameter2;

	// �R���X�g���N�^
	public FlyingBallEffect() {
		// �����l��ݒ�
		ballDiameter = 150;
		ballDiameter2 = 150;

		ballX = -ballDiameter;
		ballY = -ballDiameter;
		ballX2 = -ballDiameter2;
		ballY2 = -ballDiameter2;

		// �ʃI�u�W�F�N�g�𐶐�
		ball = new ShapeDrawable(new OvalShape());
		ball2 = new ShapeDrawable(new OvalShape());
	}

	// draw���\�b�h(�ǎ��`�揈��)
	public void draw(Canvas canvas) {

		// ��ʂ̕����擾
		width = canvas.getWidth();
		// ��ʂ̍������擾
		height = canvas.getHeight();
		// ��ʂ̒��S���Čv�Z
		centerX = width / 2 - ballDiameter2 / 2;
		centerY = height / 2 - ballDiameter2 / 2;

		// �~���ړ�
		ballX += movX;
		ballY += movY;
		ballX2 += movX2;
		ballY2 += movY2;

		// ��ʂ��N���A
		canvas.drawColor(Color.BLACK);

		canvas.save();

		ball.setBounds((int) ballX, (int) ballY, (int) ballX + ballDiameter,
				(int) ballY + ballDiameter);
		ball2.setBounds((int) ballX2, (int) ballY2, (int) ballX2 + ballDiameter2,
				(int) ballY2 + ballDiameter2);
		ball.draw(canvas);
		ball2.draw(canvas);

		canvas.restore();

		// ��ʊO�֋ʂ��o���ꍇ
		if (width < ballX || height < ballY || ballX + ballDiameter <= 0
				|| ballY + ballDiameter <= 0) {

			// �ʂ̕\���ʒu���Đݒ�
			resetPosition();
		}
		
		if (width < ballX2 || height < ballY2 || ballX2 + ballDiameter2 <= 0
				|| ballY2 + ballDiameter2 <= 0){
			
			resetPosition2();
		}
	}

	// resetPosition���\�b�h(�ʂ̕\���ʒu�Đݒ菈��)
	private void resetPosition() {

		// �~�̒��a��ύX
		ballDiameter = 75 * (RDM.nextInt(4) + 1);

		// ��ʂ̒��S���Čv�Z
		centerX = width / 2 - ballDiameter / 2;
		centerY = height / 2 - ballDiameter / 2;

		// ���̏o���ʒu
		int rdmX = RDM.nextInt(width + ballDiameter) - ballDiameter;
		// �c�̏o���ʒu
		int rdmY = RDM.nextInt(height + ballDiameter) - ballDiameter;

		// �~�̏o������������
		switch (RDM.nextInt(4)) {
		case 0:
			// ��ʏ㕔���o��
			ballX = rdmX;
			ballY = -ballDiameter;
			break;

		case 1:
			// ��ʉE�����o��
			ballX = width;
			ballY = rdmY;
			break;

		case 2:
			// ��ʉ������o��
			ballX = rdmX;
			ballY = height;
			break;

		default:
			// ��ʍ��[���o��
			ballX = -ballDiameter;
			ballY = rdmY;
			break;
		}

		// 1�X�e�b�v�ňړ����鋗����ύX
		// �����Ő��������l(1�`3)����Z���A�Ⴂ�𖾊m�ɂ���
		int step = (RDM.nextInt(2) + 1) * 3;

		// ��ʒ����܂ł̋�������1�X�e�b�v�ňړ����鋗�����Z�o
		float difX = Math.abs(ballX - centerX);
		float difY = Math.abs(ballY - centerY);
		if (difX < difY) {
			movX = difX / difY * step;
			movY = step;
		} else {
			movX = step;
			movY = difY / difX * step;
		}

		// �ړ�����������
		// �E�[����o������ꍇ�A�������ֈړ�
		if (width / 2 < ballX) {
			movX = -movX;
		}
		// ��������o������ꍇ�A������ֈړ�
		if (height / 2 < ballY) {
			movY = -movY;
		}

		// �ʂ̐F������
		ball.getPaint().setShader(getRandomRadialGradient(ballDiameter));
	}
	
	private void resetPosition2() {

		// �~�̒��a��ύX
		ballDiameter2 = 75 * (RDM.nextInt(4) + 1);

		// ��ʂ̒��S���Čv�Z
		centerX = width / 2 - ballDiameter2 / 2;
		centerY = height / 2 - ballDiameter2 / 2;

		// ���̏o���ʒu
		int rdmX = RDM.nextInt(width + ballDiameter2) - ballDiameter2;
		// �c�̏o���ʒu
		int rdmY = RDM.nextInt(height + ballDiameter2) - ballDiameter2;

		// �~�̏o������������
		switch (RDM.nextInt(4)) {
		case 0:
			// ��ʏ㕔���o��
			ballX2 = rdmX;
			ballY2 = -ballDiameter2;
			break;

		case 1:
			// ��ʉE�����o��
			ballX2 = width;
			ballY2 = rdmY;
			break;

		case 2:
			// ��ʉ������o��
			ballX2 = rdmX;
			ballY2 = height;
			break;

		default:
			// ��ʍ��[���o��
			ballX2 = -ballDiameter2;
			ballY2 = rdmY;
			break;
		}

		// 1�X�e�b�v�ňړ����鋗����ύX
		// �����Ő��������l(1�`3)����Z���A�Ⴂ�𖾊m�ɂ���
		int step = (RDM.nextInt(2) + 1) * 3;

		// ��ʒ����܂ł̋�������1�X�e�b�v�ňړ����鋗�����Z�o
		float difX = Math.abs(ballX2 - centerX);
		float difY = Math.abs(ballY2 - centerY);
		if (difX < difY) {
			movX2 = difX / difY * step;
			movY2 = step;
		} else {
			movX2 = step;
			movY2 = difY / difX * step;
		}

		// �ړ�����������
		// �E�[����o������ꍇ�A�������ֈړ�
		if (width / 2 < ballX2) {
			movX2 = -movX2;
		}
		// ��������o������ꍇ�A������ֈړ�
		if (height / 2 < ballY2) {
			movY2 = -movY2;
		}

		// �ʂ̐F������
		ball2.getPaint().setShader(getRandomRadialGradient(ballDiameter2));
	}

	// getRandomRadialGradient���\�b�h(�����_���O���f�[�V�����擾����)
	private Shader getRandomRadialGradient(int ballDiameter) {

		// �����_���ɃO���f�[�V������I��
		switch (RDM.nextInt(4)) {
		case 0:
			// ��
			return new RadialGradient(15, 15, ballDiameter, Color.YELLOW,
					Color.RED, Shader.TileMode.MIRROR);

		case 1:
			// ��
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.YELLOW, Shader.TileMode.MIRROR);

		case 2:
			// ��
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.BLUE, Shader.TileMode.MIRROR);

		default:
			// ��
			return new RadialGradient(15, 15, ballDiameter, Color.CYAN,
					Color.GREEN, Shader.TileMode.MIRROR);
		}
	}
}
