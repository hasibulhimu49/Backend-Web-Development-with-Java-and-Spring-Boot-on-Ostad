package com.SLMS.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstant {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String USER_REGISTRATION_SUCCESSFUL = "User registered successfully.";
    public static final String LOGIN_SUCCESSFUL = "Login successfully.";
    public static final String USER_ALREADY_EXISTS = "User already exists.";
    public static final String BOOK_CREATED_SUCCESSFUL = "Book created successfully.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String BOOK_NOT_FOUND = "Book not found.";
    public static final String BOOK_DELETED_SUCCESSFUL = "Book deleted successfully.";
    public static final String BOOK_UPDATED_SUCCESSFUL = "Book updated successfully.";
}
