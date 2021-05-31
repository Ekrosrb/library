package com.ekros.library.controller.commands;

public class Path {
    private Path() {}
    public static final String LIBRARY = "/library";
    public static final String REDIRECT = "redirect:";
    public static final String PROFILE = "/profile";
    public static final String ADMIN_PAGE = "/admin";
    public static final String LIBRARIAN_PAGE = "/librarian";
    public static final String ERROR_PAGE = "/error";
    public static final String INDEX_PAGE = "/";

    public static final String MAIN_PAGE = LIBRARY + "/books";
    public static final String LIBRARY_LIBRARIAN = LIBRARY + LIBRARIAN_PAGE;
    public static final String LIBRARY_ADMIN = LIBRARY + ADMIN_PAGE;
    public static final String LIBRARY_PROFILE = LIBRARY + PROFILE;

    public static final String REDIRECT_MAIN_PAGE = REDIRECT + MAIN_PAGE;
    public static final String REDIRECT_PROFILE = REDIRECT + LIBRARY_PROFILE;


}
