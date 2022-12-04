package licen777.servermanager;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.ILoggerFactory;

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

    // This method allows you to control who can execute the command.
    // If the executor does not have the required permission,
    // the execution of the command and the control of its autocompletion
    // will be sent directly to the server on which the sender is located
    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission("servermanager.use");
    }

    // With this method you can control the suggestions to send
    // to the CommandSource according to the arguments
    // it has already written or other requirements you need
    @Override
    public List<String> suggest(final Invocation invocation) {
        return List.of();
    }

    // Here you can offer argument suggestions in the same way as the previous method,
    // but asynchronously. It is recommended to use this method instead of the previous one
    // especially in cases where you make a more extensive logic to provide the suggestions
    @Override
    public CompletableFuture<List<String>> suggestAsync(final Invocation invocation) {
        return CompletableFuture.completedFuture(List.of());
    }
}
