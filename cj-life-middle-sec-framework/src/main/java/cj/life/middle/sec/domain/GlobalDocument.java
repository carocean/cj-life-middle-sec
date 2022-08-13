package cj.life.middle.sec.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(indexName = "global_document")
@Data
public class GlobalDocument {
    @Id
    String id;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    String title;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    String content;
    @Field(type = FieldType.Keyword)
    String ctime;
    @Field(type = FieldType.Keyword)
    String proxyLabel;//代理标签，即资源类别
    @Field(type = FieldType.Keyword)
    String proxySrc;//代理的标的物资源。与代理标签中的proxyHost组合为全地址，供label所代表的模块来解析
    @Field(type = FieldType.Keyword)
    String primaryPicUrl;//主图
    @Field(type = FieldType.Auto)
    List<String> propPicUrls;//属性图列表
    @Field(type = FieldType.Keyword)
    String adPicUrl;//详情图，如电商某商品的详情图片，一般是由商家制作的平面广告，图一般很长
}
