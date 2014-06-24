package jp.co.techfun.flyingballwallpaper;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class Ball extends ShapeDrawable {
	// ���������p
	private static final Random RDM = new Random();

	// ��ʂ̕�
	int width;
	// ��ʂ̍���
	int height;

	// ��ʂ̒��S���W
	int centerX;
	int centerY;

	// �ʂ�\������ʒu
	float ballX;
	float ballY;

	// �ʂ��ړ����鋗��
	float movX;
	float movY;

	// �ʂ̒��a
	int ballDiameter;

	// �R���X�g���N�^
	public Ball() {
		super(new OvalShape());

		// �����l��ݒ�
		ballDiameter = 150;

		ballX = -ballDiameter;
		ballY = -ballDiameter;
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);

		// ��ʂ̕����擾
		width = canvas.getWidth();
		// ��ʂ̍������擾
		height = canvas.getHeight();
		// ��ʂ̒��S���Čv�Z
		centerX = width / 2 - ballDiameter / 2;
		centerY = height / 2 - ballDiameter / 2;

		// �~���ړ�
		ballX += movX;
		ballY += movY;

	}

	// resetPosition���\�b�h(�ʂ̕\���ʒu�Đݒ菈��)
	public void resetPosition() {

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
		
		Ball.this.getPaint().setShader(getRandomRadialGradient(ballDiameter));
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
