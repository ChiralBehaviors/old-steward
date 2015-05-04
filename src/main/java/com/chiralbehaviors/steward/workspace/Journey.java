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
package com.chiralbehaviors.steward.workspace;

import java.util.List;

import com.chiralbehaviors.CoRE.Ruleform;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.phantasm.ScopedPhantasm;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.annotations.Edge;
import com.chiralbehaviors.annotations.Facet;
import com.chiralbehaviors.annotations.Key;
import com.chiralbehaviors.annotations.State;

/**
 * @author hparry
 *
 */
@State(facets = { @Facet(classification = @Key(namespace = "kernel", name = "IsA"), classifier = @Key(name = "Journey")) }, workspace = "uri:http://ultrastructure.me/ontology/com.chiralbehaviors/demo/steward-workspace/v1")
public interface Journey extends ScopedPhantasm<Interval>{

    @Edge(@Key(namespace = "kernel", name = "HasMember"))
    List<Step> getSteps();

    @Edge(@Key(namespace = "kernel", name = "HasMember"))
    void addStep(Step step);
    
    default Journey scopedAccess() {
        Ruleform lookup = getScope().lookup("kernel", "IsA");
        System.out.println("lookup" + lookup);
        Model model = getModel();
        return (Journey) model.wrap(Journey.class, getRuleform());
    }
}
