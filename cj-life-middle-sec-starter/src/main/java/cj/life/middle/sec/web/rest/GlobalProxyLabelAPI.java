package cj.life.middle.sec.web.rest;

import cj.life.ability.api.ResultCode;
import cj.life.ability.api.annotation.ApiResult;
import cj.life.ability.api.exception.ApiException;
import cj.life.middle.sec.domain.GlobalProxyLabel;
import cj.life.middle.sec.service.IGlobalProxyLabelService;
import cj.life.middle.sec.web.IGlobalProxyLabelAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/proxy_label")
@Api(tags = {"全局搜索之标签"})
@RefreshScope
@Slf4j
public class GlobalProxyLabelAPI implements IGlobalProxyLabelAPI {
    @Autowired
    IGlobalProxyLabelService globalProxyLabelService;

    @GetMapping("/createProxyLabel")
    @ApiOperation(value = "创建代理标签", notes = "代理标签即代表模块或分类，用于对垂直内容的搜索")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public String createProxyLabel(@RequestParam String name,@RequestParam String iconUrl,@RequestParam String proxyHost, @ApiParam String note) {
        if (!StringUtils.hasText(name)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(iconUrl)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        if (!StringUtils.hasText(proxyHost)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalProxyLabelService.createProxyLabel(name,iconUrl,proxyHost, note);
    }

    @GetMapping("/removeProxyLabel")
    @ApiOperation(value = "移除代理标签")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public void removeProxyLabel(@RequestParam String id) {
        globalProxyLabelService.removeProxyLabel(id);
    }

    @GetMapping("/listAllProxyLabel")
    @ApiOperation(value = "列出所有代理标签")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<GlobalProxyLabel> listAllProxyLabel() {
        return globalProxyLabelService.listAllProxyLabel();
    }

    @GetMapping("/listProxyLabel")
    @ApiOperation(value = "列出代理标签【分页】")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<GlobalProxyLabel> listProxyLabel(int limit, int offset) {
        return globalProxyLabelService.listProxyLabel(limit, offset);
    }

    @GetMapping("/listProxyLabelByName")
    @ApiOperation(value = "列出代理标签【按标题】", notes = "支持模糊查询")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<GlobalProxyLabel> listProxyLabelByName(@RequestParam String name, @RequestParam int limit, @RequestParam int offset) {
        if (!StringUtils.hasText(name)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalProxyLabelService.listProxyLabelByName(name, limit, offset);
    }

    @GetMapping("/listProxyLabelByNote")
    @ApiOperation(value = "列出代理标签【按注释】", notes = "支持模糊查询")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public List<GlobalProxyLabel> listProxyLabelByNote(@RequestParam String note, @RequestParam int limit, @RequestParam int offset) {
        if (!StringUtils.hasText(note)) {
            throw new ApiException(ResultCode.PARAM_IS_BLANK);
        }
        return globalProxyLabelService.listProxyLabelByNote(note, limit, offset);
    }

    @GetMapping("/getProxyLabel")
    @ApiOperation(value = "获取代理标签【按标识】")
    @ApiResult
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "ok"),
            @ApiResponse(responseCode = "1002", description = "null_parameter"),
    })
    @SneakyThrows
    @Override
    public GlobalProxyLabel getProxyLabel(@RequestParam String id) {
        return globalProxyLabelService.getProxyLabel(id);
    }
}
