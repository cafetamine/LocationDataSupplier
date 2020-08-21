package com.actilive.lds.rest;

import com.actilive.lds.core.domain.ErrorResult;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ResponseResolver<T, E extends Enum<E>> {

    ResponseEntity<?> resolve(Either<ErrorResult<E>, T> result,
                              Function<T, ResponseEntity<?>> successFunction);

    default ResponseEntity<?> resolve(Either<ErrorResult<E>, T> result,
                                      Supplier<ResponseEntity<?>> successFunction) {
        return resolve(result, __ -> successFunction.get());
    }

}
