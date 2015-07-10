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
package com.chiralbehaviors.steward.rest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.meta.models.ModelImpl;
import com.chiralbehaviors.CoRE.meta.workspace.Workspace;
import com.chiralbehaviors.CoRE.time.Interval;
import com.chiralbehaviors.steward.app.StewardApplication;
import com.chiralbehaviors.steward.workspace.Journey;
import com.chiralbehaviors.steward.workspace.StewardWorkspace;

/**
 * @author hparry
 *
 */
@Path("/steward")
public class StewardResource {

    private final EntityManagerFactory emf;

    /**
     * @param emf
     */
    public StewardResource(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @GET
    @Path("/steward/journey")
    public List<Journey> getJournies() {
        Model model = new ModelImpl(emf);
        StewardWorkspace ws = (StewardWorkspace) model.getWorkspaceModel().getScoped(Workspace.uuidOf(StewardApplication.STEWARD_WORKSPACE_URI)).getWorkspace().getAccessor(StewardWorkspace.class);
        List<Interval> jps = model.getIntervalModel().getChildren(ws.getJourney().getRuleform(), model.getKernel().getIsA().getInverse());
        List<Journey> journies = jps.stream().map(journey -> (Journey) model.wrap(Journey.class, journey)).collect(Collectors.toList());
        return journies;
    }

    @GET
    @Path("/steward/journey/{id}/")
    public Journey getJourney(@PathParam("id") UUID id) {
        return (Journey) new ModelImpl(emf).lookup(Journey.class, id);
    }

}
