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

import javax.persistence.EntityManager;

import com.chiralbehaviors.CoRE.agency.Agency;
import com.chiralbehaviors.CoRE.attribute.Attribute;
import com.chiralbehaviors.CoRE.attribute.ValueType;
import com.chiralbehaviors.CoRE.kernel.Kernel;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.meta.models.ModelImpl;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class StewardWorkspaceBootstrap extends ReadOnlyStewardWorkspace {

    private Model         model;
    private Kernel        kernel;
    private EntityManager em;

    public StewardWorkspaceBootstrap(CalendarWorkspace calendarWorkspace,
                                     EntityManager em, Kernel kernel) {
        this.calendarWorkspace = calendarWorkspace;
        this.em = em;
        this.model = new ModelImpl(em);
        this.kernel = kernel;
    }

    public void manifestWorkspace() {
        //agencies first. I create the Steward agency
        //and it creates all the rest of the objects
        loadAgencies();
        loadIntervals();
        loadAttributes();
        authorizeAttributes();
    }

    /**
     * 
     */
    private void authorizeAttributes() {
        model.getIntervalModel().authorize(new Aspect<Interval>(
                                                                kernel.getIsA(),
                                                                goal),
                                           isComplete);
        model.getIntervalModel().authorize(new Aspect<Interval>(
                                                                kernel.getIsA(),
                                                                goal), dueDate);

    }

    /**
     * 
     */
    private void loadAttributes() {
        isComplete = new Attribute("Is Complete", "The isComplete attribute",
                                   steward);
        isComplete.setValueType(ValueType.BOOLEAN);
        em.persist(isComplete);

        dueDate = new Attribute("Due Date", "The due date attribute", steward);
        dueDate.setValueType(ValueType.NUMERIC);
        em.persist(dueDate);

    }

    /**
     * 
     */
    private void loadAgencies() {
        steward = new Agency("Steward", "The Steward agency", kernel.getCore());
        em.persist(steward);

    }

    /**
     * 
     */
    private void loadIntervals() {
        goal = new Interval("Goal", "The Goal Supertype", steward);
        goal.setStartUnit(kernel.getNotApplicableUnit());
        goal.setDurationUnit(kernel.getNotApplicableUnit());
        em.persist(goal);

    }

}
