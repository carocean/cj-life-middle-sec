package cj.life.middle.sec.repository;

import cj.life.middle.sec.domain.GlobalDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GlobalDocumentRepository extends ElasticsearchRepository<GlobalDocument, String> {
    @Highlight(
            fields = {
                    @HighlightField(
                            name = "title"
                    ),
                    @HighlightField(
                            name = "content"
                    ),
            },
            parameters = @HighlightParameters(
                    preTags = "<strong><font style='color:red'>",
                    postTags = "</font></strong>"
            )
    )
    SearchPage<GlobalDocument> searchByTitleMatchesOrContentMatchesOrderByCtimeDesc(String title, String content, Pageable pageable);

    @Highlight(
            fields = {
                    @HighlightField(
                            name = "title"
                    ),
                    @HighlightField(
                            name = "content"
                    ),
            },
            parameters = @HighlightParameters(
                    preTags = "<strong><font style='color:red'>",
                    postTags = "</font></strong>"
            )
    )
    SearchPage<GlobalDocument> searchByTitleMatchesAndProxyLabelOrContentMatchesAndProxyLabelOrderByCtimeDesc(String title, String proxyLabel1, String content, String proxyLabel2, Pageable pageable);
}
