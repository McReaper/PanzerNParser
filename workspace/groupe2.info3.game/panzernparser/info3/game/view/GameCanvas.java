/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game.view;
/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.Timer;

import info3.game.sound.AudioPlayer;
import info3.game.sound.AudioPlayerListener;
import info3.game.sound.OggPlayer;

/**
 * A game-oriented canvas. It creates the JFrame in which it will added so that
 * it is aware of when the corresponding window is opened or closed.
 * 
 * We extended the AWT canvas to have an efficient double buffering. You can
 * read https://en.wikipedia.org/wiki/Multiple_buffering as an introduction.
 * 
 * We also manage an audio player for audio streams in the OGG format. The
 * player is automatically stopped when the window is closed.
 * 
 * @see info3.game.CanvasListener.GameCanvasListener to know how to get at the
 *      mouse and keyboard events, the timer events, and the paint requests.
 * 
 *      NOTA BENE: this is not a class that you are supposed to change. you may
 *      only want to change the FPS rate (frames per second). and the tick
 *      period.
 */
public class GameCanvas extends Canvas {

	static final int TICK_PERIOD = 1; // tick every milli-second.
	/*
	 * We want to target 24 frame per seconds (fps), which is the following period
	 * in milliseconds period = (1000.0 / 24.0)
	 */
	static final double FPS = 30.0;
	static final int REPAINT_DELAY = (int) (1000.0 / FPS);

	public GameCanvas(GameCanvasListener l) {

		/*
		 * This is ugly, but this is the only way to turn off the drawing of the Canvas
		 * background before the update/repaint methods are called, causing an annoying
		 * flickering.
		 */
		System.setProperty("sun.awt.noerasebackground", "true");

		m_listener = l;
		setBackground(Color.gray);
		addKeyListener(l);
		addMouseListener(l);
		addMouseMotionListener(l);
		setFocusable(true);
		requestFocusInWindow();

	}

	/**
	 * Gives the current width of the canvas, in pixels. The horizontal X-axis grows
	 * from left to right.
	 */
	public int getWidth() {
		return m_width;
	}

	/**
	 * Gives the current height of the canvas, in pixels. The vertical Y-axis grows
	 * downward.
	 */
	public int getHeight() {
		return m_height;
	}

	/**
	 * Play the given audio stream for the specified duration. A duration of -1
	 * requests to play the audio stream up to the end. The end of play of an audio
	 * stream is always notified to the canvas listener.
	 * 
	 * @param the name must be unique. if the name is already playing, the playing
	 *            is stopped and restarted.
	 */
	public void play(String name, final InputStream is, long duration) {
		_play(name, is, duration);
	}

	public void stop(String name) {
		_stop(name);
	}

	/**
	 * This method sets the unique timer to the given delay. Until the timer
	 * expires, you may not set another timer, but you may cancel the current one
	 * and set a new one.
	 * 
	 * @param delay
	 * @param listener
	 * @throws IllegalStateException if you try to set a second timer.
	 */
	public void setTimer(int delay) {
		_setTimer(delay);
	}

	/**
	 * Cancel the previously-set timer, calling its cancelled You may set a new
	 * timer after calling this method. It is safe to call this method even if there
	 * is no timer currently set.
	 */
	public void cancelTimer() {
		_cancelTimer();
	}

	// public void setTimer(int delay, TimerListener listener) {
	// long now = System.currentTimeMillis();
	// long eta = now + delay;
	// TimerEvent evt = new TimerEvent(eta, listener);
	// m_queue.push(evt);
	// }
	//
	// public void expired() {
	// m_queue.expired();
	// }

	/*
	 * ================================================================ None of the
	 * methods below are to be called by your game. None of the fields are of
	 * interest. ================================================================
	 */

	private static final long serialVersionUID = 1L;

	private Image m_buffer1, m_buffer2;
	private Image m_renderBuffer;
	private Image m_drawBuffer;
	private int m_width, m_height;
	private boolean m_swap;
	private GameCanvasListener m_listener;
	private HashMap<String, OggPlayer> m_players = new HashMap<String, OggPlayer>();

	private void initDoubleBuffering(int width, int height) {

		if (width != m_width || height != m_height) {
			m_width = width;
			m_height = height;
			m_buffer1 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
			m_buffer2 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);

			Graphics gc = m_buffer1.getGraphics();
			gc.setColor(getBackground());
			gc.fillRect(0, 0, m_width, m_height);
			gc = m_buffer2.getGraphics();
			gc.setColor(getBackground());
			gc.fillRect(0, 0, m_width, m_height);
			m_renderBuffer = m_buffer2;
			m_drawBuffer = m_buffer1;
		}
	}

	private void swap() {
		if (m_renderBuffer == m_buffer1) {
			m_renderBuffer = m_buffer2;
			m_drawBuffer = m_buffer1;
		} else {
			m_renderBuffer = m_buffer1;
			m_drawBuffer = m_buffer2;
		}
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (width > 0 && height > 0) {
			initDoubleBuffering(width, height);
			if (m_timer == null)
				createTimer();
		}
	}

	@Override
	public final void paint(Graphics g) {
		if (m_swap) {
			swap();
			m_swap = false;
		}
		g.drawImage(m_renderBuffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public final void update(Graphics g) {
		paint(g);
	}

	/*
	 * Let's create a timer, it is the heart of the simulation, ticking periodically
	 * so that we can simulate the passing of time.
	 */
	Timer m_timer;
	long m_elapsed;
	long m_lastRepaint;
	long m_lastTick;
	long m_lastSec;
	int m_nTicks;
	int m_fps;
	int m_npaints;

	float m_tickPeriod;

	public int getFPS() {
		return m_fps;
	}

	public float getTickPeriod() {
		return m_tickPeriod;
	}

	private void createTimer() {
		int tick = TICK_PERIOD;
		m_lastTick = System.currentTimeMillis();
		m_timer = new Timer(tick, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tick();
			}
		});
		m_timer.start();
	}

	/*
	 * This is the period tick callback. We compute the elapsed time since the last
	 * tick. We inform the model of the current time.
	 */
	private void tick() {
		long now = System.currentTimeMillis();
		long elapsed = (now - m_lastTick);
		m_elapsed += elapsed;
		m_lastTick = now;
		m_nTicks++;

		// compute the number of frame paints
		// during the last second
		if (now - m_lastSec > 1000L) {
			m_fps = m_npaints;
			m_lastSec = now;
			m_npaints = 0;
		}

		if (m_listener != null)
			try {
				m_listener.tick(elapsed);
			} catch (Throwable th) {
				th.printStackTrace(System.err);
			}
		elapsed = now - m_lastRepaint;
		if (elapsed > REPAINT_DELAY) {
			m_tickPeriod = (float) m_elapsed / (float) m_nTicks;
			m_tickPeriod = ((int) (m_tickPeriod * 10.0F)) / 10.0F;
			m_elapsed = 0;
			m_nTicks = 0;
			m_lastRepaint = now;

			// repainting off-screen and asking for a swap
			if (m_drawBuffer != null) {
				Graphics g = m_drawBuffer.getGraphics();
				m_listener.paint(g);
				m_swap = true;
				m_npaints++;
				repaint();
			}
		}
	}

	@SuppressWarnings("serial")
	public class RunnableEvent extends AWTEvent implements Runnable {
		public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;
		Runnable runnable;

		RunnableEvent(Object target, Runnable runnable) {
			super(target, EVENT_ID);
			this.runnable = runnable;
		}

		public void run() {
			runnable.run();
		}
	}

	public void post(Runnable r) {
		eventQueue.postEvent(new RunnableEvent(this, r));
	}

	/*
	 * Over load processEvent which is inherited from class java.awt.Window Our
	 * defined events will be handled here.
	 */
	protected void processEvent(AWTEvent event) {
		if (event instanceof RunnableEvent) {
			RunnableEvent tr = (RunnableEvent) event;
			tr.run();
		} else
			super.processEvent(event);
	}

	WindowFrame m_frame;
	java.awt.EventQueue eventQueue;

	@SuppressWarnings("serial")
	class WindowFrame extends JFrame {
		WindowFrame() {
			enableEvents(RunnableEvent.EVENT_ID);
		}

// Suite au mail de Pr.Gruber, nous commentons ces lignes :
//		@Override
//		public final void update(Graphics g) {
//		}
//
//		@Override
//		public final void paint(Graphics g) {
//		}
	}

	public JFrame createFrame(Dimension d) {

		java.awt.Toolkit tk = Toolkit.getDefaultToolkit();
		eventQueue = tk.getSystemEventQueue();

		m_frame = new WindowFrame();

		m_frame.setSize(d);
		m_frame.addWindowListener(new WindowListener());
		return m_frame;
	}

	private class WindowListener implements java.awt.event.WindowListener {

		WindowListener() {
		}

		@Override
		public void windowOpened(WindowEvent e) {
			m_listener.windowOpened();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			GameCanvasListener l = m_listener;
			// cut any further invocation of the listener
			// (in particular, cut any further ticks),
			// but do the last call to exit()
			m_listener = null;
			Set<String> keys = m_players.keySet();
			Iterator<String> iter = keys.iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				OggPlayer p = m_players.get(key);
				p.stop();
			}
			m_players.clear();
			try {
				if (l != null)
					l.exit();
			} catch (Throwable th) {
			}
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}

	}

	private void _play(String name, final InputStream is, long duration) {
		OggPlayer p = m_players.get(name);
		if (p != null)
			p.stop();
		p = new OggPlayer(new AudioPlayerListener() {
			@Override
			public void endOfPlay(AudioPlayer player, final String name) {
				post(new Runnable() {
					@Override
					public void run() {
						// System.out.println("End of play for " + name);
						m_players.remove(name);
						if (m_listener != null)
							m_listener.endOfPlay(name);
					}
				});
			}
		});
		m_players.put(name, p);
		p.play(name, is, duration);
	}

	private void _stop(String name) {
		OggPlayer p = m_players.get(name);
		if (p != null)
			p.stop();
	}

	Timer m_delayTimer;

	private void _setTimer(int delay) {
		if (m_delayTimer != null)
			throw new IllegalStateException("You may not set another timer, the current timer has not expired.");
		m_delayTimer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean canceled;
				synchronized (this) {
					if (m_delayTimer != null) {
						m_delayTimer.stop();
						m_delayTimer = null;
						canceled = false;
					} else
						canceled = true;
				}
				if (!canceled) {
					if (m_listener != null)
						m_listener.expired();
				}
			}
		});
		m_delayTimer.setRepeats(false);
		m_delayTimer.start();
	}

	private void _cancelTimer() {
		synchronized (this) {
			if (m_delayTimer != null) {
				m_delayTimer.stop();
				m_delayTimer = null;
			}
		}
	}

}
