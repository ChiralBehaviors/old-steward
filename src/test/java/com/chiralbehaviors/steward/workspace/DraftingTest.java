/**
 * Copyright (c) 2014 Chiral Behaviors, LLC, all rights reserved.
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
package com.chiralbehaviors.steward.workspace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chiralbehaviors.CoRE.meta.models.AbstractModelTest;
import com.chiralbehaviors.steward.object.Step;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspaceBootstrap;

/**
 * @author hparry
 *
 */
public class DraftingTest extends AbstractModelTest {
    private static StewardWorkspaceBootstrap  ws;
    private static CalendarWorkspaceBootstrap calWs;

    @BeforeClass
    public static void init() {
        em.getTransaction().begin();
        calWs = new CalendarWorkspaceBootstrap(model);
        calWs.createWorkspace();
        ws = new StewardWorkspaceBootstrap(calWs, em, kernel);
        ws.createWorkspace();
        em.getTransaction().commit();

    }

    @Test
    public void testImaginedGoal() {
        em.getTransaction().begin();
        Step eatMoreChikn = new Step("EatMoreChikn", "...and less beef",
                                     Instant.now(), ws, model);
        em.getTransaction().commit();

        assertFalse(eatMoreChikn.isComplete());

        em.getTransaction().begin();
        eatMoreChikn.setIsComplete(true);
        em.getTransaction().commit();

        assertTrue(eatMoreChikn.isComplete());

    }

    @Test
    public void testStartDate() {
        em.getTransaction().begin();
        Instant now = Instant.now();
        Step surviveThisMeeting = new Step("Survive this meeting",
                                           "christ it will never end", now, ws,
                                           model);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Instant later = Instant.ofEpochMilli(now.getEpochSecond() + 20);
        surviveThisMeeting.setDueDate(later);
        em.getTransaction().commit();

        assertEquals(later.toEpochMilli(),
                     surviveThisMeeting.getInterval().getStart().longValue()
                             + surviveThisMeeting.getInterval().getDuration().longValue());

    }

}
