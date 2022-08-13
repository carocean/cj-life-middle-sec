package cj.life.middle.sec.repository;

import cj.life.middle.sec.domain.GlobalProxyLabel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GlobalProxyLabelRepository extends ElasticsearchRepository<GlobalProxyLabel, String> {
    Page<GlobalProxyLabel> findByNameLike(String name, Pageable pageable);

    //SearchPage含有hits
    Page<GlobalProxyLabel> searchByNoteLike(String note, Pageable pageable);
}
