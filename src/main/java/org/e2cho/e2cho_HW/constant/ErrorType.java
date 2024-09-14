package org.e2cho.e2cho_HW.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {

    // ----- Common ------
    NotValidRequestError(
            HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."
    ),
    QueryParamTypeMismatchError(
            HttpStatus.BAD_REQUEST, "해당 쿼리 파라미터의 타입이 올바르지 않습니다."
    ),
    MissingQueryParamError(
            HttpStatus.BAD_REQUEST, "해당 파라미터의 값이 존재하지 않습니다.."
    ),
    AccessDeniedError(
            HttpStatus.FORBIDDEN, "접근할 수 없는 권한을 가진 사용자입니다."
    ),
    InternalServerError(
            HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생하였습니다. 문제가 지속되면 관리자에게 문의하세요."
    ),


    // ---- User ----
    UserNotFoundError(
            HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."
    ),

    // ----- Location ------
    OutOfBoundaryError(
            HttpStatus.BAD_REQUEST, "경위도가 범위(대한민국 내)를 벗어났습니다."
    ),
    UserLocationInfoNotFoundError(
            HttpStatus.NOT_FOUND, "유저는 존재하지만, 유저의 위치 정보가 존재하지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String message;
}
