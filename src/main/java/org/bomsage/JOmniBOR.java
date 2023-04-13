package org.bomsage;

import java.lang.instrument.Instrumentation;

/**
 * The Java agent entry point
 */
public class JOmniBOR {
    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new OmniBORLogger());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new OmniBORLogger());
    }
}
