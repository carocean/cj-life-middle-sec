package cj.life.middle.sec.web;

import cj.life.middle.sec.domain.GlobalDocument;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

public interface IGlobalDocumentAPI {
    String createDoc(String title, String content, String proxyLabel, String proxySrc, String primaryPicUrl, List<String> propPicUrls, String adPicUrl);

    void removeDoc(String id);

    List<SearchHit<GlobalDocument>> searchDoc(String keywords, int limit, int offset);

    List<SearchHit<GlobalDocument>> searchDocByLabel(String keywords, String docLabelId, int limit, int offset);
}
