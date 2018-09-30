package com.wallet.crypto.trustapp.entity;

public class NetworkInfo {
//    名称
    public final String name;
//    币种
    public final String symbol;
//    查余额
    public final String rpcServerUrl;
//    后台 获取交易信息
    public final String backendUrl;
//    查交易详情
    public final String etherscanUrl;
//    发起交易时，用于签名的   实际就的该网络在以太坊上的network ID
    public final int chainId;
    public final boolean isMainNetwork;

    public NetworkInfo(
            String name,
            String symbol,
            String rpcServerUrl,
            String backendUrl,
            String etherscanUrl,
            int chainId,
            boolean isMainNetwork) {
        this.name = name;
        this.symbol = symbol;
        this.rpcServerUrl = rpcServerUrl;
        this.backendUrl = backendUrl;
        this.etherscanUrl = etherscanUrl;
        this.chainId = chainId;
        this.isMainNetwork = isMainNetwork;
    }
}
