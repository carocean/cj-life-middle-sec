package cj.life.middle.sec.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "global_doc_label")
@Data
public class GlobalProxyLabel {
    @Id
    String id;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    String name;
    @Field(type = FieldType.Keyword, index = false)
    String iconUrl;//图标地址
    @Field(type = FieldType.Keyword, index = false)
    String proxyHost;//格式：@id://xxxx代表为资源id，http://代表是资源主机位置，或其它协议
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    String note;
}
