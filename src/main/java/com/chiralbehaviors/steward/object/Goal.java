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
package com.chiralbehaviors.steward.object;

import java.math.BigDecimal;
import java.time.Instant;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.network.Facet;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.CoRE.time.IntervalAttribute;
import com.chiralbehaviors.steward.workspace.StewardWorkspace;

/**
 * @author hparry
 *
 */
public class Goal {

    private Interval                           interval;
    @SuppressWarnings("unused")
    private Model                              model;
    private StewardWorkspace                   workspace;
    private Aspect<Interval>                   isAGoal;
    private Facet<Interval, IntervalAttribute> facet;

    public Goal(Interval interval, StewardWorkspace workspace, Model model) {
        this.interval = interval;
        this.model = model;
        this.workspace = workspace;
        isAGoal = new Aspect<Interval>(model.getKernel().getIsA(),
                                       workspace.getGoal());
        facet = model.getIntervalModel().create("Goal", "The goal interval",
                                                isAGoal);
    }

    public Goal(String name, String description, Instant dueDate,
                StewardWorkspace workspace, Model model) {
        this(
             new Interval(
                          name,
                          BigDecimal.valueOf(Instant.now().toEpochMilli()),
                          workspace.getCalendarWorkspace().getMillisSinceEpoch(),
                          BigDecimal.valueOf(0),
                          model.getKernel().getNotApplicableUnit(),
                          description, workspace.getSteward()), workspace,
             model);
        setDueDate(dueDate);
    }

    public Interval getInterval() {
        return interval;
    }

    public boolean isComplete() {
        return facet.getValue(workspace.getIsComplete()).getBooleanValue();
    }

    public void setIsComplete(boolean complete) {
        facet.getValue(workspace.getIsComplete()).setBooleanValue(complete);
    }

    public Instant getStartDate() {
        Instant date = Instant.ofEpochMilli(interval.getStart().longValue());
        return date;
    }

    public Instant getDueDate() {
        return Instant.ofEpochMilli(facet.getValue(workspace.getDueDate()).getNumericValue().longValue());
    }

    public void setDueDate(Instant date) {
        facet.getValue(workspace.getDueDate()).setNumericValue(BigDecimal.valueOf(date.toEpochMilli()));
        long duration = date.toEpochMilli() - interval.getStart().longValue();
        interval.setDuration(BigDecimal.valueOf(duration));
        interval.setDurationUnit(workspace.getCalendarWorkspace().getMilliseconds());
    }

}
