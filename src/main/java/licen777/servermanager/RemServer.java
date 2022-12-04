package licen777.servermanager;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class RemServer implements SimpleCommand {
    ProxyServer proxy;
    public RemServer(ProxyServer proxy) {
        this.proxy = proxy;
    }
    @Override
    public void execute(final Invocation invocation) {

        CommandSource source = invocation.source();
        // Get the arguments after the command alias
        if( invocation.arguments().length < 1 ) {
            source.sendMessage(Component.text("Usage: /delserv <name> ").color(NamedTextColor.RED));
            return;
        }
        if(Objects.equals(invocation.arguments()[0], "lobby")) {
            source.sendMessage(Component.text("Это нельзя удалять :)) ").color(NamedTextColor.RED));
            return;
        }
        String servname = invocation.arguments()[0];
        Optional<RegisteredServer> servtodel = proxy.getServer(invocation.arguments()[0]);
        ServerInfo infoserv = servtodel.get().getServerInfo();
        proxy.unregisterServer(infoserv);
        source.sendMessage(Component.text("Removed server named " + servname).color(NamedTextColor.GREEN));

    }
    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission("servermanager.use");
    }
    @Override
    public List<String> suggest(final Invocation invocation) {
        return List.of();
    }
    @Override
    public CompletableFuture<List<String>> suggestAsync(final Invocation invocation) {
        return CompletableFuture.completedFuture(List.of());
    }
}
