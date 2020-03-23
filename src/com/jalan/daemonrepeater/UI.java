package com.jalan.daemonrepeater;

import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UI {

	private JLabel lblTitle;
	private JButton btnService;
	private JLabel lblServiceStatus;
	private boolean serviceCtrlFlag = false;
	private Controller controller;
	
	public static final String SERVICE_STATUS_INIT = "Estado del servicio: Sin Inicializar";
	public static final String SERVICE_STATUS_STARTED = "Estado del servicio: Corriendo";
	public static final String SERVICE_STATUS_ERR_PORT = "Estado del servicio: Error. Puerto en uso";
	public static final String SERVICE_STATUS_STOPPED = "Estado del servicio: Detenido";
	
	public static final String BTN_STOP = "Detener servicio";
	public static final String BTN_START = "Iniciar servicio";

	private JPanel panel;
	
	public UI() {}
	
	public UI(Controller controller) {
		this.controller = controller;
	}
	
	public void init() {
	
		this.lblTitle = new JLabel("DaemonRepeater");
		this.lblTitle.setBorder(new EmptyBorder(5, 0, 25, 0));
		this.lblTitle.setFont(new Font("Serif", Font.PLAIN, 16));
		this.lblServiceStatus = new JLabel(UI.SERVICE_STATUS_INIT);
		
		this.btnService = new JButton(UI.BTN_START);
		this.btnService.addActionListener((e) -> onBtnServicePressed(e));
		
		this.panel = new JPanel();
		
		lblTitle.setAlignmentX(Label.CENTER_ALIGNMENT);
		lblServiceStatus.setAlignmentX(Label.CENTER_ALIGNMENT);
		btnService.setAlignmentX(Button.CENTER_ALIGNMENT);
		
		BoxLayout boxLayout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
		this.panel.setLayout(boxLayout);
		
		panel.add(lblTitle);
		panel.add(lblServiceStatus);
		panel.add(btnService);
		
		panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
	}
	
	public Component getMainPanel() {
		return this.panel;
	}
	
	public JLabel getLblServiceStatus() {
		return this.lblServiceStatus;
	}
	
	public void onBtnServicePressed(ActionEvent evt) {
		this.btnService.setEnabled(false);
		
		if(!serviceCtrlFlag) {
			this.controller.onStartService();
			this.serviceCtrlFlag = true;
			this.btnService.setText(BTN_STOP);
		}else {
			this.controller.onStopService();
			this.serviceCtrlFlag = false;
			this.btnService.setText(BTN_START);
		}
		
		this.btnService.setEnabled(true);
			
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public static void main(String []args) {
		UI ui = new UI();
		ui.testUI();
	}
	
	public void testUI() {
		
		init();
		
		JFrame frame = new JFrame("I am frame!");
		frame.add(this.panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
