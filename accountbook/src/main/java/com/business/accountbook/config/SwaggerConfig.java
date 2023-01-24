package com.business.accountbook.config;

import static org.springdoc.core.Constants.API_DOCS_URL;
import static org.springdoc.core.Constants.DEFAULT_WEB_JARS_PREFIX_URL;
import static org.springdoc.core.Constants.SWAGGER_UI_PATH;
import static org.springdoc.core.Constants.SWAGGER_UI_URL;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.WebFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Value(API_DOCS_URL)
    private String apiDocsUrl;
    @Value(SWAGGER_UI_PATH)
    private String uiPath;
  
    @Value("${server.servlet.context-path}")
    private String contextPath;

   /*  
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.business.accountbook"))
            .paths(PathSelectors.any())
            .build()
            .genericModelSubstitutes(Mono.class, Flux.class);
    }
 */
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().components(new Components())
                            .info(apiInfo());
    }
/* 
    private Set<String> getConsumeContentTypes() {
        Set<String> consumeContentTypes = new HashSet<>();

        consumeContentTypes.add("application/json;charset=UTF-8");
        consumeContentTypes.add("application/x-www-form-urlencoded");

        return consumeContentTypes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produceContentTypes = new HashSet<>();

        produceContentTypes.add("application/json;charset=UTF-8");

        return produceContentTypes;
    }
     */

    private Info apiInfo() {
        return new Info()
            .title("Account Book API")
            .description("Account Book API")
            .version("1.0.0");
    }



    @Bean
  RouterFunction<ServerResponse> routerFunction2() {
    StringBuilder sbUrl = new StringBuilder();
    sbUrl.append(contextPath);
    sbUrl.append(DEFAULT_WEB_JARS_PREFIX_URL);
    sbUrl.append(SWAGGER_UI_URL);
    sbUrl.append(contextPath + apiDocsUrl);
    return route(GET(contextPath + uiPath), req -> ServerResponse.temporaryRedirect(URI.create(sbUrl.toString())).build());
  }

/* 
  @Bean
  @ConditionalOnProperty("server.servlet.context-path")
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public WebFilter contextPathWebFilter(ServerProperties serverProperties) {
    String contextPath = serverProperties.getServlet().getContextPath();
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      String requestPath = request.getURI().getPath();
      if (requestPath.startsWith(contextPath + "/") || requestPath.equals(contextPath)) {
        return chain.filter(exchange.mutate().request(request.mutate().contextPath(contextPath).build()).build());

      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }

    };
  }
   */
}
