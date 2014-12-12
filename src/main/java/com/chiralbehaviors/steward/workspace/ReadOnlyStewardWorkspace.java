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

import com.chiralbehaviors.CoRE.agency.Agency;
import com.chiralbehaviors.CoRE.attribute.Attribute;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.ultrastructure.calendar.workspace.CalendarWorkspace;

/**
 * @author hparry
 *
 */
public class ReadOnlyStewardWorkspace implements StewardWorkspace {

    protected Interval goal;
    protected Attribute isComplete;
    protected Attribute dueDate;
    protected Agency steward;
    protected CalendarWorkspace calendarWorkspace;


    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.workspace.StewardWorkspace#getGoal()
     */
    public Interval getGoal() {
        return goal;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.workspace.StewardWorkspace#getIsComplete()
     */
    public Attribute getIsComplete() {
        return isComplete;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.workspace.StewardWorkspace#getSteward()
     */
    public Agency getSteward() {
        return steward;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.workspace.StewardWorkspace#getCalendarWorkspace()
     */
    public CalendarWorkspace getCalendarWorkspace() {
        return calendarWorkspace;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.workspace.StewardWorkspace#getDueDate()
     */
    public Attribute getDueDate() {
        return dueDate;
    }

}
