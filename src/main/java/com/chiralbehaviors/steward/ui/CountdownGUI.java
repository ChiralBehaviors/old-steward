/**
 * Copyright (c) 2015 Chiral Behaviors, LLC, all rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chiralbehaviors.steward.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.chiralbehaviors.steward.timer.CountdownTimer;

/**
 * @author hparry
 *
 */
public class CountdownGUI implements ActionListener {

    private JFrame     frame;
    private JPanel     panel;

    private JLabel     timeLabel = new JLabel();

    private JButton    startBtn  = new JButton("Start");
    private JButton    pauseBtn  = new JButton("Pause");
    private JButton    resumeBtn = new JButton("Resume");
    private JButton    stopBtn   = new JButton("Stop");
    private JButton    resetBtn  = new JButton("Reset");

    private CountdownTimer countdown;
    private Timer refresher = new Timer(1000, this);

    public CountdownGUI(CountdownTimer countdown) {
        setTimerText("         ");
        this.countdown = countdown;
        GUI();
        refresher.start();
    }

    private void GUI() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
        timeLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(timeLabel, BorderLayout.NORTH);

        startBtn.addActionListener(this);
        pauseBtn.addActionListener(this);
        resumeBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        JPanel cmdPanel = new JPanel();
        cmdPanel.setLayout(new GridLayout());

        cmdPanel.add(startBtn);
        cmdPanel.add(pauseBtn);
        cmdPanel.add(resumeBtn);
        cmdPanel.add(stopBtn);
        cmdPanel.add(resetBtn);

        panel.add(cmdPanel, BorderLayout.SOUTH);

        JPanel clrPanel = new JPanel();
        clrPanel.setLayout(new GridLayout(0, 1));

        panel.add(clrPanel, BorderLayout.EAST);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

    }

    public void setTimerText(String sTime) {
        timeLabel.setText(sTime);
    }

    
    private String format(long timeInSeconds) {

        long hours = timeInSeconds / 3600;
        long minutes = (timeInSeconds - hours * 3600) / 60;
        long seconds = timeInSeconds - minutes * 60;

        return String.format("%02d", hours) + " : "
               + String.format("%02d", minutes) + " : "
               + String.format("%02d", seconds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            setTimerText(format(countdown.getTimeLeft()));
            return;
        }
        JButton btn = (JButton) e.getSource();

        if (btn.equals(startBtn)) {
            countdown.start();
        } else if (btn.equals(pauseBtn)) {
            countdown.pause();
        } else if (btn.equals(resumeBtn)) {
            countdown.resume();
        } else if (btn.equals(stopBtn)) {
            countdown.stop();
        } else if (btn.equals(resetBtn)) {
            countdown.reset();
        }
    }

    public static void main(String[] args) {

//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CountdownGUI(new CountdownTimer(10));
//            }
//        });

    }

}
