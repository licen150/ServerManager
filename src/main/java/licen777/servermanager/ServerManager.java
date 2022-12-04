package licen777.servermanager;

import com.google.inject.Inject;
import com.velocitypowered.api.command.*;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(
        id = "servermanager",
        name = "ServerManager",
        version = "1.0-SNAPSHOT",
        authors = {"licen777"}
)

public final class ServerManager {


    private final ProxyServer proxy;

    @Inject
    public ServerManager(ProxyServer proxy) {
        this.proxy = proxy;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        CommandManager commandManager = proxy.getCommandManager();
        // Here you can add meta for the command, as aliases and the plugin to which it belongs (RECOMMENDED)
        CommandMeta commandMeta = commandManager.metaBuilder("addserv")
                // This will create a new alias for the command "/test"
                // with the same arguments and functionality
                .plugin(this)
                .build();

        // You can replace this with "new EchoCommand()" or "new TestCommand()"
        // SimpleCommand simpleCommand = new TestCommand();
        // RawCommand rawCommand = new EchoCommand();
        // The registration is done in the same way, since all 3 interfaces implement "Command"
        SimpleCommand AddServer = new AddServer(proxy);
        //BrigadierCommand commandToRegister = AddServer.createBrigadierCommand(proxy);

        // Finally, you can register the command
        commandManager.register(commandMeta, AddServer);

        CommandMeta commandMeta1 = commandManager.metaBuilder("remserv")
                // This will create a new alias for the command "/test"
                // with the same arguments and functionality
                .plugin(this)
                .build();

        // You can replace this with "new EchoCommand()" or "new TestCommand()"
        // SimpleCommand simpleCommand = new TestCommand();
        // RawCommand rawCommand = new EchoCommand();
        // The registration is done in the same way, since all 3 interfaces implement "Command"
        SimpleCommand RemServer = new RemServer(proxy);
        //BrigadierCommand commandToRegister = AddServer.createBrigadierCommand(proxy);

        // Finally, you can register the command
        commandManager.register(commandMeta1, RemServer);
    }
}

