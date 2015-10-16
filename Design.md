# Backend Design #

ConnectBot is designed to keep multiple SSH sessions open in the background, so here's the design that currently allows this to happen:

```

TerminalManager extends android.app.Service
     ||   (1:*)
TerminalBridge
     ||   (1:?)
TerminalView extends android.view.View

```

We have a Service called `TerminalManager` that keeps a list of all connected SSH sessions as `TerminalBridge` objects.  Each `TerminalBridge` maintains the SSH session by handling incoming and outgoing data.

When we want to show a `TerminalBridge` in a user interface, we create a `TerminalView` that provides a Bitmap down to the `TerminalBridge` for rendering.

`TerminalBridge` will render any updates to the Bitmap from the parent `TerminalView` if someone has a user interface attached, otherwise it will just update its internal buffers.

## Showing a user interface ##

With this approach, our `ConsoleActivity` connects to the `TerminalManager` to request any active Bridges.  For each Bridge we create and link it with a View.  When we close the Activity, we tell each Bridge to dispose the internal Bitmap that it's been using for rendering.  (The Bridge still lives on in the background Service to keep its buffers updated, but it doesn't need to render anything.)