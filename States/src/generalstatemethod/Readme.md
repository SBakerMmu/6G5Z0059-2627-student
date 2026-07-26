# General State Pattern Package

This package provides a minimal Java example of the **State design pattern**.

It models a `Context` object whose behavior is delegated to a current `State`.
The two concrete states (`ConcreteStateA` and `ConcreteStateB`) alternate by
setting each other on the context when `handle(Context context)` is called.

1. `ConcreteContext` is created with an initial state (`ConcreteStateA`).
2. Calling `request()` invokes `state.handle(this)`.
3. The current state decides and applies the next state via `setState(State newState)` method of the `Context`
   interface.
4. Repeated requests cause the context to move between A and B.

In this implementation a reference to the context is passed to each concrete state when `handle(Context context)` is
called.
