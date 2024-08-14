package com.cafepal.order.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cafepal.order.exception.MenuNotFoundException;
import com.cafepal.order.exception.MessageException;
import com.cafepal.order.exception.BadRequestException;
import com.cafepal.order.exception.CartNotFoundException;
import com.cafepal.order.exception.ConflictException;
import com.cafepal.order.exception.ForbiddenException;
import com.cafepal.order.exception.UnauthorizedException;
import com.cafepal.order.exception.UnprocessableEntityException;
import com.cafepal.order.exception.VoucherNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> createErrorBody(String code, String message, String details, WebRequest request,
            String suggestion) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("code", code);
        errorDetails.put("message", message);
        errorDetails.put("details", details);
        errorDetails.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        errorDetails.put("path", request.getDescription(false));
        errorDetails.put("suggestion", suggestion);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "error");
        body.put("statusCode", HttpStatus.valueOf(code).value());
        body.put("error", errorDetails);

        return body;
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<Object> messageGlobalException(MessageException ex, WebRequest request) {
        Map<String, Object> body = createErrorBody(ex.getErrorCode(), ex.getMessage(), ex.getDetails(), request,
                ex.getSuggestion());
        return new ResponseEntity<>(body, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        String details = "An unexpected error occurred.";
        String suggestion = "Please try again later or contact support.";
        // String message = "INTERNAL_SERVER_ERROR";
        Map<String, Object> body = createErrorBody("INTERNAL_SERVER_ERROR", ex
                .getMessage(), details, request,
                suggestion);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<Object> handleMenuNotFoundException(MenuNotFoundException ex, WebRequest request) {
        String details = "The menu with the ID '" + request.getParameter("id") + "' does not exist in our records.";
        String suggestion = "Please check if the menu ID is correct information.";
        // String message = "NOT_FOUND";
        Map<String, Object> body = createErrorBody("NOT_FOUND", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> cartNotFoundException(CartNotFoundException ex, WebRequest request) {
        String details = "The Cart with the ID '" + request.getParameter("id") + "' does not exist in our records.";
        String suggestion = "Please check if the Cart ID is correct information.";
        // String message = "NOT_FOUND";
        Map<String, Object> body = createErrorBody("NOT_FOUND", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VoucherNotFoundException.class)
    public ResponseEntity<Object> voucherNotFoundException(VoucherNotFoundException ex, WebRequest request) {
        String details = "The Voucher with the Code '" + request.getParameter("id")
                + "' does not exist in our records or not valid";
        String suggestion = "Please check if the Voucher ID is correct information.";
        // String message = "NOT_FOUND";
        Map<String, Object> body = createErrorBody("NOT_FOUND", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        String details = "The request could not be understood or was missing required parameters.";
        String suggestion = "Please check your request parameters and try again.";
        // String message = "BAD_REQUEST";
        Map<String, Object> body = createErrorBody("BAD_REQUEST", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        String details = "Authentication is required and has failed or has not yet been provided.";
        String suggestion = "Please provide valid authentication credentials and try again.";
        // String message = "UNAUTHORIZED";
        Map<String, Object> body = createErrorBody("UNAUTHORIZED", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        String details = "You do not have permission to access this resource.";
        String suggestion = "Please ensure you have the necessary permissions and try again.";
        // String message = "FORBIDDEN";
        Map<String, Object> body = createErrorBody("FORBIDDEN", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {
        String details = "There was a conflict with the current state of the resource.";
        String suggestion = "Please resolve the conflict and try again.";
        // String message = "CONFLICT";
        Map<String, Object> body = createErrorBody("CONFLICT", ex.getMessage(), details, request, suggestion);

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(UnprocessableEntityException ex,
            WebRequest request) {
        String details = "The request was well-formed but was unable to be followed due to semantic errors.";
        String suggestion = "Please check the data you have submitted and try again.";
        // String message = "UNPROCESSABLE_ENTITY";
        Map<String, Object> body = createErrorBody("UNPROCESSABLE_ENTITY", ex
                .getMessage(), details, request,
                suggestion);

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
