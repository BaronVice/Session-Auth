package admin_user.requests;

public record AuthenticationRequest (
        String username,
        String password
) {
}
