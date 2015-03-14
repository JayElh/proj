package com.jayelh.jl.mypurchasemanager;
//TODO: I need to put this in another package to be able to config wich manager to use

import com.jayelh.jl.PurchaseManager;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;

@Provider
public class PurchaseManagerProvider implements InjectableProvider<Context, Type>, Injectable<PurchaseManager> {


    private PurchaseManager myPurchaseManager;

    @PostConstruct
    private void init() {
        myPurchaseManager = new MyPurchaseManager();
    }


    @Override
    public PurchaseManager getValue() {return myPurchaseManager; }

    @Override
    public ComponentScope getScope() { return ComponentScope.Singleton; }

    @Override
    public Injectable<PurchaseManager> getInjectable(final ComponentContext ic, final Context context, final Type type) {
        if (type.equals(PurchaseManager.class)) {
            return this;
        } else {
            return null;
        }
    }
}