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
        // /addserv command start
        CommandManager commandManager = proxy.getCommandManager();
        CommandMeta commandMeta = commandManager.metaBuilder("addserv")
                .plugin(this)
                .build();

        SimpleCommand AddServer = new AddServer(proxy);
        commandManager.register(commandMeta, AddServer);
        // /addserv command end

        // /remserv command start
        CommandMeta commandMeta1 = commandManager.metaBuilder("remserv")

                .plugin(this)
                .build();
        SimpleCommand RemServer = new RemServer(proxy);
        commandManager.register(commandMeta1, RemServer);
        // /remserv command end
    }
}

