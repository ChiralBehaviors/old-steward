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
package com.chiralbehaviors.steward.object;

import java.util.ArrayList;
import java.util.List;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.steward.workspace.StewardWorkspace;

/**
 * @author hparry
 *
 */
public class Journey extends StewardObject {
    
    private List<Step> steps;

    public Journey(String name, String description, Model model,
                   StewardWorkspace workspace) {
        this.interval = model.getIntervalModel().create(name,
                                                     description,
                                                     new Aspect<Interval>(
                                                                          model.getKernel().getIsA(),
                                                                          workspace.getJourney())).asRuleform();
        this.model = model;
        this.workspace = workspace;
        this.steps = new ArrayList<Step>();
    }
    
    public List<Step> getSteps() {
        return steps;
    }

    public void addStep(Step step) {
        steps.add(step);
    }
    
    public void removeStep(Step step) {
        steps.remove(step);
    }
}
