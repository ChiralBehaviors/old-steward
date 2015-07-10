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
package com.chiralbehaviors.steward.app;

import static org.junit.Assert.assertNotNull;
import io.dropwizard.Application;
import io.dropwizard.servlets.assets.AssetServlet;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletRegistration.Dynamic;

import com.chiralbehaviors.CoRE.WellKnownObject;
import com.chiralbehaviors.CoRE.agency.Agency;
import com.chiralbehaviors.CoRE.meta.Model;
import com.chiralbehaviors.CoRE.meta.models.ModelImpl;
import com.google.common.base.Charsets;

/**
 * @author hparry
 *
 */
public class StewardApplication extends Application<StewardConfiguration> {
    
    public static final String STEWARD_WORKSPACE_URI ="uri:http://ultrastructure.me/ontology/com.chiralbehaviors/demo/steward-workspace/v1";
    private EntityManagerFactory emf;

    public static void main(String... args) throws Exception {
        new StewardApplication().run(args);
    }

    /* (non-Javadoc)
     * @see io.dropwizard.Application#run(io.dropwizard.Configuration, io.dropwizard.setup.Environment)
     */
    @Override
    public void run(StewardConfiguration configuration, Environment environment)
                                                                                throws Exception {
        Dynamic dynamic = environment.admin().addServlet("assets",
                                                         new AssetServlet(
                                                                          "/assets",
                                                                          "/assets/",
                                                                          "html/index.html",
                                                                          Charsets.UTF_8));
        dynamic.addMapping("/assets/*");
        dynamic.setInitParameter("useFileMappedBuffer", "false");
        emf = getEntityManagerFactory();
        Model model = new ModelImpl(emf);
        EntityManager em = model.getEntityManager();
        if (model.find("Core User", Agency.class) == null) {
            com.chiralbehaviors.CoRE.kernel.Bootstrap bootstrap = new com.chiralbehaviors.CoRE.kernel.Bootstrap(
                                                                                                                em);
            em.getTransaction().begin();
            bootstrap.bootstrap();
            em.getTransaction().commit();
        }

    }

    @Override
    public void initialize(Bootstrap<StewardConfiguration> bootstrap) {
    }

    /**
     * @return
     * @throws IOException
     */
    private EntityManagerFactory getEntityManagerFactory() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/jpa.properties");
        assertNotNull("jpa properties missing", is);
        Properties properties = new Properties();
        properties.load(is);
        return Persistence.createEntityManagerFactory(WellKnownObject.CORE,
                                                      properties);
    }

}
