package tech.iboot.framework.starters.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import tech.iboot.framework.core.bean.PackageInfo;
import tech.iboot.framework.core.messageSource.AssembleMessageSource;
import tech.iboot.framework.core.utils.BannerPrinter;
import tech.iboot.framework.core.utils.ClassUtil;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * <strong>openapi自动配置类</strong>
 * <p></p>
 * Created on 2022/10/15
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConditionalOnProperty(
        name = {"springdoc.api-docs.enabled"},
        matchIfMissing = true
)
public class OpenApiConfiguration {

    /**
     * 信息源
     */
    @Resource
    private AssembleMessageSource assembleMessageSource;

    /**
     * 标题
     */
    private String title;

    /**
     * 版本
     */
    private String version;
    @Resource
    private OpenapiProperties openapiProperties;

    /**
     * openapi bean
     * @return OpenAPI
     */
    @Bean
    public OpenAPI springOpenApi() {
        initBaseInfo();
        Info info = new Info().title(assembleMessageSource.msg("openapi.title", this.title))
                .version(assembleMessageSource.msg("openapi.version", this.version));
        if (openapiProperties.getInfo()!=null) {
            Optional.ofNullable(openapiProperties.getInfo().getDescription()).ifPresent(info::description);
            Optional.ofNullable(openapiProperties.getInfo().getTermsOfService()).ifPresent(info::termsOfService);
            Optional.ofNullable(openapiProperties.getInfo().getContact()).ifPresent(info::contact);
            Optional.ofNullable(openapiProperties.getInfo().getLicense()).ifPresent(info::license);
        }
        new BannerPrinter(OpenApiConfiguration.class).print(new PackageInfo(OpenApiConfiguration.class));
        return new OpenAPI().info(info);
    }

    /**
     * 初始化信息，获取包信息
     **/
    private void initBaseInfo() {
        this.title = (openapiProperties.getInfo() == null || openapiProperties.getInfo().getTitle() == null) ? "" : openapiProperties.getInfo().getTitle();
        this.version = (openapiProperties.getInfo() == null || openapiProperties.getInfo().getVersion() == null) ?
                "" : openapiProperties.getInfo().getVersion();
        PackageInfo packageInfo = new PackageInfo(ClassUtil.deduceMainApplicationClass());
        if ("".equals(this.title)) {
            this.title = packageInfo.getTitle();
        }
        if ("".equals(this.version)) {
            this.version = packageInfo.getVersion();
        }
    }
}
