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

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.network.Aspect;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.steward.workspace.StewardWorkspace;

/**
 * @author hparry
 *
 */
public class Goal {

    private Interval interval;
    private Model model;
    private StewardWorkspace workspace;

    public Goal(Interval interval, StewardWorkspace workspace, Model model) {
        this.interval = interval;
        this.model = model;
        this.workspace = workspace;
        model.getIntervalModel().authorize(new Aspect<Interval>(
                                                                model.getKernel().getIsA(),
                                                                workspace.getGoal()),
                                           workspace.getIsComplete());
    }

    public Interval getInterval() {
        return interval;
    }
    
    public boolean getIsComplete() {
        ???????????
    }

}
