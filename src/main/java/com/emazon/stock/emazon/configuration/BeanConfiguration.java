package com.emazon.stock.emazon.configuration;

import com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.emazon.stock.emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.emazon.stock.emazon.domain.api.IBrandServicePort;
import com.emazon.stock.emazon.domain.api.ICategoryServicePort;
import com.emazon.stock.emazon.domain.api.usecase.BrandUseCase;
import com.emazon.stock.emazon.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.emazon.domain.spi.IBrandPersistencePort;
import com.emazon.stock.emazon.domain.spi.ICategoryPersistencePort;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Emazon stock API")
                        .version("1.0")
                        .description("API de los microservicios implementados en la tienda Emazon"));
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
}
