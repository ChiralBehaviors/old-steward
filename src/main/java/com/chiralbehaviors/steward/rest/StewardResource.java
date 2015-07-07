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

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.meta.models.ModelImpl;
import com.chiralbehaviors.CoRE.product.Product;
import com.chiralbehaviors.CoRE.workspace.WorkspaceAuthorization;
import com.chiralbehaviors.steward.workspace.Journey;

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

    private static final String STEWARD_WORKSPACE_NAME = "Steward";
    
    @SuppressWarnings("unused")
    @GET
    @Path("/steward/journey")
    public List<Journey> getJournies() {
        Model model = new ModelImpl(emf);
        Product stewardWorkspace = model.find(STEWARD_WORKSPACE_NAME, Product.class);
        List<WorkspaceAuthorization> ws = model.getWorkspaceModel().getWorkspace(stewardWorkspace);
        List<Product> jps = model.getProductModel().getChildren(null, null);
        return null;
    }
    
    @GET
    @Path("/steward/journey/{id}/")
    public Journey getJourney(@PathParam("id") String id){
        return null;
    }

}
