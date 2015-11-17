package _2Draw.Panels;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;


public class ApplicationTimer {
    Toolkit toolkit;
    Timer timer;
    private CanvasPanel cv;

    public ApplicationTimer(CanvasPanel cv) {
    this.cv = cv;
	toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, 2*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
        	cv.refreshServerList();
			cv.repaint();
        }
    }
}
