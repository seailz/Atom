# Atom
Simplifies [Bungeecord's plugin messaging API](https://www.spigotmc.org/wiki/bukkit-bungee-plugin-messaging-channel/).

## Get Started
To get started receiving messages, you'll need to start by creating a new instance of the `Atom` class.
You should only ever do this once.
<br>Here's an example:

```java
import com.seailz.atom.Atom;
...

@Override
public void onEnable() {
    Atom atom = new Atom(this);
}
```

### Receiving Messages
You can now start registering listeners. Like Bukkit's listener system, you'll first want to start by creating a new class that extends <code>AtomListener</code>.
<p>

```java
import com.seailz.atom.events.AtomListener;

public class ExampleClass extends AtomListener {

}
```

<br>
From there, you can override the <code>onMessageReceived</code> method.
<p></p>
  
```java
@Override
public void onMessageReceived(ChannelMessageEvent event) {

}
```
You'll also need to add the <code>AtomMethod</code> annotation to the method. That annotation also has a utility that allows you to require a specific sub-channel name for the method to run. For example:
<p></p>
  
```java
@Override
@AtomMethod(channel = "testSubChannel")
public void onMessageReceived(ChannelMessageEvent event) {

}
```

  This <code>onMessageReceived</code> method will only run when the sub-channel is "testSubChannel".
  One other thing you'll need to do is register your listeners. This can be done using the Atom class instance that you created earlier. Here's an example:
  <p></p>
  
```java
@Override
public void onEnable() {
    Atom atom = new Atom(this);
    atom.registerListener(new ExampleClass());
}
```
    
### Sending Messages
You can send messages using the Atom instance that you created earlier. The Atom class has a couple `sendMessage` methods that you can use. Here's a couple examples:
<p></p>

```java
// Send a message that isn't linked to a player.
atom.sendMessage("exampleSubChannel", "Hello! This is an example message");
```

<p></p>

```java
// Send a message that is linked to a player.
Player player = ...;
atom.sendMessage("exampleSubChannel", "Hello! This is an example message", player);
```

## Project License
License info can be found [here](https://github.com/seailz/Atom/blob/master/LICENSE). This project is licensed under GNU General Public License V3.
