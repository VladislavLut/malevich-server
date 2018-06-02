package com.malevich.server.util;

import com.sun.corba.se.spi.ior.Identifiable;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.*;

public class PreIdGenerator extends IdentityGenerator implements Configurable {

    private String sequenceCallSyntax;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        if (obj instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) obj;
            Serializable id = identifiable.getId();
            if (id != null) {
                return id;
            }
        }

        return ((Number) Session.class.cast(session)
                .createNativeQuery(sequenceCallSyntax)
                .uniqueResult()).intValue();
    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(JdbcEnvironment.class);
        final Dialect dialect = jdbcEnvironment.getDialect();

        final String sequencePerEntitySuffix = ConfigurationHelper.getString(
                CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,
                properties,
                DEF_SEQUENCE_SUFFIX);

        final String defaultSequenceName =
                ConfigurationHelper.getBoolean(
                        CONFIG_PREFER_SEQUENCE_PER_ENTITY,
                        properties,
                        false)
                        ? properties.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
                        : DEF_SEQUENCE_NAME;

        sequenceCallSyntax = dialect.getSequenceNextValString(
                ConfigurationHelper.getString(
                        SEQUENCE_PARAM,
                        properties,
                        defaultSequenceName));
    }
}
