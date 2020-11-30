package RhythmGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		if (DynamicBeat.game == null) {
			return;
		}
		if (e.getButton() == MouseEvent.BUTTON1) {
			DynamicBeat.game.pressL();
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			DynamicBeat.game.pressR();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (DynamicBeat.game == null) {
			return;
		}
		if (e.getButton() == MouseEvent.BUTTON1) {
			DynamicBeat.game.releaseL();
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			DynamicBeat.game.releaseR();
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int n = e.getWheelRotation();
		if (n < 0) {
			// 위로{
			if (Main.wheelTime == 0) {
				Main.wheelTime = System.currentTimeMillis();
			}
			DynamicBeat.game.pressW();

		} else {
			// 아래로

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// 휠 릴리스 이벤트
		if (Main.wheelTime != 0 && System.currentTimeMillis() - Main.wheelTime > 50) {
			DynamicBeat.game.releaseW();
			Main.wheelTime = 0;
		}
	}
}
