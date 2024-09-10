package br.com.fiap.sprintjava.user;

public record UserResponse(
        Long id_user,
        String url_avatar,
        String tx_description,
        String tx_interests
) {
    public static UserResponse fromModel(User user) {
        return new UserResponse(
                user.getId_user(),
                user.getUrl_avatar(),
                user.getTx_description(),
                user.getTx_interests()
        );
    }
}
