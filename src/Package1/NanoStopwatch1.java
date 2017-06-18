package Package1;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import sun.audio.*;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NanoStopwatch1 {

	private JFrame frmNano;
	private JTextField txtNano;
	private long lStart;
	private long lStop;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NanoStopwatch1 window = new NanoStopwatch1();
					window.frmNano.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NanoStopwatch1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNano = new JFrame();
		frmNano.setTitle("Test Nano Watch");
		frmNano.setBounds(100, 100, 907, 656);
		frmNano.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNano.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setRolloverIcon(new ImageIcon("/Users/rohith1/Downloads/scary2.jpg"));
		btnStart.setBackground(Color.LIGHT_GRAY);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStart.setForeground(Color.blue);
			}
		});
		JLabel lblZombie = new JLabel("");
		lblZombie.setIcon(new ImageIcon("/Users/rohith1/Documents/workspace/TestProject-1/scary1.jpg"));
		lblZombie.setBounds(227, 22, 674, 594);
		frmNano.getContentPane().add(lblZombie);
		lblZombie.setVisible(false);
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lStart=System.nanoTime();
				lblZombie.setVisible(true);
				txtNano.setText("Counting..");
				txtNano.setEditable(false);
				btnStart.setBackground(Color.BLUE);
				new Thread() {
				      public void run() {
				    	  PlaySound();
				      }   
				    }.start();
			}
		});
		
		
		btnStart.setBounds(47, 193, 75, 29);
		frmNano.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lStop=System.nanoTime();
				txtNano.setText(Long.toString(lStop-lStart));
			}
		});
		btnStop.setBounds(163, 193, 65, 29);
		frmNano.getContentPane().add(btnStop);
		
		txtNano = new JTextField();
		txtNano.setBounds(47, 113, 181, 26);
		frmNano.getContentPane().add(txtNano);
		txtNano.setColumns(10);
		
		JLabel lblNanoStopwatch = new JLabel("Nano Stopwatch");
		lblNanoStopwatch.setFont(new Font("Avenir Next", Font.BOLD, 14));
		lblNanoStopwatch.setBounds(47, 40, 181, 16);
		frmNano.getContentPane().add(lblNanoStopwatch);
		
	}
	
	private static void PlaySound(){
		// open the sound file as a Java input stream
		String gongFile = "sound1.wav";
		//JOptionPane.showMessageDialog(null, gongFile);
		
		InputStream in = null;

				try {
					in = new FileInputStream(gongFile);
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}


		// create an audiostream from the inputstream
	AudioStream audioStream=null;
	try {
		audioStream = new AudioStream(in);
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}

		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//AudioPlayer.player.start(audioStream);
		try {
			audioStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
	
	
