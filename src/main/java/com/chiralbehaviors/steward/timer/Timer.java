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

import com.chiralbehaviors.steward.workspace.Journey;

/**
 * @author hparry
 *
 */
public interface Timer {

    void start();

    void stop();

    void reset();

    /**
     * @param journey
     */
    void setJourney(Journey journey);
    
    Journey getJourney();
    
    boolean completed();
    
    void addStep();

}
