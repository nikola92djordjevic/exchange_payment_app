package rs.payment.exchange.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jooq.JooqExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class JooqConfiguration {


    @Autowired
    DataSource dataSource;

    @Bean
    public DSLContext DSLContext() throws SQLException {

        return new DefaultDSLContext(configuration());
    }


    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(
                new TransactionAwareDataSourceProxy(dataSource));
    }
    public DefaultConfiguration configuration() {
        DefaultConfiguration config = new DefaultConfiguration();
        config.set(connectionProvider());
        config.set(SQLDialect.POSTGRES);
        config.set(new Settings().withRenderNameStyle(RenderNameStyle.UPPER));
        config.set(new DefaultExecuteListenerProvider(
                new JooqExceptionTranslator() ));
        return config;
    }

    @Bean
    public RequestMappingHandlerAdapter requestHandler(){
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new SqlDateSerializer());
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        converter.setSupportedMediaTypes(mediaTypeList);
        adapter.getMessageConverters().add(converter);

        return adapter;
    }

}

