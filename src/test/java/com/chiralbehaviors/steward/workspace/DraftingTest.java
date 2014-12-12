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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chiralbehaviors.CoRE.attribute.unit.Unit;
import com.chiralbehaviors.CoRE.meta.models.AbstractModelTest;
import com.chiralbehaviors.steward.object.Goal;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class DraftingTest extends AbstractModelTest {
    private static StewardWorkspaceBootstrap ws;
    private static CalendarWorkspace                 calWs;

    @BeforeClass
    public static void init() {
        calWs = mock(CalendarWorkspace.class);
        Unit millisSinceEpoch = new Unit("MillisSinceEpoch", null,
                                         kernel.getCore());
        em.getTransaction().begin();
        em.persist(millisSinceEpoch);
        em.getTransaction().commit();
        when(calWs.getMillisSinceEpoch()).thenReturn(millisSinceEpoch);
        ws = new StewardWorkspaceBootstrap(calWs, em, kernel);
        em.getTransaction().begin();
        ws.manifestWorkspace();
        em.getTransaction().commit();
    }

    @Test
    public void testImaginedGoal() {
        em.getTransaction().begin();
        Goal eatMoreChikn = new Goal("EatMoreChikn", "...and less beef",
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
        Goal surviveThisMeeting = new Goal("Survive this meeting",
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
