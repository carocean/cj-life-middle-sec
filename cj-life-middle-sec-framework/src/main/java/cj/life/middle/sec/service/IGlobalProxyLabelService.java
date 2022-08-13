package cj.life.middle.sec.service;

import cj.life.middle.sec.domain.GlobalProxyLabel;

import java.util.List;

public interface IGlobalProxyLabelService {
    String createProxyLabel(String name, String iconUrl,String proxyHost, String note);

    void removeProxyLabel(String id);

    List<GlobalProxyLabel> listAllProxyLabel();

    List<GlobalProxyLabel> listProxyLabel(int limit, int offset);

    List<GlobalProxyLabel> listProxyLabelByName(String name, int limit, int offset);

    List<GlobalProxyLabel> listProxyLabelByNote(String note, int limit, int offset);

    GlobalProxyLabel getProxyLabel(String id);
}
