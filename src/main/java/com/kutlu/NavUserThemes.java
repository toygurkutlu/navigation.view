package com.kutlu;

import java.util.List;

public class NavUserThemes {
    private final List<NavTheme> userThemes;

    public NavUserThemes(List<NavTheme> userThemes) {
        this.userThemes = userThemes;
    }

    public List<NavTheme> getThemes() {
        return userThemes;
    }
}
