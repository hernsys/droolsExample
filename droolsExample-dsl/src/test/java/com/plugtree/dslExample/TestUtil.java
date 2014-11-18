package com.plugtree.dslExample;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;


public class TestUtil {
    private TestUtil() {
        // Non-instantiable from outside
    }

    public static Map<Object, FactHandle> insertAll(KieRuntime runtime, Object... objects) {
        Map<Object, FactHandle> factHandles = new HashMap<Object, FactHandle>(objects.length);
        for (Object o : objects) {
            FactHandle factHandle = runtime.insert(o);
            factHandles.put(o, factHandle);
        }
        return factHandles;
    }

    public static StatelessKieSession createStatelessKieSession(String... drlResourcesPaths) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kcontainer = createKieContainer(ks, drlResourcesPaths);
        
        // Configure and create the KieBase
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        KieBase kbase = kcontainer.newKieBase(kbconf);

        // Configure and create the KieSession
        KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
        return kbase.newStatelessKieSession(ksconf);
    }

    public static KieSession createKieSession(String... drlResourcesPaths) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kcontainer = createKieContainer(ks, drlResourcesPaths);

        // Configure and create the KieBase
        KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
        KieBase kbase = kcontainer.newKieBase(kbconf);

        // Configure and create the KieSession
        KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
        return kbase.newKieSession(ksconf, null);
    }

    private static KieContainer createKieContainer(KieServices ks, String... drlResourcesPaths) {
        // Create the in-memory File System and add the resources files to it
        KieFileSystem kfs = ks.newKieFileSystem();
        for (String path : drlResourcesPaths) {
            kfs.write(ResourceFactory.newClassPathResource(path));
        }
        // Create the builder for the resources of the File System
        KieBuilder kbuilder = ks.newKieBuilder(kfs);
        // Build the Kie Bases
        kbuilder.buildAll();
        // Check for errors
        if (kbuilder.getResults().hasMessages(Level.ERROR)) {
            throw new IllegalArgumentException(kbuilder.getResults().toString());
        }
        // Get the Release ID (mvn style: groupId, artifactId,version)
        ReleaseId relId = kbuilder.getKieModule().getReleaseId();
        // Create the Container, wrapping the KieModule with the given ReleaseId
        return ks.newKieContainer(relId);
    }

    public static void dispose(KieSession session) {
        if (session != null) {
            session.dispose();
        }
    }

}