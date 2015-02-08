package org.serfeo.dev.guice.module;

import com.google.inject.name.Names;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import org.serfeo.dev.persistance.domain.*;
import org.serfeo.dev.persistance.mapper.EventMapper;
import org.serfeo.dev.persistance.mapper.OrderMapper;
import org.serfeo.dev.persistance.mapper.UserMapper;
import org.serfeo.dev.persistance.domain.ItemPriceStat;

public class PersistenceModule extends MyBatisModule {
    public static final String JDBC_SCHEMA   = "JDBC.schema";
    public static final String JDBC_USERNAME = "JDBC.username";
    public static final String JDBC_PASSWORD = "JDBC.password";

    @Override
    protected void initialize() {
        install(JdbcHelper.MySQL);
        bindConstant().annotatedWith( Names.named( JDBC_SCHEMA ) ).to( "daybook" );
        bindConstant().annotatedWith( Names.named( JDBC_USERNAME ) ).to("daybook");
        bindConstant().annotatedWith( Names.named( JDBC_PASSWORD ) ).to( "daybook" );

        environmentId( "production" );
        bindDataSourceProviderType( PooledDataSourceProvider.class );
        bindTransactionFactoryType( JdbcTransactionFactory.class );

        //mappers
        addMapperClass(EventMapper.class);
        addMapperClass(OrderMapper.class);
        addMapperClass(UserMapper.class);

        //aliases
        addSimpleAlias(CalendarEvent.class);
        addSimpleAlias(Order.class);
        addSimpleAlias(OrderItem.class);
        addSimpleAlias(Item.class);
        addSimpleAlias(BuyList.class);
        addSimpleAlias(BuyList.BuyListItem.class);
        addSimpleAlias(User.class);
        addSimpleAlias(ItemPriceStat.class);
        addSimpleAlias(ItemPriceStat.ItemPrice.class);
    }
}
