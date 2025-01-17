package yosolito;

public class MainYoSolito {
    public static void main(String[] args) {

        System.out.println("Creatng token------------------------------");
        String token = JwtUtils.getToken();
        System.out.println("Analysing Token ----------------------------");
        VerificandoYClaims.isValidToken(token);

    }
}
