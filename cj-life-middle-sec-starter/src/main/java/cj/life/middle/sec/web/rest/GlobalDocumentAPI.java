package cj.life.middle.sec.web.rest;

import cj.life.ability.api.ResultCode;
import cj.life.ability.api.annotation.ApiResult;
import cj.life.ability.api.exception.ApiException;
import cj.life.middle.sec.domain.GlobalDocument;
import cj.life.middle.sec.service.IGlobalDocumentService;
import cj.life.middle.sec.web.IGlobalDocumentAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/document")
@Api(tags = {"全局搜索之文档"})
@RefreshScope
@Slf4j
public class GlobalDocumentAPI implements IGlobalDocumentAPI {
    @Autowired
    IGlobalDocumentService globalDocumentService;

    @GetMapping("/createDoc")
    @ApiOperation(value = "创建文档")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public String createDoc(@RequestParam String title,
                            @RequestParam String content,
                            @RequestParam String proxyLabel,
                            @RequestParam String proxySrc,
                            @ApiParam String primaryPicUrl,
                            @RequestParam(required = false) List<String> propPicUrls,
                            @ApiParam String adPicUrl) {
        if (!StringUtils.hasText(title)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(content)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(proxyLabel)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(proxySrc)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalDocumentService.createDoc(title, content, proxyLabel, proxySrc, primaryPicUrl, propPicUrls, adPicUrl);
    }

    @GetMapping("/removeDoc")
    @ApiOperation(value = "移除文档")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public void removeDoc(@RequestParam String id) {
        if (!StringUtils.hasText(id)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        globalDocumentService.removeDoc(id);
    }

    @GetMapping("/searchDoc")
    @ApiOperation(value = "搜索文档")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<SearchHit<GlobalDocument>> searchDoc(@RequestParam String keywords, @RequestParam int limit, @RequestParam int offset) {
        if (!StringUtils.hasText(keywords)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalDocumentService.searchDoc(keywords, limit, offset);
    }

    @GetMapping("/searchDocByLabel")
    @ApiOperation(value = "搜索文档【按标签】")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<SearchHit<GlobalDocument>> searchDocByLabel(@RequestParam String keywords, @RequestParam String docLabelId, @RequestParam int limit, @RequestParam int offset) {
        if (!StringUtils.hasText(keywords)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(docLabelId)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalDocumentService.searchDocByLabel(keywords, docLabelId, limit, offset);
    }
}
