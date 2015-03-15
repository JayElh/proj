package com.jayelh.jl.mydecisionmanager;

import com.jayelh.jl.DecisionManager;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;


import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

/**
 * An InjectibleProvider for MyDecisionManager.
 */
@Provider
public class DecisionManagerProvider implements InjectableProvider<Context, Type>, Injectable<DecisionManager> {

    /**
     * {@inheritDoc}
     */
    @Override
    public DecisionManager getValue() {
        return MyDecisionManager.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentScope getScope() {
        return ComponentScope.Singleton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Injectable<DecisionManager> getInjectable(final ComponentContext ic, final Context context, final Type type) {
        if (type.equals(DecisionManager.class)) {
            return this;
        } else {
            return null;
        }
    }
}