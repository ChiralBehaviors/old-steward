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

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
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
    private StewardWorkspaceBootstrap ws;
    CalendarWorkspace                 calWs;

    @Before
    public void init() {
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
                                     new Date(), ws, model);
        em.getTransaction().commit();

        assertFalse(eatMoreChikn.isComplete());

    }

}
