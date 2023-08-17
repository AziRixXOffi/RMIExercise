# RMIExercise
This code use RMI java to communicate between client and plugin on spigot server.
## Spigot plugin
Place spigot plugin in plugins folder. A config will be generated:
```yaml
port: default
```
## Client
Client jar file must be run with the following command:
```
java -jar Client-1.0-SNAPSHOT.jar
```
With flags:
- `-port (1099)` set port for RMI

Once the program is launched, you can execute commands in it. More details with /help
| Command  | Description |
| --- | --- |
| `/help` | Get help |
| `/stop` | Stop the client |
| `/list` | List online players |
| `/message <player> <message>` | Send a message to a player |
| `/block <world> <x> <y> <z> [material]` | Get or set a block if material arg is set |
| `/teleport <player> <world> <x> <y> <z>` | Teleport a player |
| `/playparticle <player> <particle> <world> <x> <y> <z>` | Play a particle |
