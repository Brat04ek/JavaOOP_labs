package com.di;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import static org.junit.Assert.assertSame;
import org.junit.Test;

import com.di.BasicEnvironment;
import com.di.BindException;
import com.di.CircularInjectException;
import com.di.UnregisteredComponentException;

public class MyTest {

    private final Environment environment = new BasicEnvironment();

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRebindWithClass() {
        environment.configure((binder) -> {
            binder.bind(Pets.class, Dog.class);
            binder.bind(Pets.class, Cat.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRebindWithInstance() {
        environment.configure((binder) -> {
            binder.bind(Pets.class, Dog.class);
            binder.bind(Pets.class, new Cat());
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterAbstractClass() {
        environment.configure((binder) -> {
            binder.bind(Pets.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterInterface() {
        environment.configure((binder) -> {
            binder.bind(Animal.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterNull() {
        environment.configure((binder) -> {
            binder.bind(null);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterClassWithMoreThanOneInjectionConstructor() {
        environment.configure((binder) -> {
            binder.bind(TwoConstructorInjection.class);
        });
    }

    @Test(expected = UnregisteredComponentException.class)
    public void shouldThrowUnregisteredComponentExceptionWhenTryingToGetUnregisteredComponent() {
        Container container = environment.configure((binder) -> {});
        container.getComponent(Dog.class);
    }

    @Test(expected = UnregisteredComponentException.class)
    public void shouldThrowUnregisteredComponentExceptionWhenTryingToGetComponentWhichDependenciesNotRegistered() {
        Container container = environment.configure((binder) -> {
            binder.bind(DoggyShelter.class);
        });
        container.getComponent(DoggyShelter.class);
    }

    @Test(expected = CircularInjectException.class)
    public void shouldThrowCircularInjectExceptionWhenTryingToRegisterComponentWithCircularInjectDependency() {
        environment.configure((binder) -> {
            binder.bind(A.class);
            binder.bind(B.class);
            binder.bind(C.class);
        });
    }

    @Test
    public void shouldResolveSingletonWithInjection() {
        Container container = environment.configure((binder) -> {
            binder.bind(Dog.class);
            binder.bind(DoggyShelter.class);
        });
        /* @Singleton AppleSharer */
        DoggyShelter puppy1 = container.getComponent(DoggyShelter.class);
        DoggyShelter puppy2 = container.getComponent(DoggyShelter.class);
        assertSame(puppy1, puppy2);
        assertSame(puppy1.getPuppy(), puppy2.getPuppy());
    }

    @Test
    public void shouldResolveNestedInjectDependency() {
        Container container = environment.configure((binder) -> {
            binder.bind(ShelterFactory.class);
            binder.bind(Dog.class);
            binder.bind(DoggyShelter.class);
        });
        ShelterFactory c = container.getComponent(ShelterFactory.class);
        assertSame(c.getShelter(), container.getComponent(DoggyShelter.class));
    }

}
