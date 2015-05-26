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
package com.chiralbehaviors.steward.timer.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.steward.timer.CompletionAction;
import com.chiralbehaviors.steward.workspace.Journey;
import com.chiralbehaviors.steward.workspace.Step;

/**
 * @author hparry
 *
 */
public class DefaultCompletionAction implements CompletionAction {

    private final Journey journey;
    private final Model   model;

    public DefaultCompletionAction(Journey journey, Model model) {
        this.journey = journey;
        this.model = model;
    }

    /* (non-Javadoc)
     * @see com.chiralbehaviors.steward.timer.CompletionAction#onComplete()
     */
    @Override
    public boolean onComplete() {
        EntityManager em = model.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            journey.addStep((Step) model.construct(Step.class,
                                                   String.format("[%s] step",
                                                                 journey.getRuleform().getName()),
                                                   null));
            return true;
        } catch (Throwable e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
