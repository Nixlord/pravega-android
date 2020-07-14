package com.phoenixoverlord.pravega.notes

/*
******************************** DAGGER 2 ************************************

2 ways to do dependency injection -
    1. Constructor injection - dependencies of class are passed in the constructor
    2. Field injection - dependencies are instantiated after class is created.

Alternative to Dagger 2 is Service Locator.
* ServiceLocator class creates and store dependencies and then provides those dependencies on demand.

With ServiceLocator pattern - classes have control and ask for objects to be injected
With DI pattern - app has control and proactively injects the required objects.

Some drawbacks of using manual dependency injection -
    1. Code duplication
    2. Dependencies have to be declared in order
    3. It is difficult to reuse objects. If we want to reuse we will have to make it Singleton and Singleton pattern makes testing more difficult.

Dagger code is generated at compile time.

For every class in the graph, dagger uses factory-type class that it uses internally to get instances of that type.

@Inject - lets dagger know how to create instances of this object.

Dagger creates a graph of dependencies which it uses to find out where it should get all the dependencies from whenever needed.
To make Dagger do the above, we need to create an interface and annotate it with @Component.

@Component - tells Dagger to to generate a container with all the dependencies required to satisfy the types it is exposing.
            this helps dagger create a graph of dependencies.

@Singleton - used to limit the lifetime of an object to the lifetime of its component.

Certain Android Frameworks like Activity and Fragment are instantiated by the system, so Dagger cant create them for us.
For activities, any initialization code needs to goto onCreate() => we cannot use @Inject annotation in the constructor of a class.
Thus we have to use field injection.

Instead of creating dependencies an activity requires in onCreate(), we want dagger to populate them for us.
*
Field injection - apply @Inject annotation to the fields that you want to get from the Dagger graph. Should only be used in classes where constructor injection cannot be used.

Injected fields cannot be private.

When using activities, inject dagger in activities onCreate() before super.onCreate().
When using fragments, inject dagger in fragments onAttach() before or after super.onAttach().

Apart form @Inject, there is another way to tell dagger how to provide an instance of a class - information inside dagger modules.
In Dagger modules, we can define dependencies with @Provides annotation.

@Provides methods cannot be private

The @modules attribute in the @Component annotation tells dagger what modules to include when building a graph.

Add scope annotation in classes when using constructor injections (@Inject) and add them in @Provides methods when using Dagger Modules.

Read from subcomponenets.ri
















 */