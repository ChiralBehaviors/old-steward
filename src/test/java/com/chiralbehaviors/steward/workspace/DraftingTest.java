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

import org.junit.Test;

import com.chiralbehaviors.CoRE.kernel.Kernel;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.product.Product;
/**
 * @author hparry
 *
 */
public class DraftingTest {
   
    @SuppressWarnings("null")
    @Test
    public void testImaginedGoal() {
        Kernel kernel = null;
        Model model = null;
        StewardWorkspace ws = new StewardWorkspaceImpl();
        Product eatMoreChikn = new Product("EatMoreChikn", "...and less beef", kernel.getCore());
        model.getProductModel().link(eatMoreChikn, kernel.getIsA(), ws.getGoal(), kernel.getCore());
        
    }

}
