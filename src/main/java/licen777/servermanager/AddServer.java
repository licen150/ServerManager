package licen777.servermanager;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class AddServer implements SimpleCommand {
    ProxyServer proxy;
    public AddServer(ProxyServer proxy) {
        this.proxy = proxy;
    }
    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        // Get the arguments after the command alias
        if( invocation.arguments().length < 3 ) {
            source.sendMessage(Component.text("Usage: /addserv <name> <ip> <port>").color(NamedTextColor.RED));
            return;
        }
        String servname = invocation.arguments()[0];
        String servip = invocation.arguments()[1];
        int servport = Integer.parseInt(invocation.arguments()[2]);
        InetSocketAddress ipserv = new InetSocketAddress(servip, servport);
        ServerInfo infoserv = new ServerInfo(servname, ipserv);
        proxy.registerServer(infoserv);
        source.sendMessage(Component.text("Created server named " + servname + " with address " + servip + ":" + servport).color(NamedTextColor.GREEN));
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
