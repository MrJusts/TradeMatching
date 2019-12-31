package com.template;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.core.crypto.SecureHash;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;
import net.corda.core.utilities.NetworkHostAndPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.jar.JarInputStream;

import static net.corda.core.utilities.NetworkHostAndPort.parse;

/**
 * Connects to a Corda node via RPC and performs RPC operations on the node.
 *
 * The RPC connection is configured using command line arguments.
 */
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws IOException {
        // Create an RPC connection to the node.
        if (args.length != 3) throw new IllegalArgumentException("Usage: Client <node address> <rpc username> <rpc password>");
        final NetworkHostAndPort nodeAddress = parse(args[0]);
        final String rpcUsername = args[1];
        final String rpcPassword = args[2];
        final CordaRPCClient client = new CordaRPCClient(nodeAddress);
        final CordaRPCOps proxy = client.start(rpcUsername, rpcPassword).getProxy();

        // Interact with the node.
        // For example, here we print the nodes on the network.
        final List<NodeInfo> nodes = proxy.networkMapSnapshot();
        logger.info("{}", nodes);
        InputStream inputstream = new FileInputStream("SWIFT.zip");

        SecureHash attachmentHash = proxy.uploadAttachment(inputstream);
        System.out.println(attachmentHash);

        InputStream stream = proxy.openAttachment(attachmentHash);

        JarInputStream in = new JarInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        System.out.println("Output from attachment :   "+br.readLine());


    }
}