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
package com.chiralbehaviors.steward.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author hparry
 *
 */
public class CountdownTimer implements ActionListener {

    private static final int ONE_SECOND     = 1000;
    private long             secondsElapsed = 0;
    private boolean          isTimerActive  = false;
    private Timer            tmr            = new Timer(ONE_SECOND, this);
    private long             lengthInSeconds;
    private CompletionAction action;
    private boolean completed = false;

    public CountdownTimer(int lengthInSeconds, CompletionAction action) {
        secondsElapsed = 0;
        this.lengthInSeconds = lengthInSeconds;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (secondsElapsed >= lengthInSeconds && !completed) {
            isTimerActive = false;
            action.onComplete();
            completed = true;
        }
        if (isTimerActive) {
            secondsElapsed++;
        }
    }
    
    public boolean isCompleted() {
        return completed;
    }

    public void start() {
        secondsElapsed = 0;
        isTimerActive = true;
        tmr.start();
    }

    public void resume() {
        isTimerActive = true;
        tmr.restart();
    }

    public void stop() {
        tmr.stop();
    }

    public void pause() {
        isTimerActive = false;
    }

    public void reset() {
        secondsElapsed = 0;
        isTimerActive = false;

    }

    public long getTimeLeft() {
        return lengthInSeconds - secondsElapsed;
    }

}
