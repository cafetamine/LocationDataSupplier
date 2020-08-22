package com.actilive.lds.repository.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.data.util.ParsingUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

// TODO [future] provide config in lds-service
@Component
public class ActiliveNamingStrategy implements NamingStrategy {

    @Override
    public @NotNull String getTableName(@NotNull final Class<?> type) {
        Assert.notNull(type, "Type must not be null.");
        return ParsingUtils.reconcatenateCamelCase(type.getSimpleName(), "_").toUpperCase();
    }

    @Override
    public @NotNull String getColumnName(@NotNull final RelationalPersistentProperty property) {
        Assert.notNull(property, "Property must not be null.");
        return property.getName().substring(0, 1).toUpperCase() + property.getName().substring(1);
    }

}
