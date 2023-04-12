package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.*;


public class AbstractLayout extends VerticalLayout implements RouterLayout {

    private static final String REALM_ACCESS = "realm_access";
    private static final String ROLES = "roles";

    private final HorizontalLayout headerLayout = new HorizontalLayout();
    private final AuthenticationContext authenticationContext;

    public AbstractLayout(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
        add(headerLayout);
        setHeightFull();

        HorizontalLayout menuLayout = new HorizontalLayout();
        RouteConfiguration.forApplicationScope()
                .getAvailableRoutes()
                .forEach(routeData -> {
                    if (routeData.getNavigationTarget().equals(TopicView.class)) {
                        return;
                    }
                    var routerLink = new RouterLink(
                            routeData.getNavigationTarget().getSimpleName(),
                            routeData.getNavigationTarget());
                    routerLink.setVisible(hasPermission(routeData));
                    menuLayout.add(routerLink);
                });


        menuLayout.setPadding(false);
        headerLayout.add(menuLayout);
        Button logout = new Button("Logout", event -> this.authenticationContext.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        menuLayout.add(logout);

        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        Optional<DefaultOidcUser> authenticatedUser = authenticationContext.getAuthenticatedUser(DefaultOidcUser.class);
        authenticatedUser.ifPresent(defaultOidcUser -> {
            if (defaultOidcUser.getUserInfo().getFullName() != null)
                headerLayout.add(new Span("Welcome, " + defaultOidcUser.getUserInfo().getFullName()));
        });

        setSizeFull();
    }

    private boolean hasPermission(RouteData routeData) {

        return routeData.getNavigationTarget().isAnnotationPresent(PermitAll.class) ||
                Arrays.stream(routeData.getNavigationTarget().getAnnotation(RolesAllowed.class).value())
                        .anyMatch(this::matchRole);
    }

    private boolean matchRole(String role) {
        DefaultOidcUser defaultOidcUser =
                authenticationContext.getAuthenticatedUser(DefaultOidcUser.class).get();

        var grantedRoles = (ArrayList<String>) defaultOidcUser.getUserInfo()
                .getClaimAsMap(REALM_ACCESS)
                .get(ROLES);

        return grantedRoles.stream()
                .anyMatch(grantedRole -> StringUtils.equals(grantedRole, role));
    }
}
