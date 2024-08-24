package com.emazon.stock.emazon.configuration;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public BeanConfiguration(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Emazon stock API")
                        .version("1.0")
                        .description("API de los microservicios implementados en la tienda Emazon"));
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
