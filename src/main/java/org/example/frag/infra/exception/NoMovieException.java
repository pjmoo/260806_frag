package org.example.frag.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Handler까지 붙여서 표시를 해야 확실히 ErrorCode가 전파가 됨
// 이 상태면 error/404를 부르는 것까지는 가능
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoMovieException extends RuntimeException {
    public NoMovieException(String message) {
        super(message);
    }
}
