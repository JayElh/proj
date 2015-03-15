package com.jayelh.jl.mydecisionmanager;
//TODO: I need to put this in another package to be able to config wich manager to use

import com.jayelh.jl.DecisionManager;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

@Provider
public class DecisionManagerProvider implements InjectableProvider<Context, Type>, Injectable<DecisionManager> {

    @Override
    public DecisionManager getValue() {return MyDecisionManager.getInstance(); }

    @Override
    public ComponentScope getScope() { return ComponentScope.Singleton; }

    @Override
    public Injectable<DecisionManager> getInjectable(final ComponentContext ic, final Context context, final Type type) {
        if (type.equals(DecisionManager.class)) {
            return this;
        } else {
            return null;
        }
    }
}