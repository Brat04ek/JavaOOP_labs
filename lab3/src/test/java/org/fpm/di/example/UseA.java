package org.fpm.di.example;

import javax.inject.Inject;

public class UseA {
    private final A dependency;
    private final B b;
    private final MySingleton mySingleton;
    @Inject
    public UseA(A a, B b, MySingleton mySingleton) {
        this.dependency = a;
        this.b = b;
        this.mySingleton = mySingleton;
    }

    public A getDependency() {
        return dependency;
    }
}
