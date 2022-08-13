package cj.life.middle.sec.service.impl;

import cj.life.ability.api.exception.ApiException;
import cj.life.middle.sec.domain.GlobalDocument;
import cj.life.middle.sec.repository.GlobalDocumentRepository;
import cj.life.middle.sec.repository.GlobalProxyLabelRepository;
import cj.life.middle.sec.service.IGlobalDocumentService;
import cj.life.middle.sec.util.IDateUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GlobalDocumentService implements IGlobalDocumentService {
    @Resource
    GlobalDocumentRepository globalDocumentRepository;
    @Resource
    GlobalProxyLabelRepository globalProxyLabelRepository;

    @Override
    public String createDoc(String title, String content, String proxyLabel, String proxySrc, String primaryPicUrl, List<String> propPicUrls, String adPicUrl) {
        if (!globalProxyLabelRepository.existsById(proxyLabel)) {
            throw new ApiException("4004", "The proxyLabel does not exist.");
        }
        GlobalDocument document = new GlobalDocument();
        document.setContent(content);
        document.setCtime(IDateUtil.toDateEndMillisSecond(System.currentTimeMillis()));
        document.setAdPicUrl(adPicUrl);
        document.setPrimaryPicUrl(primaryPicUrl);
        document.setProxyLabel(proxyLabel);
        document.setTitle(title);
        document.setProxySrc(proxySrc);
        document.setPropPicUrls(propPicUrls);
        document.setContent(content);
        globalDocumentRepository.save(document);
        return document.getId();
    }

    @Override
    public void removeDoc(String id) {
        globalDocumentRepository.deleteById(id);
    }

    @Override
    public List<SearchHit<GlobalDocument>> searchDoc(String keywords, int limit, int offset) {
        SearchPage<GlobalDocument> page = globalDocumentRepository.searchByTitleMatchesOrContentMatchesOrderByCtimeDesc(keywords, keywords, PageRequest.of(offset, limit));
        List<SearchHit<GlobalDocument>> hits = page.getContent();
        return hits;
    }

    @Override
    public List<SearchHit<GlobalDocument>> searchDocByLabel(String keywords, String docLabelId, int limit, int offset) {
        SearchPage<GlobalDocument> page = globalDocumentRepository.searchByTitleMatchesAndProxyLabelOrContentMatchesAndProxyLabelOrderByCtimeDesc(keywords, docLabelId, keywords, docLabelId, PageRequest.of(offset, limit));
        List<SearchHit<GlobalDocument>> hits = page.getContent();
        return hits;
    }
}
