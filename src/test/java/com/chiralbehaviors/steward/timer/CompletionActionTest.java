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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.chiralbehaviors.steward.timer.impl.DefaultCompletionAction;
import com.chiralbehaviors.steward.workspace.AbstractStewardTest;
import com.chiralbehaviors.steward.workspace.Journey;
import com.hellblazer.utils.Utils;

/**
 * @author hparry
 *
 */
public class CompletionActionTest extends AbstractStewardTest {

    @Test
    public void testStepInsert() throws Exception {
        em.getTransaction().begin();
        Journey journey = (Journey) model.construct(Journey.class,
                                                    "my journey", null);
        CountdownTimer timer = new CountdownTimer(
                                                  1,
                                                  new DefaultCompletionAction(
                                                                              journey,
                                                                              model));
        timer.start();
        assertTrue(Utils.waitForCondition(3000, () -> timer.isCompleted()));
        em.getTransaction().commit();
        assertEquals(1, journey.getSteps().size());
    }

}
