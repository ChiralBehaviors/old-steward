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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.chiralbehaviors.steward.workspace.AbstractStewardTest;
import com.chiralbehaviors.steward.workspace.Journey;
import com.chiralbehaviors.steward.workspace.Step;

/**
 * @author hparry
 *
 */
public class MockTimerTest extends AbstractStewardTest {
    
    @Test
    public void testTimer() throws Exception {
        Timer timer = mock(Timer.class);
        
        Journey journey = (Journey) model.construct(Journey.class, "two step", null);
        timer.setJourney(journey);
        
        when(timer.completed()).thenReturn(true);
        if (timer.completed()) {
            timer.addStep();
            journey.addStep((Step) model.construct(Step.class, "step 1", null));
        }
        
        assertEquals(1, journey.getSteps().size());
        
        
    }

}
