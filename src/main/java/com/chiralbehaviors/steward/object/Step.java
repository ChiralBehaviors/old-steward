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
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.steward.workspace.StewardWorkspace;

/**
 * @author hparry
 *
 */
public class Step extends StewardObject {

    private Journey journey;

    public Step(String name, String description, Instant dueDate,
                StewardWorkspace workspace, Model model) {
        this(name, description, dueDate, null, workspace, model);
    }

    @SuppressWarnings("unchecked")
    public Step(String name, String description, Instant dueDate,
                Journey journey, StewardWorkspace workspace, Model model) {
        this.model = model;
        this.workspace = workspace;
        if (journey != null) {
            interval = model.getIntervalModel().create(name,
                                                       description,
                                                       new Aspect<Interval>(
                                                                            model.getKernel().getIsA(),
                                                                            workspace.getStep()),
                                                       new Aspect<Interval>(
                                                                            workspace.getInJourney(),
                                                                            journey.getInterval()));
            this.journey = journey;
        } else {
            interval = model.getIntervalModel().create(name,
                                                       description,
                                                       new Aspect<Interval>(
                                                                            model.getKernel().getIsA(),
                                                                            workspace.getStep())).asRuleform();
        }
        interval.setStart(BigDecimal.valueOf(Instant.now().toEpochMilli()));
        interval.setStartUnit(workspace.getCalendarWorkspace().getMillisSinceEpoch());
        setDueDate(dueDate);
    }

    public boolean isComplete() {
        return model.getIntervalModel().getFacet(interval,
                                                 new Aspect<Interval>(
                                                                      model.getKernel().getIsA(),
                                                                      workspace.getStep())).getValue(workspace.getIsComplete()).getBooleanValue();

    }

    public void setIsComplete(boolean complete) {
        model.getIntervalModel().getFacet(interval,
                                          new Aspect<Interval>(
                                                               model.getKernel().getIsA(),
                                                               workspace.getStep())).getValue(workspace.getIsComplete()).setBooleanValue(complete);
    }

    public Instant getDueDate() {
        return Instant.ofEpochMilli(model.getIntervalModel().getFacet(interval,
                                                                      new Aspect<Interval>(
                                                                                           model.getKernel().getIsA(),
                                                                                           workspace.getStep())).getValue(workspace.getDueDate()).getNumericValue().longValue());
    }

    public void setDueDate(Instant date) {
        model.getIntervalModel().getFacet(interval,
                                          new Aspect<Interval>(
                                                               model.getKernel().getIsA(),
                                                               workspace.getStep())).getValue(workspace.getDueDate()).setNumericValue(BigDecimal.valueOf(date.toEpochMilli()));
        long duration = date.toEpochMilli() - interval.getStart().longValue();
        interval.setDuration(BigDecimal.valueOf(duration));
        interval.setDurationUnit(workspace.getCalendarWorkspace().getMilliseconds());
    }

    public Journey getJourney() {
        return journey;
    }
    
    public void delete() {
        journey.removeStep(this);
        model.getEntityManager().remove(interval);
    }

}
